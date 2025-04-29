package com.example.evaluatorservice.dto;

import lombok.Data;
import java.util.List;

@Data
public class Experience {
    private String company;
    private String role;
    private int durationInMonths;
    private List<String> skillsUsed;
}