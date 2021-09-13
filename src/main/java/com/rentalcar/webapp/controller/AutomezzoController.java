package com.rentalcar.webapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rentalcar.webapp.dtos.InfoMsg;
import com.rentalcar.webapp.entity.Automezzo;
import com.rentalcar.webapp.entity.TipologiaAutomezzo;
import com.rentalcar.webapp.entity.Utente;
import com.rentalcar.webapp.service.AutomezzoService;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @GetMapping(value = "searchbydate/{startDate}+{endDate}")//if endDate = 27-08-2021 service add 1 day to catch also 27-08-2021 if not, it catch only since 26-08-2021
    public ResponseEntity<List<Automezzo>> searchByDateBetween(@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") Date startDate, @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy")Date endDate){
        List<Automezzo> automezzi = automezzoService.searchByDateBetween(startDate, endDate);
        return new ResponseEntity<>(automezzi, HttpStatus.OK);
    }

    @GetMapping(value = "/id={id}")
    public ResponseEntity<Automezzo> getAutomezzo(@PathVariable("id") Long id) {
        Automezzo automezzo = automezzoService.getAutomezzo(id);
        if (automezzo == null)
        {
            String ErrMsg = "Automezzo con id = " + id + " non trovato!";

            logger.warn(ErrMsg);

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(automezzo, HttpStatus.OK);
    }

    @GetMapping(value = "/categoria={categoria}")
    public ResponseEntity<List<Automezzo>> getAutomezzoByCategoria(@PathVariable("categoria") String categoria) {
        List<Automezzo> automezzi = automezzoService.findAutoByCategoria(categoria);
        return new ResponseEntity<>(automezzi, HttpStatus.OK);
    }

    // ------------------- INSERIMENTO AUTOMEZZO ------------------------------------
    @PostMapping(value = "/inserisci")
    public ResponseEntity<?> insertAutomezzo(@RequestBody Automezzo automezzo) {
        logger.info("Salviamo l'automezzo con id " + automezzo.getId());

        automezzoService.insertAutomezzo(automezzo);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseNode = mapper.createObjectNode();

        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message", String.format("Inserimento Automezzo %s Eseguito Con Successo", automezzo.getId()));

        return new ResponseEntity<>(responseNode, new HttpHeaders(), HttpStatus.CREATED);
    }

    // ------------------- MODIFICA AUTOMEZZO ------------------------------------
    @RequestMapping(value = "/modifica", method = RequestMethod.PUT)
    public ResponseEntity<?> updateAutomezzo(@RequestBody Automezzo automezzo) throws NotFoundException {
        logger.info("Modifichiamo l'automezzo con id " + automezzo.getId());
        Automezzo check = automezzoService.getAutomezzo(automezzo.getId());
        if (check == null){
            String ErrMsg = "Automezzo con id = " + automezzo.getId() + " non trovato!";

            logger.warn(ErrMsg);

            throw new NotFoundException(ErrMsg);
        }
        automezzoService.insertAutomezzo(automezzo);

        String code = HttpStatus.OK.toString();
        String message = String.format("Modifica automezzo %s Eseguita Con Successo", automezzo.getId());

        return new ResponseEntity<>(new InfoMsg(code, message), HttpStatus.CREATED);
    }
    // ------------------- ELIMINAZIONE AUTOMEZZO ------------------------------------
    @RequestMapping(value = "/elimina/{id}", method = RequestMethod.DELETE, produces = "application/json" )
    public ResponseEntity<?> deleteAutomezzo(@PathVariable("id") Long id)
            throws NotFoundException
    {
        logger.info("Eliminiamo l'automezzo con id " + id);

        Automezzo automezzo = automezzoService.getAutomezzo(id);

        if (automezzo == null)
        {
            String MsgErr = String.format("Automezzo %s non presente nel DB! ",id);

            logger.warn(MsgErr);

            throw new NotFoundException(MsgErr);
        }

        automezzoService.deleteAutomezzo(automezzo);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseNode = mapper.createObjectNode();

        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message", "Eliminazione Automezzo " + id + " Eseguita Con Successo");

        return new ResponseEntity<>(responseNode, new HttpHeaders(), HttpStatus.OK);

    }
}
