package com.MindMatters.application.Controllers;



import com.MindMatters.application.Controllers.Repositories.RelationalRepo;
import com.MindMatters.application.Models.ProviderPatient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class RelationalController {

    private RelationalRepo relationalDao;





    public RelationalController(RelationalRepo relationalDao) {
        this.relationalDao = relationalDao;

    }

    @GetMapping("/signup")
    public String assignPatient(Model model){
        model.addAttribute("myProvider", new ProviderPatient());
        return ("/signup");

    }
}
