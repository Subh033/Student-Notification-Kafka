package com.example.resumeservice.service;

import com.example.resumeservice.dto.ApplicantRequest;
import com.example.resumeservice.dto.ApplicantResponse;
import com.example.resumeservice.kafka.ResumeProducer;
import com.example.resumeservice.repository.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ResumeServiceImpl implements ResumeService {
    @Autowired
    private  ResumeProducer resumeProducer;
    @Autowired
    private  ApplicantRepository applicantRepository;

    @Override
    public List<ApplicantResponse> submitApplicants(List<ApplicantRequest> applicants) {
        applicantRepository.saveAll(applicants);
        resumeProducer.sendApplicants(applicants);

        return applicants.stream().map(applicant -> {
            ApplicantResponse response = new ApplicantResponse();
            response.setApplicantId(applicant.getApplicantId());
            response.setStatus("Processing");
            response.setReasons(List.of("Will be evaluated by EvaluatorService"));
            return response;
        }).toList();
    }
}