package com.medinar.covidwatch.controller;

import com.medinar.covidwatch.domain.GlobalTotal;
import com.medinar.covidwatch.service.GlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Rommel Medina
 */
@Controller
public class GlobalController {

    @Autowired
    GlobalService globalService;

    @GetMapping("/global")
    public String getTotal(
            Model model,
            @RequestParam(required = false) boolean yesterday,
            @RequestParam(required = false) boolean twoDaysAgo,
            @RequestParam(required = false) boolean allowNull
    ) throws Exception {

        GlobalTotal globalTotal = globalService.getTotal(
                yesterday,
                twoDaysAgo,
                allowNull
        );

        model.addAttribute("cases", globalTotal.getCases());
        model.addAttribute("deaths", globalTotal.getDeaths());
        model.addAttribute("recovered", globalTotal.getRecovered());
        model.addAttribute("active", globalTotal.getActive());
        model.addAttribute(
                "closed",
                globalTotal.getDeaths() + globalTotal.getRecovered()
        );

        return "index";
    }

}
