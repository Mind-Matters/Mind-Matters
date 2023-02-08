package com.MindMatters.application.Controllers.Repositories;


import com.MindMatters.application.Models.ProviderPatient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelationalRepo extends JpaRepository<ProviderPatient, Long> {

}
