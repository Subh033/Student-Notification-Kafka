package com.example.evaluatorservice.service;

import com.example.evaluatorservice.dto.*;
import com.example.evaluatorservice.entity.EvaluationResult;
import com.example.evaluatorservice.repository.EvaluationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class EvaluatorServiceImpl implements EvaluatorService {
    @Autowired
    private  EvaluationRepository evaluationRepository;

    @Override
    public List<ApplicantResponse> evaluate(List<ApplicantRequest> applicants) {
        List<ApplicantResponse> responses = new ArrayList<>();

        for (ApplicantRequest applicant : applicants) {
            List<String> reasons = new ArrayList<>();
            boolean isFullTime = false;
            boolean isIntern = false;

            boolean hasGoodBTech = applicant.getEducation().stream()
                    .anyMatch(e -> e.getDegree().equalsIgnoreCase("B.Tech") && e.getCgpa() > 7.0);
            if (hasGoodBTech) reasons.add("B.Tech with CGPA > 7.0");

            boolean hasExperience = applicant.getExperience().stream()
                    .mapToInt(e -> e.getDurationInMonths()).sum() > 12;
            if (hasExperience) reasons.add("Experience: " + applicant.getExperience().stream().mapToInt(e -> e.getDurationInMonths()).sum() + " months");

            boolean hasRelevantProject = applicant.getProjects().stream()
                    .anyMatch(p -> p.getTechStack().stream()
                            .anyMatch(tech -> tech.equalsIgnoreCase("Spring Boot") || tech.equalsIgnoreCase("React")));
            if (hasRelevantProject) reasons.add("Project using Spring Boot or React");

            boolean hasComplexProject = applicant.getProjects().stream()
                    .anyMatch(p -> p.getTechStack().size() >= 4);

            if (hasGoodBTech && hasExperience && hasRelevantProject) {
                isFullTime = true;
            } else if (hasComplexProject) {
                isIntern = true;
                reasons.clear();
                reasons.add("Project with 4 tech stacks");
            } else {
                continue;
            }

            ApplicantResponse response = new ApplicantResponse();
            response.setApplicantId(applicant.getApplicantId());
            response.setStatus(isFullTime ? "Full-Time Eligible" : isIntern ? "Intern Eligible" : "Not Eligible");
            response.setReasons(reasons);
            responses.add(response);
            EvaluationResult result=new EvaluationResult(response);
            evaluationRepository.save(result);
        }

        return responses;
    }
}
