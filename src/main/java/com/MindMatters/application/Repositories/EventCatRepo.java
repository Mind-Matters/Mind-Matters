package com.MindMatters.application.Repositories;


import com.MindMatters.application.Models.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventCatRepo extends JpaRepository<EventCategory, Long> {


}
