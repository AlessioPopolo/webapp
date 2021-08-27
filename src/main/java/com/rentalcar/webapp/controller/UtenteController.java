package com.rentalcar.webapp.controller;

import com.rentalcar.webapp.entity.Utente;
import com.rentalcar.webapp.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/utente")
@CrossOrigin(origins = "http://localhost:4200")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping(value = "/lista-utenti")
    public ResponseEntity<List<Utente>> listUtenti(){
        List<Utente> customers = utenteService.getAll();

        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping(value = "/lista-customers")
    public ResponseEntity<List<Utente>> listCustomers(){
        List<Utente> customers = utenteService.getAllCustomers();

        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping(value = "/search/{searchName}")
    public ResponseEntity<List<Utente>> searchCustomers(@PathVariable("searchName") String SearchName){
        List<Utente> customers = utenteService.searchCustomers(SearchName);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

}
