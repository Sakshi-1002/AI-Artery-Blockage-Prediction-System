package com.ai.artery.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ai.artery.dto.PredictionRequest;
import com.ai.artery.dto.PredictionResponse;
import com.ai.artery.model.PredictionResult;
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
	
	@GetMapping("/history")
	public String historyPage(
	        Model model) {

	    List<PredictionResult> history =

	            predictionService
	            .getPredictionHistory();

	    model.addAttribute(
	            "history",
	            history);

	    return "history";
	}
	
	@Autowired
	private PredictionService predictionService;
	
	@PostMapping("/predict")
	public String predict(
			PredictionRequest request,
			Model model) {
		PredictionResponse response = predictionService.predictHeartDisease(request);
		
		List<String> recommendations =
		        new ArrayList<>();
		
		if (request.getAge() >= 60) {

		    recommendations.add(
		        "Age above 60 years - regular cardiovascular screening recommended");
		}

		if (request.getChol() > 240) {

		    recommendations.add(
		        "Elevated cholesterol detected - reduce saturated fat intake");
		}

		if (request.getTrestbps() > 140) {

		    recommendations.add(
		        "High resting blood pressure detected - monitor blood pressure regularly");
		}

		if (request.getExang() == 1) {

		    recommendations.add(
		        "Exercise induced angina detected - consult a cardiologist before strenuous activity");
		}

		if (request.getOldpeak() > 2.0) {

		    recommendations.add(
		        "Abnormal ST depression observed - further cardiovascular evaluation recommended");
		}
		
		if (recommendations.isEmpty()) {

		    recommendations.add(
		        "Maintain your current healthy lifestyle");

		    recommendations.add(
		        "Continue regular physical activity");

		    recommendations.add(
		        "Attend routine preventive health checkups");
		}
		
		model.addAttribute("riskLevel", response.getRisk_level());
		
		model.addAttribute("confidence", response.getConfidence());
		
		model.addAttribute("message", response.getMessage());
		
		model.addAttribute("recommendations",recommendations);
		
		return "result";
	}
	
	

}
