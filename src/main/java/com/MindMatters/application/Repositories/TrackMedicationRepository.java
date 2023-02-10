package com.MindMatters.application.Repositories;


import com.MindMatters.application.Models.TrackMedication;
import com.MindMatters.application.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrackMedicationRepository extends JpaRepository<TrackMedication, Long> {

    List<TrackMedication> findAllByUser(User loggedInUser);
}
