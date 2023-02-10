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
            List<User> pendingUsers = userDao.findByIsProviderAndIsVerified(false, false);
            model.addAttribute("pendingUsers", pendingUsers);
            return "provider-dashboard";
        }
        else {
            return "patient-dashboard";
        }
    }

    @PostMapping("/approval")
    public String approveUser(@RequestParam(name = "id") long id, @RequestParam Boolean isApproved){
        User user = userDao.findById(id);
        if(isApproved){
            user.setIsVerified(true);
            userDao.save(user);
            User provider = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            ProviderPatient providerPatient = new ProviderPatient(provider, user);
            providerPatientDao.save(providerPatient);
        } else {
            // patient is not approved: remove patient account
            userDao.delete(user);
        }
        return "redirect:/dashboard";
    }
}
