
package com.example.resumeservice.service;

import com.example.resumeservice.dto.ApplicantRequest;
import com.example.resumeservice.dto.ApplicantResponse;
import java.util.List;

public interface ResumeService {
    List<ApplicantResponse> submitApplicants(List<ApplicantRequest> applicants);
}
