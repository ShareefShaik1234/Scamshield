package org.example.scamshield.repository;

import org.example.scamshield.entity.UrlScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlScanRepository extends JpaRepository<UrlScan, Long> {

}