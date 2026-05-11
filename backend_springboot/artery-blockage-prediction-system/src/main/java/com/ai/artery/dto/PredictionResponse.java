package com.ai.artery.dto;

public class PredictionResponse {
	
	private int prediction;
	private double confidence;
	private String risk_level;
	private String message;
	
	public PredictionResponse() {
		
	}

	public int getPrediction() {
		return prediction;
	}

	public void setPrediction(int prediction) {
		this.prediction = prediction;
	}

	public double getConfidence() {
		return confidence;
	}

	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}

	public String getRisk_level() {
		return risk_level;
	}

	public void setRisk_level(String risk_level) {
		this.risk_level = risk_level;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
