package com.corona.tracker.Service;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.corona.tracker.Model.LocationData;

import jakarta.annotation.PostConstruct;

@Service
public class CoronaVirusDeathDataService {

	private List<LocationData> allData = new ArrayList<>();

	// getter and setter for locatinData
	public List<LocationData> getAllData() {
		return allData;
	}

	public void setAllData(List<LocationData> allData) {
		this.allData = allData;
	}
	
	// constant declaration, holding URL of csv file containing 
	// global covid-19 death data
	private static String virusDataUrl = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";
	
	@PostConstruct
	@Scheduled(cron = "* * * 1 * *")
	public void fetchVirusData() throws IOException, InterruptedException {
		
		List<LocationData> newData = new ArrayList<>();
		
		// HTTPS request setup
		
		// to handle HTTP request
		HttpClient client = HttpClient.newHttpClient();
		
		// constructs an HTTP GET request using specified URL
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(virusDataUrl)).build();
		
		// Sends the request and captures the response as a string
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		
		// CSV Parsing and Processing
		StringReader csvConverter = new StringReader(response.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvConverter);
		
		
		// iterating through current records
		for(CSVRecord csv : records) {
			
			LocationData locData = new LocationData();
			locData.setState(csv.get("Province/State"));
			locData.setCountry(csv.get("Country/Region"));
			// Extracts the latest reported death count from the CSV, which is expected to be in the last column
			// casting to integer
			int latestCase = Integer.parseInt(csv.get(csv.size() - 1));
			locData.setLatestDeathcount(latestCase);
			int prevCase = Integer.parseInt(csv.get(csv.size() - 2));
			locData.setDifferFromPY(latestCase - prevCase); // to get difference
			
			newData.add(locData);
		}
		// Updates the allstates list with the newly fetched data
		this.allData = newData;
	}
}
