package com.medinar.covid19app.controller;

import com.medinar.covid19app.domain.ContinentalTotal;
import com.medinar.covid19app.domain.CountryTotal;
import com.medinar.covid19app.domain.GlobalTotal;
import com.medinar.covid19app.service.Covid19Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.medinar.covid19app.service.ContinentalService;
import com.medinar.covid19app.service.GlobalService;

/**
 *
 * @author Rommel Medina
 */
@Controller
public class Covid19Controller {

    @Autowired
    Covid19Service covid19Service;
    
    @Autowired
    ContinentalService continentalService;

    @GetMapping("/continents")
    public String getContinents(Model model) throws Exception {
        List<ContinentalTotal> continentalTotals = covid19Service
                .getContinentalTotals(true, true, "", true);
        model.addAttribute("continentalTotals", continentalTotals);
        return "covid19";
    }

    @GetMapping("/countries")
    public String getCountriesTotal(Model model) throws Exception {
        List<CountryTotal> countryTotals = covid19Service
                .getNationalTotals(true, true, "", true);
        model.addAttribute("countryTotals", countryTotals);
        return "covid19";
    }

    @GetMapping("/continents/{continent}")
    public String getContinentalTotal(
            Model model,
            @PathVariable String continent
    ) throws Exception {
        List<CountryTotal> countryTotalsByContinent = covid19Service
                .getNationalTotalsbyContinent(continent, true, true, "", true);
        
        ContinentalTotal continentalTotal = continentalService.getTotalByContinent(continent);
        
        model.addAttribute("continent", continent);
        model.addAttribute("continentTotalCases", continentalTotal.getCases());
        model.addAttribute("continentTotalDeaths", continentalTotal.getDeaths());
        
        model.addAttribute("countryTotals", countryTotalsByContinent);
        return "continent";
    }

    @GetMapping("/countries/{country}")
    public String getCountryTotal(
            Model model,
            @PathVariable String country
    ) throws Exception {
        CountryTotal countryTotal = covid19Service
                .getCountryTotal(country, true, true, "", true);
        model.addAttribute("flag", countryTotal.getCountryInfo().getFlag());

        model.addAttribute("cases", countryTotal.getCases());
        model.addAttribute("deaths", countryTotal.getDeaths());
        model.addAttribute("recovered", countryTotal.getRecovered());

        model.addAttribute("active", countryTotal.getActive());
        model.addAttribute("closed", countryTotal.getDeaths() + countryTotal.getRecovered());

        float activePercentage = (float) ((Double.valueOf(countryTotal.getActive()) * 100) / Double.valueOf(countryTotal.getCases()));
        model.addAttribute("activePercentage", activePercentage);

        float recoveredPercentage = (float) ((Double.valueOf(countryTotal.getRecovered()) * 100) / Double.valueOf(countryTotal.getCases()));
        model.addAttribute("recoveredPercentage", recoveredPercentage);

        float deathPercentage = (float) ((Double.valueOf(countryTotal.getDeaths()) * 100) / Double.valueOf(countryTotal.getCases()));
        model.addAttribute("deathPercentage", deathPercentage);

        model.addAttribute("todayCases", countryTotal.getTodayCases());
        model.addAttribute("todayRecovered", countryTotal.getTodayRecovered());
        model.addAttribute("todayDeaths", countryTotal.getTodayDeaths());

        return "country";
    }

}
