package com.ai.artery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.artery.client.FlaskApiClient;
import com.ai.artery.dto.PredictionRequest;
import com.ai.artery.dto.PredictionResponse;

@Service
public class PredictionService {
	
	@Autowired
	private FlaskApiClient flaskApiClient;
	
	public PredictionResponse predictHeartDisease(
			PredictionRequest request) {
		
		validateRequest(request);
		
		PredictionResponse response = flaskApiClient.getPrediction(request);
		
		return response;
	}
	
	public void validateRequest(PredictionRequest request) {
		
		if (request.getAge() <= 0) {
			throw new RuntimeException("Invalid age");
		}
		
		if (request.getChol() <= 0) {
			throw new RuntimeException("Invalid cholestrol value");
		}
	}
}
