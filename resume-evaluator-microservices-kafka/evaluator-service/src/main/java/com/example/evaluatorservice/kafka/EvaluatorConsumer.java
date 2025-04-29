package com.example.evaluatorservice.kafka;

import com.example.evaluatorservice.dto.ApplicantRequest;
import com.example.evaluatorservice.dto.ApplicantResponse;
import com.example.evaluatorservice.service.EvaluatorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class EvaluatorConsumer {

    private final EvaluatorService evaluatorService;

    @KafkaListener(topics = "applicant-topic", groupId = "evaluator-group")
    public void consume(@Payload List<LinkedHashMap<String, Object>> rawApplicants) {
        ObjectMapper mapper = new ObjectMapper();

        List<ApplicantRequest> applicants = rawApplicants.stream()
                .map(data -> mapper.convertValue(data, ApplicantRequest.class))
                .toList();

        log.info("Received applicant list from Kafka: {} applicants", applicants.size());
        List<ApplicantResponse> responses = evaluatorService.evaluate(applicants);
        responses.forEach(res -> log.info("Evaluated Response: {}", res));
    }

}