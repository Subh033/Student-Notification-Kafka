package com.example.evaluatorservice.dto;

import lombok.Data;

@Data
public class Education {
    private String degree;
    private String major;
    private double cgpa;
    private int year;
}