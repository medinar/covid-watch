package com.medinar.covid19app.controller;

import com.medinar.covid19app.domain.GlobalTotal;
import com.medinar.covid19app.service.GlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Rommel Medina
 */
@Controller
public class GlobalController {

    @Autowired
    GlobalService globalService;

    @GetMapping("/global")
    public String covid19(Model model) throws Exception {
        GlobalTotal globalTotal = globalService.getTotal(false, false, true);

        model.addAttribute("cases", globalTotal.getCases());
        model.addAttribute("deaths", globalTotal.getDeaths());
        model.addAttribute("recovered", globalTotal.getRecovered());

        model.addAttribute("active", globalTotal.getActive());
        model.addAttribute("closed", globalTotal.getDeaths() + globalTotal.getRecovered());

        return "index";

    }

}
