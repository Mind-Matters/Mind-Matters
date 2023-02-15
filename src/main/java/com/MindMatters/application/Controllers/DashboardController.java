package com.MindMatters.application.Controllers;

import com.MindMatters.application.Models.*;
import com.MindMatters.application.Repositories.*;
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

    public final UserRepository userDao;
    public final TrackMedicationRepository trackMedicationDao;
    public final ScalingRepository scalingDataDao;
    public final EventRepository eventDao;


    public DashboardController(UserRepository userDao, TrackMedicationRepository trackMedicationRepository, ScalingRepository scalingDataDao, EventRepository eventDao){
        this.userDao = userDao;
        this.trackMedicationDao = trackMedicationRepository;
        this.scalingDataDao = scalingDataDao;
        this.eventDao = eventDao;
    }

    @GetMapping("/dashboard")
    public String goDashboard(Model model){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(loggedInUser.getIsProvider()) {
            // get pending users list
            List<User> pendingUsers = new ArrayList<>();
            User user = userDao.findById(loggedInUser.getId());

            List<User> patients = userDao.findByIsProviderAndProviderId(true, loggedInUser.getId());
            model.addAttribute("patients", patients);
            //work around to get the events for the patients onclick
            // get events list for this particular provider for their patients
            //Im logged in as a provider and loggedin user id = provider id which links back to the patient id


            //I need to link the event id to the patient id in the user table
//            model.addAttribute("events", userEvents);
            // get pending users list for this particular provider
            // find all users provider has
            // find users on that list that are not verified
/*            List<ProviderPatient> providerPatients = providerPatientDao.findAllByProvider(loggedInUser);
            for(ProviderPatient providerPatient : providerPatients){
                if(!providerPatient.getPatient().getIsVerified()){
                    pendingUsers.add(providerPatient.getPatient());
                }

            model.addAttribute("pendingUsers", pendingUsers);
            }*/
            return "provider-dashboard";
        } else {
            // user is patient
            // populate trackMedications info
            List<TrackMedication> trackMedications = trackMedicationDao.findAllByUser(loggedInUser);
            model.addAttribute("trackMedications", trackMedications);

            // populate mood_over_time info
            List<ScalingData> scalingData = scalingDataDao.findAllByUser(loggedInUser);
            model.addAttribute("scalingData", scalingData);

            // populate calendar data
            List<Event> events = eventDao.findAllByUser(loggedInUser);
            model.addAttribute("events", events);

            return "patient-dashboard";
        }
    }

    @Transactional
    @PostMapping("/approval")
    public String approveUser(@RequestParam(name = "id") long id, @RequestParam Boolean isApproved){
        User patient = userDao.findById(id);
        User provider = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        /*ProviderPatient providerPatient = providerPatientDao.findByProviderAndPatient(provider, patient);*/
        if(isApproved){
            patient.setIsVerified(true);
            userDao.save(patient);
        } else {
            // patient is not approved: remove patient user and providerPatient rows
            /*providerPatientDao.deleteById(providerPatient.getId());*/
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