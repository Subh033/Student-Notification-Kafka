
package com.example.resumeservice.kafka;

import com.example.resumeservice.dto.ApplicantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka.topic.name}")
    private String topicName;

    public void sendApplicants(List<ApplicantRequest> applicants) {
        // Send applicants list to Kafka topic
        kafkaTemplate.send(topicName, applicants);
    }
}
