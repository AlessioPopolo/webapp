package com.rentalcar.webapp.controller;

import com.rentalcar.webapp.entity.Prenotazioni;
import com.rentalcar.webapp.service.PrenotazioniService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/prenotazioni")
@CrossOrigin(origins = "http://localhost:4200")
public class PrenotazioniController {

    private static final Logger logger = LoggerFactory.getLogger(PrenotazioniController.class);

    @Autowired
    private PrenotazioniService prenotazioniService;

    @GetMapping(value = "/lista-prenotazioni")
    public ResponseEntity<List<Prenotazioni>> listPrenotazioni(){
        List<Prenotazioni> prenotazioni = prenotazioniService.getAll();

        return new ResponseEntity<>(prenotazioni, HttpStatus.OK);
    }
}
