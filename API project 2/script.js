let number = {
    apikey: "o4aNF0iK0rrPZQ5cos4TOgoY4apG9xJq",
    fetchNumber: function (phoneNumber) {
        fetch (
            "https://api.apilayer.com/number_verification/validate?number=" + phoneNumber + "&apikey=" + this.apikey
        )
        .then((response) => response.json())
        .then((data) => this.displayNumber(data));
    },
    displayNumber: function(data) {
        const number =  data.number;
        const country_prefix = data.country_prefix;
        const country_name = data.country_name;
        const location = data.location;
        const valid = data.valid;
        document.querySelector(".number").innerText = number;
        document.querySelector(".country_prefix").innerText = "Coutry prefix: " + country_prefix;
        document.querySelector(".country_name").innerText = "Country name: " + country_name;
        document.querySelector(".location").innerText = "Location: " + location;
        document.querySelector(".valid").innerText = "Valid: " + valid;
        document.querySelector(".content").classList.remove("loading");
    },

    search: function() {
        this.fetchNumber(document.querySelector(".search-bar").value);
    },
};
document.querySelector(".search button").addEventListener("click", function () {
    number.search();
});
document.querySelector(".search-bar").addEventListener("keyup", function (event) {
    if(event.key == "Enter"){
        number.search();
    }
});
