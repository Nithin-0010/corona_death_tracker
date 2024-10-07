package com.corona.tracker.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.corona.tracker.Model.LocationData;
import com.corona.tracker.Service.CoronaVirusDeathDataService;

@Controller
public class CoronaDeathTrackerController {

	@Autowired
	private CoronaVirusDeathDataService dataService;
	
	@GetMapping("/")
	public String home(Model mdl) {
		List<LocationData> coronaData =dataService.getAllData();
		
		// calculates the total number of reported deaths 
		int totalDeaths = coronaData.stream().mapToInt(state -> state.getLatestDeathcount()).sum();
		
		mdl.addAttribute("coronaData", coronaData);
		mdl.addAttribute("totalDeaths", totalDeaths);
		
		return "Home";
	}
}
