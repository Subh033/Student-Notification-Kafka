
package com.example.resumeservice.controller;

import com.example.resumeservice.dto.ApplicantRequest;
import com.example.resumeservice.dto.ApplicantResponse;
import com.example.resumeservice.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    private  ResumeService resumeService;

    @PostMapping("/submitAll")
    public ResponseEntity<List<ApplicantResponse>> submitAll(@RequestBody List<ApplicantRequest> applicants) {
        // Delegate to service for processing and return response
        return ResponseEntity.ok(resumeService.submitApplicants(applicants));
    }
}
