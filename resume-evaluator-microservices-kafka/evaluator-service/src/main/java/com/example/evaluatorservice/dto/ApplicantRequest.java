package com.example.evaluatorservice.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "applicants")
public class ApplicantRequest {
    @Id
    private String applicantId;
    private Personal personal;
    private List<Education> education;
    private List<Experience> experience;
    private List<Project> projects;
}