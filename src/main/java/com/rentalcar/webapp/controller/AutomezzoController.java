package com.rentalcar.webapp.controller;

import com.rentalcar.webapp.entity.Automezzo;
import com.rentalcar.webapp.service.AutomezzoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/automezzo")
@CrossOrigin(origins = "http://localhost:4200")
public class AutomezzoController {

    private static final Logger logger = LoggerFactory.getLogger(AutomezzoController.class);

    @Autowired
    private AutomezzoService automezzoService;

    @GetMapping(value = "/lista-auto")
    public ResponseEntity<List<Automezzo>> listAutomezzi(){
        List<Automezzo> automezzi = automezzoService.getAll();

        return new ResponseEntity<>(automezzi, HttpStatus.OK);
    }

    @GetMapping(value = "/search/all/{searchName}")
    public ResponseEntity<List<Automezzo>> searchAll(@PathVariable("searchName") String SearchName){
        List<Automezzo> automezzi = automezzoService.searchBy(SearchName);
        return new ResponseEntity<>(automezzi, HttpStatus.OK);
    }

}
