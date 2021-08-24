package com.medinar.covidwatch.controller;

import com.medinar.covidwatch.exception.ContinentalCasesNotFoundException;
import com.medinar.covidwatch.exception.GlobalCasesNotFoundException;
import com.medinar.covidwatch.exception.InternationalCasesNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Rommel Medina
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String MSG = "message";

    @ExceptionHandler({
        GlobalCasesNotFoundException.class,
        ContinentalCasesNotFoundException.class,
        InternationalCasesNotFoundException.class,
        IOException.class
    })
    public ModelAndView handle(Exception ex) {

        var mv = new ModelAndView();
        mv.addObject(MSG, ex.getMessage());
        mv.setViewName("error/404");

        return mv;
    }

    @ExceptionHandler(InterruptedException.class)
    public ModelAndView handleInterruptedException(Exception ex) {

        var mv = new ModelAndView();
        mv.addObject(MSG, ex.getMessage());
        mv.setViewName("error/500");

        return mv;
    }

    @ExceptionHandler(ExecutionException.class)
    public ModelAndView handleExecutionException(Exception ex) {

        var mv = new ModelAndView();
        mv.addObject(MSG, ex.getMessage());
        mv.setViewName("error/500");

        return mv;
    }

}
