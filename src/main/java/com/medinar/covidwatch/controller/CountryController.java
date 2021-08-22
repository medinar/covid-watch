package com.medinar.covidwatch.controller;

import com.medinar.covidwatch.domain.CountryTotal;
import com.medinar.covidwatch.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Rommel Medina
 */
@Controller
public class CountryController {

    @Autowired
    CountryService countryService;

    @GetMapping("/countries/{country}")
    public String getTotal(
            Model model,
            @PathVariable String country
    ) throws Exception {

        CountryTotal countryTotal = countryService
                .getTotal(country, false, false, "cases", true);

        model.addAttribute("flag", countryTotal.getCountryInfo().getFlag());

        model.addAttribute("cases", countryTotal.getCases());
        model.addAttribute("deaths", countryTotal.getDeaths());
        model.addAttribute("recovered", countryTotal.getRecovered());

        model.addAttribute("active", countryTotal.getActive());
        model.addAttribute(
                "closed",
                countryTotal.getDeaths() + countryTotal.getRecovered()
        );

        float activePercentage = (float) ((Double.valueOf(countryTotal.getActive()) * 100)
                / Double.valueOf(countryTotal.getCases()));
        model.addAttribute("activePercentage", activePercentage);

        float recoveredPercentage = (float) ((Double.valueOf(countryTotal.getRecovered()) * 100) 
                / Double.valueOf(countryTotal.getCases()));
        model.addAttribute("recoveredPercentage", recoveredPercentage);

        float deathPercentage = (float) ((Double.valueOf(countryTotal.getDeaths()) * 100) 
                / Double.valueOf(countryTotal.getCases()));
        model.addAttribute("deathPercentage", deathPercentage);

        model.addAttribute("todayCases", countryTotal.getTodayCases());
        model.addAttribute("todayRecovered", countryTotal.getTodayRecovered());
        model.addAttribute("todayDeaths", countryTotal.getTodayDeaths());

        return "country";
    }
}
