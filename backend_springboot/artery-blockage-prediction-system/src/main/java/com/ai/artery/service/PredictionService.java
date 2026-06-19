package com.ai.artery.service;

import java.util.List;

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
                        "No response received from Flask API");
            }

            PredictionResult result =
                    new PredictionResult();

            result.setAge(request.getAge());
            result.setSex(request.getSex());
            result.setChol(request.getChol());

            result.setPrediction(
                    response.getPrediction());

            result.setConfidence(
                    response.getConfidence());

            result.setRiskLevel(
                    response.getRisk_level());

            result.setPredictionTime(
                    java.time.LocalDateTime.now());

            repository.save(result);

            return response;

        }
        catch (Exception e) {

            PredictionResponse errorResponse =
                    new PredictionResponse();

            errorResponse.setPrediction(-1);
            errorResponse.setConfidence(0.0);
            errorResponse.setRisk_level("ERROR");

            errorResponse.setMessage(
                    "Backend error: "
                    + e.getMessage());

            return errorResponse;
        }
    }

    public List<PredictionResult>
    getPredictionHistory() {

        return repository
                .findAllByOrderByPredictionTimeDesc();
    }

    public void validateRequest(
            PredictionRequest request) {

        if (request.getAge() < 1 ||
                request.getAge() > 120) {

            throw new RuntimeException(
                    "Age must be between 1 and 120");
        }

        if (request.getChol() < 50 ||
                request.getChol() > 700) {

            throw new RuntimeException(
                    "Cholesterol must be between 50 and 700");
        }

        if (request.getTrestbps() < 50 ||
                request.getTrestbps() > 300) {

            throw new RuntimeException(
                    "Blood Pressure must be between 50 and 300");
        }

        if (request.getThalach() < 50 ||
                request.getThalach() > 250) {

            throw new RuntimeException(
                    "Heart Rate must be between 50 and 250");
        }

        if (request.getOldpeak() < 0 ||
                request.getOldpeak() > 10) {

            throw new RuntimeException(
                    "Oldpeak must be between 0 and 10");
        }
    }
}