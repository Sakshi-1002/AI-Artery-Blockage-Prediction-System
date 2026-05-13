package com.ai.artery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ai.artery.dto.PredictionRequest;
import com.ai.artery.dto.PredictionResponse;
import com.ai.artery.service.PredictionService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class PredictionController {
	
	@Autowired
	private PredictionService predictionService;
	
	@PostMapping("/api/predict")
	public PredictionResponse predictHeartDiesease(
			@RequestBody PredictionRequest request) {
		
		return predictionService.predictHeartDisease(request);
	}

}
