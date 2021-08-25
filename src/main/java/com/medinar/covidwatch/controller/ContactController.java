package com.medinar.covidwatch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Rommel Medina
 */
@Controller
public class ContactController {

    @GetMapping("/contact")
    public String getContact() {
        return "contact";
    }
}
