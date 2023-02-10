package com.MindMatters.application.Controllers;

import com.MindMatters.application.Models.ProviderPatient;
import com.MindMatters.application.Models.User;
import com.MindMatters.application.Repositories.ProviderPatientRepository;
import com.MindMatters.application.Repositories.UserRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DashboardController {

    public final UserRepo userDao;
    public final ProviderPatientRepository providerPatientDao;

    public DashboardController(UserRepo userDao, ProviderPatientRepository providerPatientRepository){
        this.userDao = userDao;
        this.providerPatientDao = providerPatientRepository;
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
            }
            model.addAttribute("pendingUsers", pendingUsers);
            return "provider-dashboard";
        }
        else {
            return "patient-dashboard";
        }
    }

    @PostMapping("/approval")
    public String approveUser(@RequestParam(name = "id") long id, @RequestParam Boolean isApproved){
        User patient = userDao.findById(id);
        User provider = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ProviderPatient providerPatient = new ProviderPatient(provider, patient);
        if(isApproved){
            patient.setIsVerified(true);
            userDao.save(patient);
        } else {
            // patient is not approved: remove patient user and providerPatient rows
            userDao.delete(patient);
            providerPatientDao.delete(providerPatient);
        }
        return "redirect:/dashboard";
    }
}
