package com.medinar.covid19app.controller;

import com.medinar.covid19app.domain.ContinentalTotal;
import com.medinar.covid19app.domain.CountryTotal;
import com.medinar.covid19app.domain.WorldTotal;
import com.medinar.covid19app.service.Covid19Service;
import java.util.List;
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
public class Covid19Controller {

    @Autowired
    Covid19Service covid19Service;

    @GetMapping("/")
    public String covid19(Model model) throws Exception {
        WorldTotal worldTotal = covid19Service.getWorldTotal(true, true, true);

        model.addAttribute("cases", worldTotal.getCases());
        model.addAttribute("deaths", worldTotal.getDeaths());
        model.addAttribute("recovered", worldTotal.getRecovered());

        model.addAttribute("active", worldTotal.getActive());
        model.addAttribute("closed", worldTotal.getDeaths() + worldTotal.getRecovered());

        return "covid19";

    }

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
    
    @GetMapping("/continents/{continent}/countries")
    public String getCountriesTotal(
            Model model, 
            @PathVariable String continent
    ) throws Exception {
        List<CountryTotal> countryTotalsByContinent = covid19Service
                .getNationalTotalsbyContinent(continent, true, true, "", true);
        model.addAttribute("continent", continent);
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
        model.addAttribute("countryTotal", countryTotal);
        return "country";
    }

}
