//authors: Aidan Fahey, Connor Dailey
//date: 04/16/20205
//purpose: program designed to show the shortest path to a destination with specified amount of locations, mode of transportation, and ETA.
//AI generation: We used ChatGPT to provide approach ideas, skeletons, and help with functions for the content view body (35% percent wholly provided by AI). Whereas, the other portions we used AI for debugging and troubleshooting, then implementing new approaches by ourselves.

import SwiftUI
import MapKit
import CoreLocation
// MARK: - Main View
struct ContentView: View {
    @StateObject private var locationManager = LocationManager()
    @State private var searchQuery = ""
    @State private var searchResults: [MKMapItem] = []
    @State private var selectedPlace: MKMapItem?
    @State private var resultLimit: String = ""
    @State private var showingSplash = true
    @State private var fadeInOut = false
    @State private var selectedOption = MKDirectionsTransportType.automobile
    @State private var showHelloAlert = false
    @State private var etaString: String = "" // ETA string shown in bottom-right
    var body: some View {
        ZStack {
            if showingSplash {
                VStack(spacing: 20) {
                    Text("Welcome to Shortcut")
                        .font(.largeTitle)
                        .fontWeight(.bold)
                        .multilineTextAlignment(.center)
                    Text("""
                    - Enter a place to search (e.g., 'Coffee', 'Target').
                    - Optional: Enter a number (n) to limit results.
                    - If no number is entered, results are limited by proximity.
                    - Please select your desired mode of transportation.
                    """)
                        .multilineTextAlignment(.center)
                        .padding()
                    Text("Tap anywhere to continue")
                        .foregroundColor(.gray)
                        .italic()
                        .opacity(fadeInOut ? 1 : 0.0)
                        .onAppear {
                            withAnimation(Animation.easeInOut(duration: 1).repeatForever(autoreverses: true)) {
                                fadeInOut.toggle()
                            }
                        }
                }
                .frame(maxWidth: .infinity, maxHeight: .infinity)
                .background(Color.white)
                .onTapGesture {
                    withAnimation {
                        showingSplash = false
                    }
                }
            } else {
                ZStack(alignment: .top) {
                    MapView(
                        searchResults: $searchResults,
                        selectedPlace: $selectedPlace,
                        selectedOption: $selectedOption,
                        showHelloAlert: $showHelloAlert,
                        etaString: $etaString // Pass ETA
                    )
                    .environmentObject(locationManager)
                    .edgesIgnoringSafeArea(.all)
                    VStack {
                        HStack {
                            TextField("Search Places", text: $searchQuery, onCommit: searchPlaces)
                                .textFieldStyle(RoundedBorderTextFieldStyle())
                                .padding(.leading)
                            TextField("n", text: $resultLimit)
                                .textFieldStyle(RoundedBorderTextFieldStyle())
                                .frame(width: 50)
                                .keyboardType(.numberPad)
                            Menu("Mode") {
                                Button("Automobile") { selectedOption = .automobile }
                                Button("Walking") { selectedOption = .walking }
                            }
                            Button(action: searchPlaces) {
                                Image(systemName: "magnifyingglass")
                                    .padding()
                            }
                        }
                        .background(Color.white)
                        .clipShape(RoundedRectangle(cornerRadius: 10))
                        .padding()
                    }
                    // ETA rectangle at bottom-right
                    VStack {
                        Spacer()
                        HStack {
                            Spacer()
                            if !etaString.isEmpty {
                                Text("ETA: \(etaString) min")
                                    .padding(10)
                                    .background(Color.white.opacity(0.9))
                                    .cornerRadius(10)
                                    .shadow(radius: 5)
                                    .padding()
                            }
                        }
                    }
                }
            }
        }
    }
    
