package com.example.evaluatorservice.service;

import com.example.evaluatorservice.dto.ApplicantRequest;
import com.example.evaluatorservice.dto.ApplicantResponse;
import java.util.List;

public interface EvaluatorService {
    List<ApplicantResponse> evaluate(List<ApplicantRequest> applicants);
}