package com.rentalcar.webapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rentalcar.webapp.dtos.InfoMsg;
import com.rentalcar.webapp.entity.Prenotazioni;
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
    public ResponseEntity<Prenotazioni> insertPrenotazione(@RequestBody Prenotazioni prenotazione) throws Exception {
        logger.info("Salviamo la prenotazione con id " + prenotazione.getId());

        if (!prenotazioniService.checkDataEndAfterDataStart(prenotazione)){
            String ErrMsg = "Non è possibile inserire una prenotazione con data inizio posteriore a data fine o anteriore a oggi";

            logger.warn(ErrMsg);

            throw new Exception(ErrMsg);
        }

        if (prenotazioniService.checkAvailableVehicleInDatePrenotazione(prenotazione).size() > 0){
            String ErrMsg = "Non è possibile inserire perchè veicolo in uso in quelle date";

            logger.warn(ErrMsg);

            throw new Exception(ErrMsg);
        }

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseNode = mapper.createObjectNode();

        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message", String.format("Inserimento Prenotazione %s Eseguito Con Successo", prenotazione.getId()));

        return ResponseEntity.ok(prenotazioniService.insertPrenotazione(prenotazione));
    }

    // ------------------- MODIFICA PRENOTAZIONE ------------------------------------
    @RequestMapping(value = "/modifica", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePrenotazione(@RequestBody Prenotazioni prenotazione) throws Exception {
        logger.info("Modifichiamo la prenotazione con id " + prenotazione.getId());
        Prenotazioni check = prenotazioniService.getPrenotazione(prenotazione.getId());
        if (check == null){
            String ErrMsg = "Prenotazione con id = " + prenotazione.getId() + " non trovata!";

            logger.warn(ErrMsg);

            throw new NotFoundException(ErrMsg);
        }
        if (!prenotazioniService.checkEditableOrDeletableBeforeXDaysPrenotazione(check.getStartdate())){
            String ErrMsg = "Non è possibile modificare una prenotazione meno di 2 giorni dall'inizio della stessa";

            logger.warn(ErrMsg);

            throw new Exception(ErrMsg);
        }

        List<Prenotazioni> checkVehicle = prenotazioniService.checkAvailableVehicleInDatePrenotazione(prenotazione);

        if (checkVehicle.size() > 1){
            String ErrMsg = "Non è possibile inserire perchè veicolo in uso in quelle date";

            logger.warn(ErrMsg);

            throw new Exception(ErrMsg);
        }
        else if (checkVehicle.size() == 1){
            Prenotazioni p = checkVehicle.get(0);
            if (p.getId() != prenotazione.getId()){
                String ErrMsg = "Non è possibile inserire perchè veicolo in uso in quelle date";

                logger.warn(ErrMsg);

                throw new Exception(ErrMsg);
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseNode = mapper.createObjectNode();

        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message", "Modifica Prenotazione " + prenotazione.getId() + " Eseguita Con Successo");

        return ResponseEntity.ok(prenotazioniService.insertPrenotazione(prenotazione));
    }

    // ------------------- ELIMINAZIONE PRENOTAZIONE ------------------------------------
    @RequestMapping(value = "/elimina/{id}", method = RequestMethod.DELETE, produces = "application/json" )
    public ResponseEntity<?> deletePrenotazione(@PathVariable("id") Long id)
            throws Exception
    {
        logger.info("Eliminiamo la prenotazione con id " + id);

        Prenotazioni prenotazione = prenotazioniService.getPrenotazione(id);

        if (prenotazione == null)
        {
            String MsgErr = String.format("Prenotazione %s non presente nel DB! ",id);

            logger.warn(MsgErr);

            throw new NotFoundException(MsgErr);
        }
        if (!prenotazioniService.checkEditableOrDeletableBeforeXDaysPrenotazione(prenotazione.getStartdate())){
            String ErrMsg = "Non è possibile eliminare una prenotazione meno di 2 giorni dall'inizio della stessa";

            logger.warn(ErrMsg);

            throw new Exception(ErrMsg);
        }

        prenotazioniService.deletePrenotazione(prenotazione);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseNode = mapper.createObjectNode();

        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message", "Eliminazione Prenotazione " + id + " Eseguita Con Successo");

        return new ResponseEntity<>(responseNode, new HttpHeaders(), HttpStatus.OK);

    }

    // ------------------- APPROVA PRENOTAZIONE ------------------------------------
    @RequestMapping(value = "/approva/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<List<Prenotazioni>> approvePrenotazione(@PathVariable("id") Long id) throws Exception {
        logger.info("Approviamo la prenotazione con id " + id);
        Prenotazioni prenotazione = prenotazioniService.getPrenotazione(id);
        if (prenotazione == null){
            String MsgErr = String.format("Prenotazione %s non presente nel DB! ",id);

            logger.warn(MsgErr);

            throw new NotFoundException(MsgErr);
        }

        if (prenotazioniService.checkAvailableVehicleInDatePrenotazione(prenotazione).size() > 0){
            String ErrMsg = "Non è possibile inserire perchè veicolo in uso in quelle date";

            logger.warn(ErrMsg);

            throw new Exception(ErrMsg);
        }

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseNode = mapper.createObjectNode();

        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message", "Approvazione Prenotazione " + id + " Eseguita Con Successo");

        return ResponseEntity.ok(prenotazioniService.approvePrenotazione(id));
    }
}
