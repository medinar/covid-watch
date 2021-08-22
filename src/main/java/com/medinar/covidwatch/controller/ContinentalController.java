package com.medinar.covidwatch.controller;

import com.medinar.covidwatch.domain.ContinentalTotal;
import com.medinar.covidwatch.domain.CountryTotal;
import com.medinar.covidwatch.service.ContinentalService;
import com.medinar.covidwatch.service.CountryService;
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
public class ContinentalController {

    @Autowired
    ContinentalService continentalService;
    
    @Autowired
    CountryService countryService;
    
    @GetMapping("/continental/{continent}")
    public String getContinentalTotal(
            Model model,
            @PathVariable String continent
    ) throws Exception {
        List<CountryTotal> countryTotalsByContinent = countryService
                .getTotalsbyContinent(continent, false, false, continent, true);
        
        ContinentalTotal continentalTotal = continentalService.getTotal(continent);
        
        model.addAttribute("continent", continent);
        model.addAttribute("continentTotalCases", continentalTotal.getCases());
        model.addAttribute("continentTotalDeaths", continentalTotal.getDeaths());
        
        model.addAttribute("countryTotals", countryTotalsByContinent);
        
        return "continent";
    }
    
}
