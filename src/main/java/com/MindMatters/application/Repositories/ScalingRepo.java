package com.MindMatters.application.Repositories;

import com.MindMatters.application.models.ScalingData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScalingRepo extends JpaRepository<ScalingData, Long> {
}
