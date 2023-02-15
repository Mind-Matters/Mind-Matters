package com.MindMatters.application.Repositories;

import com.MindMatters.application.Models.Event;
import com.MindMatters.application.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByUser(User loggedInUser);
}
