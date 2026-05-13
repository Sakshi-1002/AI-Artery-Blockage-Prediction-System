package com.ai.artery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.artery.client.FlaskApiClient;
import com.ai.artery.dto.PredictionRequest;
import com.ai.artery.dto.PredictionResponse;
import com.ai.artery.model.PredictionResult;
import com.ai.artery.repository.PredictionResultRepository;

@Service
public class PredictionService {
	
	@Autowired
	private FlaskApiClient flaskApiClient;
	
	@Autowired
	private PredictionResultRepository repository;
	
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
	        
	        PredictionResult result = new PredictionResult();
	        
	        result.setAge(request.getAge());
	        result.setSex(request.getSex());
	        result.setChol(request.getChol());
	        result.setPrediction(response.getPrediction());
	        result.setConfidence(response.getConfidence());
	        result.setRiskLevel(response.getRisk_level());
	        result.setPredictionTime(java.time.LocalDateTime.now());
	        
	        repository.save(result);

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
