package com.MindMatters.application.repositories;

import com.MindMatters.application.models.Event;
import com.MindMatters.application.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByUser(User loggedInUser);

    List<Event> findAllByUserId(long id);

}