package com.medinar.covidwatch.controller;

import com.medinar.covidwatch.domain.ContinentalTotal;
import com.medinar.covidwatch.domain.InternationalTotal;
import com.medinar.covidwatch.exception.ContinentalCasesNotFoundException;
import com.medinar.covidwatch.exception.InternationalCasesNotFoundException;
import com.medinar.covidwatch.service.ContinentalService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.medinar.covidwatch.service.InternationalService;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Rommel Medina
 */
@Slf4j
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
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) boolean allowNull
    ) {

        try {
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
                                sortBy,
                                allowNull
                        );

                model.addAttribute("continent", continent);
                model.addAttribute("continentTotalCases", continentalTotal.get().getCases());
                model.addAttribute("continentTotalDeaths", continentalTotal.get().getDeaths());
                model.addAttribute("internationalTotals", internationalTotals);
            } else {
                model.addAttribute("continentTotalCases", 0);
                model.addAttribute("continentTotalDeaths", 0);
            }

        } catch (InterruptedException ex) {
            log.error("InterruptedException handler executed", ex);
            Thread.currentThread().interrupt();
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "InterruptedException occured",
                    ex
            );
        } catch (ExecutionException ex) {
            log.error("ExecutionException handler executed", ex);
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "ExecutionException occured",
                    ex
            );
        } catch (IOException ex) {
            log.error("IOException handler executed", ex);
            throw new ResponseStatusException(
                    NOT_FOUND,
                    "IOException occured",
                    ex
            );
        } catch (ContinentalCasesNotFoundException | InternationalCasesNotFoundException ex) {
            log.error("ContinentNotFoundException handler executed", ex);
            throw new ResponseStatusException(NOT_FOUND, continent, ex);
        }

        return "continental";
    }

}
