package com.ai.artery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ai.artery.model.PredictionResult;

public interface PredictionResultRepository extends JpaRepository<PredictionResult, Long> {
	
	

}
