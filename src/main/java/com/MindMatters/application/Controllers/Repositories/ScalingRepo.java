package com.MindMatters.application.Controllers.Repositories;

import com.MindMatters.application.Models.ScalingData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScalingRepo extends JpaRepository<ScalingData, Long> {
}
