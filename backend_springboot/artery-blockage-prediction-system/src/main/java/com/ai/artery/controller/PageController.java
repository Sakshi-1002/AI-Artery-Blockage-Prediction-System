package com.ai.artery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ai.artery.dto.PredictionRequest;
import com.ai.artery.dto.PredictionResponse;
import com.ai.artery.service.PredictionService;

@Controller
public class PageController {
	
	@GetMapping("/")
	public String homePage() {
		return "home";
	}
	
	@GetMapping("/predict-page")
	public String predictPage() {
		return "predict";
	}
	
	@Autowired
	private PredictionService predictionService;
	
	@PostMapping("/predict")
	public String predict(
			PredictionRequest request,
			Model model) {
		PredictionResponse response = predictionService.predictHeartDisease(request);
		
		model.addAttribute("riskLevel", response.getRisk_level());
		
		model.addAttribute("confidence", response.getConfidence());
		
		model.addAttribute("message", response.getMessage());
		
		return "result";
	}
	
	

}
