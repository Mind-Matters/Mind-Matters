package com.MindMatters.application.Repositories;

import com.MindMatters.application.Models.ScalingData;
import com.MindMatters.application.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScalingRepository extends JpaRepository<ScalingData, Long> {
    List<ScalingData> findAllByUser(User loggedInUser);
}
