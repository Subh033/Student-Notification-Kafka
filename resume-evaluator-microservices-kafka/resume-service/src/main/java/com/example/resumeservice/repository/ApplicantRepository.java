
package com.example.resumeservice.repository;

import com.example.resumeservice.dto.ApplicantRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantRepository extends MongoRepository<ApplicantRequest, String> {
}
