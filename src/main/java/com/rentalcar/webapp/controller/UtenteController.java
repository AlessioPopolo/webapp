package com.rentalcar.webapp.controller;

import com.rentalcar.webapp.entity.Utente;
import com.rentalcar.webapp.service.UtenteService;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/utente")
@CrossOrigin(origins = "http://localhost:4200")
public class UtenteController {

    private static final Logger logger = LoggerFactory.getLogger(UtenteController.class);

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

    @GetMapping(value = "/search/all/{searchName}")
    public ResponseEntity<List<Utente>> searchAll(@PathVariable("searchName") String SearchName){
        List<Utente> customers = utenteService.searchBy(SearchName);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping(value = "searchbydate/{startDate}+{endDate}")//if endDate = 27-08-2021 service add 1 day to catch also 27-08-2021 if not, it catch only since 26-08-2021
    public ResponseEntity<List<Utente>> searchByDateBetween(@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy")Date startDate, @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy")Date endDate){
        List<Utente> customers = utenteService.searchByDateBetween(startDate, endDate);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping(value = "/id={id}")
    public ResponseEntity<Utente> getUtente(@PathVariable("id") Long id) {
        Utente utente = utenteService.getCustomer(id);
        if (utente == null)
        {
            String ErrMsg = "Utente con id = " + id + " non trovato!";

            logger.warn(ErrMsg);

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(utente, HttpStatus.OK);
    }

    @GetMapping(value = "/ssoid={ssoid}")
    public ResponseEntity<Utente> getUtenteBySso(@PathVariable("ssoid") String ssoid) throws NotFoundException {
        Utente utente = utenteService.findUserBySSO(ssoid);
        if (utente == null)
        {
            String ErrMsg = "Utente con ssoid = " + ssoid + " non trovato!";

            logger.warn(ErrMsg);

            throw new NotFoundException(ErrMsg);

        }
        return new ResponseEntity<>(utente, HttpStatus.OK);
    }
}
