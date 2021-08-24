package com.medinar.covidwatch.controller;

import com.medinar.covidwatch.exception.ContinentalCasesNotFoundException;
import com.medinar.covidwatch.exception.GlobalCasesNotFoundException;
import com.medinar.covidwatch.exception.InternationalCasesNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Rommel Medina
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            GlobalCasesNotFoundException.class,
            ContinentalCasesNotFoundException.class,
            InternationalCasesNotFoundException.class
    })
    public ModelAndView handle(Exception ex) {

        var mv = new ModelAndView();
        mv.addObject("message", ex.getMessage());
        mv.setViewName("404");

        return mv;
    }
}
