package com.rentalcar.webapp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class RentalCarRestController {
    private String utenti;

    @GetMapping(value = "/test")
    public String getGreetings(){
        return "\"Ciao Babbo\"";
    }
    @GetMapping(value = "/utenti")
    public String getUtenti(){
        utenti = "pippo";
        return utenti;
    }
}
