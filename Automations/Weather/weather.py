#Inspired by a tutorial. I added and enhanced features for a more in-depth weather app. You can see what I referenced here: https://www.youtube.com/watch?v=nQUx4lYdg7E #

import requests

API_KEY = ""
BASE_URL = "https://api.openweathermap.org/data/2.5/weather"

city = input("Enter city name: ").lower()
request_url = f"{BASE_URL}?appid={API_KEY}&q={city}"
response = requests.get(request_url)

if response.status_code == 200:

    #weather variables#
    data = response.json()
    weather = data['weather'][0]['description']
    temperature_C = round(data["main"]["temp"] - 273.15)
    temperature_F = round(round(data["main"]["temp"] - 273.15) * 1.8 + 32)
    feels_like = round(round(data["main"]["feels_like"] - 273.15) * 1.8 + 32)
    temp_max = round(round(data["main"]["temp_max"] - 273.15) * 1.8 + 32)
    temp_min = round(round(data["main"]["temp_min"] - 273.15) * 1.8 + 32)

    #weather print statements#
    print("Weather: ", weather)
    print("Current temperature C: ", temperature_C, "degrees celsius")
    print("Current temperature F: ", temperature_F, "degrees fahrenheit")
    print("Feels-like: ", feels_like, "degrees fahrenheit")
    print("Temperature high: ", temp_max, "degrees fahrenheit")
    print("Temperature low: ", temp_min, "degrees fahrenheit")

else:
    print("An error has occured. City not recognized or possible spelling mistake.")
