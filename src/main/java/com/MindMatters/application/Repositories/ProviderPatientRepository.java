package com.MindMatters.application.Repositories;


import com.MindMatters.application.Models.ProviderPatient;
import com.MindMatters.application.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProviderPatientRepository extends JpaRepository<ProviderPatient, Long> {

    List<ProviderPatient> findAllByProvider(User loggedInUser);

    ProviderPatient findByProviderAndPatient(User provider, User patient);

    void deleteByPatient(User patient);
}
