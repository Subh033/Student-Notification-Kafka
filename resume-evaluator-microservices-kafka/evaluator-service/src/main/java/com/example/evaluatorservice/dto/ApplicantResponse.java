package com.example.evaluatorservice.dto;

import lombok.Data;
import java.util.List;

@Data
public class ApplicantResponse {
    private String applicantId;
    private String status;
    private List<String> reasons;
}