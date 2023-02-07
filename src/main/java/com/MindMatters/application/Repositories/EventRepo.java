package com.MindMatters.application.Repositories;

import com.MindMatters.application.Models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepo extends JpaRepository<Event, Long> {
}
