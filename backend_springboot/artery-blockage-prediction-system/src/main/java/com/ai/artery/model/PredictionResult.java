package com.ai.artery.model;

import java.time.LocalDateTime;

public class PredictionResult {
	
	private Long id;
	
	private int age;
	private int sex;
	private int chol;
	
	private int prediction;
	
	private double confidence;
	
	private String riskLevel;
	
	private LocalDateTime predictionTime;

	public PredictionResult() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getChol() {
		return chol;
	}

	public void setChol(int chol) {
		this.chol = chol;
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

	public String getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}

	public LocalDateTime getPredictionTime() {
		return predictionTime;
	}

	public void setPredictionTime(LocalDateTime predictionTime) {
		this.predictionTime = predictionTime;
	}
	
	
	
	
	

}
