package com.ai.artery.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ai.artery.dto.PredictionRequest;
import com.ai.artery.dto.PredictionResponse;

@Component
public class FlaskApiClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${flask.api.url}")
    private String flaskApiUrl;

    public PredictionResponse getPrediction(
            PredictionRequest request) {

        ResponseEntity<PredictionResponse> response =
                restTemplate.postForEntity(
                        flaskApiUrl,
                        request,
                        PredictionResponse.class
                );

        return response.getBody();
    }
}