    private func searchPlaces() {
        guard let userLocation = locationManager.userLocation else { return }
        let request = MKLocalSearch.Request()
        request.naturalLanguageQuery = searchQuery
        let isValidLimit = Int(resultLimit) != nil && Int(resultLimit)! > 0
        let regionSpan = isValidLimit
            ? MKCoordinateSpan(latitudeDelta: 0.05, longitudeDelta: 0.05)
            : MKCoordinateSpan(latitudeDelta: 0.01, longitudeDelta: 0.01)
        request.region = MKCoordinateRegion(center: userLocation, span: regionSpan)
        MKLocalSearch(request: request).start { response, error in
            guard let response = response else { return }
            let sortedResults = response.mapItems.sorted {
                let loc1 = $0.placemark.location ?? CLLocation()
                let loc2 = $1.placemark.location ?? CLLocation()
                return loc1.distance(from: CLLocation(latitude: userLocation.latitude, longitude: userLocation.longitude)) <
                    loc2.distance(from: CLLocation(latitude: userLocation.latitude, longitude: userLocation.longitude))
            }
            if isValidLimit, let n = Int(resultLimit) {
                searchResults = Array(sortedResults.prefix(n))
            } else {
                searchResults = sortedResults
            }
        }
    }
}
extension MKDirectionsTransportType{
    var readableName: String{
        switch self{
        case .automobile: return "Driving"
        case .any: return "Walking"
        default: return "Walking"
        }
    }
}
// MARK: - Map View
struct MapView: UIViewRepresentable {
    @EnvironmentObject var locationManager: LocationManager
    @Binding var searchResults: [MKMapItem]
    @Binding var selectedPlace: MKMapItem?
    @Binding var selectedOption: MKDirectionsTransportType
    @Binding var showHelloAlert: Bool
    @Binding var etaString: String // <-- ETA string
    let mapView = MKMapView()
    func makeUIView(context: Context) -> MKMapView {
        mapView.delegate = context.coordinator
        mapView.showsUserLocation = true
        return mapView
    }
    func updateUIView(_ uiView: MKMapView, context: Context) {
        uiView.removeAnnotations(uiView.annotations)
        searchResults.forEach { place in
            let annotation = MKPointAnnotation()
            annotation.coordinate = place.placemark.coordinate
            annotation.title = place.name
            uiView.addAnnotation(annotation)
        }
    }
    func makeCoordinator() -> Coordinator {
        Coordinator(self)
    }
    class Coordinator: NSObject, MKMapViewDelegate {
        var parent: MapView
        var currentRoute: MKRoute?
        init(_ parent: MapView) {
            self.parent = parent
        }
        func mapView(_ mapView: MKMapView, didSelect annotationView: MKAnnotationView) {
            guard let annotation = annotationView.annotation else { return }
            let selected = parent.searchResults.first {
                $0.placemark.coordinate.latitude == annotation.coordinate.latitude &&
                $0.placemark.coordinate.longitude == annotation.coordinate.longitude
            }
            if let selected = selected {
                parent.selectedPlace = selected
                showRoute(to: selected, in: mapView)
            }
        }
        func mapView(_ mapView: MKMapView, rendererFor overlay: MKOverlay) -> MKOverlayRenderer {
            if let polyline = overlay as? MKPolyline {
                let renderer = MKPolylineRenderer(polyline: polyline)
                renderer.strokeColor = .blue
                renderer.lineWidth = 4
                return renderer
            }
            return MKOverlayRenderer()
        }
        func showRoute(to destination: MKMapItem, in mapView: MKMapView) {
            guard let userLocation = parent.locationManager.userLocation else { return }
            let request = MKDirections.Request()
            request.source = MKMapItem(placemark: MKPlacemark(coordinate: userLocation))
            request.destination = destination
            request.transportType = parent.selectedOption
            MKDirections(request: request).calculate { response, error in
                guard let route = response?.routes.first else { return }
                let etaInMinutes = route.expectedTravelTime / 60
                DispatchQueue.main.async {
                    mapView.removeOverlays(mapView.overlays)
                    self.currentRoute = route
                    mapView.addOverlay(route.polyline)
                    self.parent.etaString = String(format: "%.0f", etaInMinutes)
                }
            }
        }
    }
}
// MARK: - Location Manager
class LocationManager: NSObject, ObservableObject, CLLocationManagerDelegate {
    private let manager = CLLocationManager()
    @Published var userLocation: CLLocationCoordinate2D?
    override init() {
        super.init()
        manager.delegate = self
        manager.requestWhenInUseAuthorization()
        manager.startUpdatingLocation()
    }
    func locationManager(_ manager: CLLocationManager, didChangeAuthorization status: CLAuthorizationStatus) {
        if status == .authorizedWhenInUse || status == .authorizedAlways {
            manager.startUpdatingLocation()
        }
    }
    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        guard let location = locations.last else { return }
        DispatchQueue.main.async {
            self.userLocation = location.coordinate
        }
    }
}
// MARK: - Preview
struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}


