package com.rentalcar.webapp.service;

import com.rentalcar.webapp.entity.Prenotazioni;
import com.rentalcar.webapp.entity.Utente;

import java.util.List;

public interface PrenotazioniService {

    List<Prenotazioni> getAll();

    List<Prenotazioni> getPrenotazioniByUser(Long id);

    Prenotazioni getPrenotazione(Long id);

    Prenotazioni insertPrenotazione(Prenotazioni prenotazione);

    /*void save(Prenotazioni prenotazione);

    void update(Prenotazioni prenotazione);

    void delete(Long id);

    void approve(Prenotazioni prenotazione);

    boolean checkEditableOrDeletableBeforeXDaysPrenotazione(Date start);

    boolean checkAvailableVehicleInDatePrenotazione(Prenotazioni prenotazione);

    boolean checkDataEndAfterDataStart(Prenotazioni prenotazione);*/

}
