package com.medinar.covidwatch.controller;

import com.medinar.covidwatch.domain.ContinentalTotal;
import com.medinar.covidwatch.domain.InternationalTotal;
import com.medinar.covidwatch.service.ContinentalService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.medinar.covidwatch.service.InternationalService;

/**
 *
 * @author Rommel Medina
 */
@Controller
public class ContinentalController {

    @Autowired
    ContinentalService continentalService;
    
    @Autowired
    InternationalService countryService;
    
    @GetMapping("/continental/{continent}")
    public String getContinentalTotal(
            Model model,
            @PathVariable String continent
    ) throws Exception {
        List<InternationalTotal> internationalTotals = countryService
                .getTotalsbyContinent(continent, false, false, "cases", true);
        
        ContinentalTotal continentalTotal = continentalService.getTotal(continent);
        
        model.addAttribute("continent", continent);
        model.addAttribute("continentTotalCases", continentalTotal.getCases());
        model.addAttribute("continentTotalDeaths", continentalTotal.getDeaths());
        
        model.addAttribute("internationalTotals", internationalTotals);
        
        return "continental";
    }
    
}
