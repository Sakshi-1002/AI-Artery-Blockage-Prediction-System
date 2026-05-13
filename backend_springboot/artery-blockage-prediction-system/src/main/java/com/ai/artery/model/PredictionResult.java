package com.ai.artery.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "prediction_results")
public class PredictionResult {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
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
