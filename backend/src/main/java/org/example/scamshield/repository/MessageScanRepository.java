package org.example.scamshield.repository;

import org.example.scamshield.entity.MessageScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageScanRepository extends JpaRepository<MessageScan, Long> {

    List<MessageScan> findByStatus(String status);

    List<MessageScan> findByMessageType(String messageType);

    List<MessageScan> findByRiskScoreGreaterThanEqual(int riskScore);
}