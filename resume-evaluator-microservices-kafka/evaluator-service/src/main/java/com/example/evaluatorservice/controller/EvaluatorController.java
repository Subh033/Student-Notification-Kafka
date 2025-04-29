package com.example.evaluatorservice.controller;

import com.example.evaluatorservice.dto.ApplicantRequest;
import com.example.evaluatorservice.dto.ApplicantResponse;
import com.example.evaluatorservice.service.EvaluatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluator")
@RequiredArgsConstructor
public class EvaluatorController {

    private final EvaluatorService evaluatorService;

    @PostMapping("/evaluate")
    public List<ApplicantResponse> evaluateApplicants(@RequestBody List<ApplicantRequest> applicants) {
        return evaluatorService.evaluate(applicants);
    }
}
