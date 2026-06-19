package com.ai.artery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ai.artery.model.PredictionResult;

public interface PredictionResultRepository
extends JpaRepository<PredictionResult, Long> {

List<PredictionResult>
findAllByOrderByPredictionTimeDesc();

}