# corona_death_tracker
 corona death data all over the world

 # COVID-19 Global Death Tracker
 This is a Spring Boot application that tracks and updates COVID-19 death counts worldwide by fetching data from Johns Hopkins University’s CSSE COVID-19 Dataset. The data is updated automatically and displayed via a web interface.

 # Features:
 * Live Data Fetching: The application regularly fetches updated COVID-19 death data from a CSV file hosted on GitHub.
 * Global Statistics: It displays the latest death count for each country and region globally.
 * Automatic Updates: The data is refreshed daily through a scheduled task.
 * Simple UI: The data is presented on a web page using Thymeleaf templates, showing the total deaths and individual country-wise details.

# How It Works:
* The service fetches the CSV data from "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv"
  source containing global COVID-19 death counts.
* The CoronaVirusDeathDataService class processes the CSV data and calculates the latest death counts along with the change in deaths compared to the previous day.
* The controller (CoronaDeathTrackerController) handles requests to the home page and passes the data to the view layer.
    
# Technologies Used:
* Java 17
* Spring Boot
* Thymeleaf for HTML Rendering
* Apache Commons CSV for Data Parsing
* 
