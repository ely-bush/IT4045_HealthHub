package com.healthhub.healthhub.repositories;

import com.healthhub.healthhub.entities.HealthLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HealthLogRepository extends JpaRepository<HealthLog, UUID> {
}