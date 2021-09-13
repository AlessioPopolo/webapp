package com.rentalcar.webapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rentalcar.webapp.dtos.InfoMsg;
import com.rentalcar.webapp.entity.Prenotazioni;
import com.rentalcar.webapp.entity.Utente;
import com.rentalcar.webapp.service.PrenotazioniService;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/utente={id}")
    public ResponseEntity<List<Prenotazioni>> getPrenotazioniByUtente(@PathVariable("id") Long id) {
        List<Prenotazioni> prenotazioni = prenotazioniService.getPrenotazioniByUser(id);
        if (prenotazioni == null)
        {
            String ErrMsg = "Prenotazioni per l'utente con id = " + id + " non trovate!";

            logger.warn(ErrMsg);

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(prenotazioni, HttpStatus.OK);
    }

    @GetMapping(value = "/id={id}")
    public ResponseEntity<Prenotazioni> getPrenotazione(@PathVariable("id") Long id) {
        Prenotazioni prenotazione = prenotazioniService.getPrenotazione(id);
        if (prenotazione == null)
        {
            String ErrMsg = "Prenotazione con id = " + id + " non trovata!";

            logger.warn(ErrMsg);

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(prenotazione, HttpStatus.OK);
    }

    // ------------------- INSERIMENTO PRENOTAZIONE ------------------------------------
    @PostMapping(value = "/inserisci")
    public ResponseEntity<Prenotazioni> insertPrenotazione(@RequestBody Prenotazioni prenotazione) {
        logger.info("Salviamo la prenotazione con id " + prenotazione.getId());



        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseNode = mapper.createObjectNode();

        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message", String.format("Inserimento Prenotazione %s Eseguito Con Successo", prenotazione.getId()));

        return ResponseEntity.ok(prenotazioniService.insertPrenotazione(prenotazione));
    }

    // ------------------- MODIFICA PRENOTAZIONE ------------------------------------
    @RequestMapping(value = "/modifica", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePrenotazione(@RequestBody Prenotazioni prenotazione) throws NotFoundException {
        logger.info("Modifichiamo la prenotazione con id " + prenotazione.getId());
        Prenotazioni check = prenotazioniService.getPrenotazione(prenotazione.getId());
        if (check == null){
            String ErrMsg = "Prenotazione con id = " + prenotazione.getId() + " non trovata!";

            logger.warn(ErrMsg);

            throw new NotFoundException(ErrMsg);
        }
        prenotazioniService.insertPrenotazione(prenotazione);

        String code = HttpStatus.OK.toString();
        String message = String.format("Modifica prenotazione %s Eseguita Con Successo", prenotazione.getId());

        return new ResponseEntity<>(new InfoMsg(code, message), HttpStatus.CREATED);
    }
}
