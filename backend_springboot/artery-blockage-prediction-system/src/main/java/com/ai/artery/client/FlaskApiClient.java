package com.ai.artery.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ai.artery.dto.PredictionRequest;
import com.ai.artery.dto.PredictionResponse;

@Component
public class FlaskApiClient {
	
	private final String FLASK_API_URL = "http://127.0.0.1:5000/predict";
	
	public PredictionResponse getPrediction(PredictionRequest request) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON); 
		
		HttpEntity<PredictionRequest> entity = new HttpEntity<>(request, headers);
		
		PredictionResponse response = restTemplate.postForObject(FLASK_API_URL, entity, PredictionResponse.class);
		
		return response;
	}

}
