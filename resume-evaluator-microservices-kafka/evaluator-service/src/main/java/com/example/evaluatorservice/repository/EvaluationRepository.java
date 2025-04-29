package com.example.evaluatorservice.repository;

import com.example.evaluatorservice.entity.EvaluationResult;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EvaluationRepository extends MongoRepository<EvaluationResult,String> {
}
