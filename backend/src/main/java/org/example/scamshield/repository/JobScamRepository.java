package org.example.scamshield.repository;

import org.example.scamshield.entity.JobScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobScamRepository extends JpaRepository<JobScan, Long> {

}