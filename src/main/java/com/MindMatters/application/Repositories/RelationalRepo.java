package com.MindMatters.application.Repositories;


import com.MindMatters.application.models.ProviderPatient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelationalRepo extends JpaRepository<ProviderPatient, Long> {




}
