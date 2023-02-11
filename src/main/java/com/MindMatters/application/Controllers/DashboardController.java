package com.MindMatters.application.Controllers;

import com.MindMatters.application.Models.ProviderPatient;
import com.MindMatters.application.Models.TrackMedication;
import com.MindMatters.application.Models.User;
import com.MindMatters.application.Repositories.ProviderPatientRepository;
import com.MindMatters.application.Repositories.TrackMedicationRepository;
import com.MindMatters.application.Repositories.UserRepo;
import jakarta.transaction.Transaction;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class DashboardController {

    public final UserRepo userDao;
    public final ProviderPatientRepository providerPatientDao;
    public final TrackMedicationRepository trackMedicationDao;

    public DashboardController(UserRepo userDao, ProviderPatientRepository providerPatientRepository, TrackMedicationRepository trackMedicationRepository){
        this.userDao = userDao;
        this.providerPatientDao = providerPatientRepository;
        this.trackMedicationDao = trackMedicationRepository;
    }

    @GetMapping("/dashboard")
    public String goDashboard(Model model){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(loggedInUser.getIsProvider()) {
            // get pending users list
            List<User> pendingUsers = new ArrayList<>();
            // get pending users list for this particular provider
            // find all users provider has
            // find users on that list that are not verified
            List<ProviderPatient> providerPatients = providerPatientDao.findAllByProvider(loggedInUser);
            for(ProviderPatient providerPatient : providerPatients){
                if(!providerPatient.getPatient().getIsVerified()){
                    pendingUsers.add(providerPatient.getPatient());
                }

            model.addAttribute("pendingUsers", pendingUsers);
            }
            return "provider-dashboard";
        } else {
            // user is patient
            // populate patient info
            List<TrackMedication> trackMedications = trackMedicationDao.findAllByUser(loggedInUser);
            model.addAttribute("trackMedications", trackMedications);

            return "patient-dashboard";
        }
    }

    @Transactional
    @PostMapping("/approval")
    public String approveUser(@RequestParam(name = "id") long id, @RequestParam Boolean isApproved){
        User patient = userDao.findById(id);
        User provider = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ProviderPatient providerPatient = providerPatientDao.findByProviderAndPatient(provider, patient);
        if(isApproved){
            patient.setIsVerified(true);
            userDao.save(patient);
        } else {
            // patient is not approved: remove patient user and providerPatient rows
            providerPatientDao.deleteById(providerPatient.getId());
            userDao.deleteByUsername(patient.getUsername());
        }
        return "redirect:/dashboard";
    }

    @PostMapping("/trackMedication")
    public String trackMedication(@RequestParam(name = "taken") String taken){
        // Gather inputs
        boolean isTaken = taken.equals("true"); //convert string to boolean
        User patient = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // format data
        TrackMedication trackMedication = new TrackMedication();
        trackMedication.setUser(patient);
        trackMedication.setTaken(isTaken);
        trackMedication.setDate(new Date());

        // update db
        trackMedicationDao.save(trackMedication);

        return "redirect:/dashboard";
    }
}
