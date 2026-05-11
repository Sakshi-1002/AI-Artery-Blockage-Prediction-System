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

	    try {

	        PredictionResponse response =
	                flaskApiClient.getPrediction(request);

	        if (response == null) {
	            throw new RuntimeException(
	                    "No response received from Flask API"
	            );
	        }

	        return response;

	    } catch (Exception e) {

	        PredictionResponse errorResponse =
	                new PredictionResponse();

	        errorResponse.setPrediction(-1);
	        errorResponse.setConfidence(0.0);
	        errorResponse.setRisk_level("ERROR");
	        errorResponse.setMessage(
	                "Backend error: " + e.getMessage()
	        );

	        return errorResponse;
	    }
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
