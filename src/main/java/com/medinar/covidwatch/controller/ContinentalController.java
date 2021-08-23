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
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Rommel Medina
 */
@Controller
public class ContinentalController {

    @Autowired
    ContinentalService continentalService;

    @Autowired
    InternationalService internationalService;

    @GetMapping("/continental/{continent}")
    public String getContinentalTotal(
            Model model,
            @PathVariable String continent,
            @RequestParam(required = false) boolean yesterday,
            @RequestParam(required = false) boolean twoDaysAgo,
            @RequestParam(required = false) boolean strict,
            @RequestParam(required = false) boolean allowNull
    ) throws Exception {

        Optional<ContinentalTotal> continentalTotal = continentalService.getTotal(
                continent,
                yesterday,
                twoDaysAgo,
                strict,
                allowNull
        );

        if (continentalTotal.isPresent()) {
            List<InternationalTotal> internationalTotals = internationalService
                    .getTotalsbyContinent(
                            continent, 
                            yesterday, 
                            twoDaysAgo, 
                            strict, 
                            allowNull
                    );
            
            model.addAttribute("continent", continent);
            model.addAttribute("continentTotalCases", continentalTotal.get().getCases());
            model.addAttribute("continentTotalDeaths", continentalTotal.get().getDeaths());
            model.addAttribute("internationalTotals", internationalTotals);
        }
        else {
            model.addAttribute("continentTotalCases", 0);
            model.addAttribute("continentTotalDeaths", 0);
        }
        return "continental";
    }

}
