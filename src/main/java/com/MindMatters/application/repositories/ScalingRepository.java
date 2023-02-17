package com.MindMatters.application.repositories;

import com.MindMatters.application.models.ScalingData;
import com.MindMatters.application.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScalingRepository extends JpaRepository<ScalingData, Long> {
    List<ScalingData> findAllByUser(User loggedInUser);
}
