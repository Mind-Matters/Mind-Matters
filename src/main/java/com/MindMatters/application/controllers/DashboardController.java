package com.MindMatters.application.controllers;

import com.MindMatters.application.models.*;
import com.MindMatters.application.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

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
            //            List<User> pendingUsers = new ArrayList<>();
            //            User user = userDao.findById(loggedInUser.getId());
            //come back to this method
            List<User> patients = userDao.findByIsProviderAndProviderId(true, loggedInUser.getId());
            model.addAttribute("patients", patients);

            List<User> pendingUsers = userDao.findByIsProviderAndIsVerifiedAndProviderId(false,false, loggedInUser.getId());
            model.addAttribute("pendingUsers", pendingUsers);
            // get pending users list for this particular provider
            // find all users provider has
            // find users on that list that are not verified
//           List<ProviderPatient> providerPatients = providerPatientDao.findAllByProvider(loggedInUser);
//            for(ProviderPatient providerPatient : providerPatients){
//                if(!providerPatient.getPatient().getIsVerified()){
//                    pendingUsers.add(providerPatient.getPatient());
//                }
//
//            model.addAttribute("pendingUsers", pendingUsers);
//            }
            return "provider-dashboard";
        } else {
            // send user to view
            model.addAttribute("greeting", "Hello " + loggedInUser.getUsername() + ",");

            // send pt's provider name to view
            User provider = userDao.findById(loggedInUser.getProviderId());
            model.addAttribute("providerInfo", "Your provider is: " + provider.getUsername());

            // populate trackMedications info
            List<TrackMedication> trackMedications = trackMedicationDao.findAllByUser(loggedInUser);
            // create class to pass medication values to the view and be iterable
            // not a db model, so don't put this in the model folder
            class MedTrack{
                private String date;
                private String taken;
                public MedTrack(String date, String taken){
                    this.date = date;
                    this.taken = taken;
                }
                public String getDate() {
                    return date;
                }
                public void setDate(String date) {
                    this.date = date;
                }
                public String getTaken() {
                    return taken;
                }
                public void setTaken(String taken) {
                    this.taken = taken;
                }
            }
            List<MedTrack> medTrackList = new ArrayList<>();
            //  change dates to just yyyy-mm-dd and change boolean to yes or no
            for(TrackMedication trackMedication : trackMedications){
                Date date = trackMedication.getDate();
                String dateStr = date.toString();
                String[] dateArr = dateStr.split(" ");
                String newDateStr = dateArr[0];
                medTrackList.add(new MedTrack(newDateStr, trackMedication.getTaken() ? "Yes" : "No"));
            }
            model.addAttribute("trackMedications", medTrackList);

            // populate mood_over_time info
            List<ScalingData> scalingData = scalingDataDao.findAllByUser(loggedInUser);
            String scalingChartTitle = "";
            if(scalingData.size() > 0){
                scalingChartTitle = "Mood Over Time";
            }
            model.addAttribute("scalingChartTitle", scalingChartTitle);
            StringBuilder scores = new StringBuilder("[");
            StringBuilder ids = new StringBuilder("[");
            for(int i = 0; i < scalingData.size(); i++){
                scores.append(scalingData.get(i).getScore());
                ids.append(scalingData.get(i).getId());
                if(i < scalingData.size() - 1){
                    scores.append(",");
                    ids.append(",");
                } else {
                    scores.append("]");
                    ids.append("]");
                }
            }
            model.addAttribute("scores", scores.toString());
            model.addAttribute("scoreIds", ids.toString());

            // populate calendar data | create a javascript array
            List<Event> events = eventDao.findAllByUser(loggedInUser);
            StringBuilder titles = new StringBuilder("['");
            StringBuilder descriptions = new StringBuilder("['");
            StringBuilder dates = new StringBuilder("['");
            for(int i = 0; i < events.size(); i++){
                titles.append(events.get(i).getTitle());
                descriptions.append(events.get(i).getDescription());
                dates.append(events.get(i).getDate().toString());
                if(i < events.size() - 1){
                    titles.append("','");
                    descriptions.append("','");
                    dates.append("','");
                }
            }

            titles.append("']");
            descriptions.append("']");
            dates.append("']");

            model.addAttribute("titles", titles.toString());
            model.addAttribute("descriptions", descriptions.toString());
            model.addAttribute("dates", dates.toString());

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
            userDao.deleteById(patient.getId());
            System.out.println(patient.getId() + patient.getUsername());


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