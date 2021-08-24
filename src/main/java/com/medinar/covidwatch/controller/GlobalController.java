package com.medinar.covidwatch.controller;

import com.medinar.covidwatch.domain.GlobalTotal;
import com.medinar.covidwatch.exception.GlobalCasesNotFoundException;
import com.medinar.covidwatch.service.GlobalService;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Rommel Medina
 */
@Slf4j
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
    ) {

        try {
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
        } 
        catch (GlobalCasesNotFoundException ex) {
            log.error("GlobalCasesNotFoundException handler executed", ex);
            throw new ResponseStatusException(NOT_FOUND, ex.getMessage(), ex);
        }

        return "index";
    }

}
