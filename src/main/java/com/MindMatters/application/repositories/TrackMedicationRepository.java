package com.MindMatters.application.repositories;


import com.MindMatters.application.models.TrackMedication;
import com.MindMatters.application.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrackMedicationRepository extends JpaRepository<TrackMedication, Long> {

    List<TrackMedication> findAllByUser(User loggedInUser);
}
