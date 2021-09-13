package com.rentalcar.webapp.service;

import com.rentalcar.webapp.entity.Prenotazioni;

import java.util.Date;
import java.util.List;

public interface PrenotazioniService {

    List<Prenotazioni> getAll();

    List<Prenotazioni> getPrenotazioniByUser(Long id);

    Prenotazioni getPrenotazione(Long id);

    Prenotazioni insertPrenotazione(Prenotazioni prenotazione);

    void deletePrenotazione(Prenotazioni prenotazione);

    void approvePrenotazione(Long id);

    boolean checkEditableOrDeletableBeforeXDaysPrenotazione(Date start);

    boolean checkDataEndAfterDataStart(Prenotazioni prenotazione);

    /*
    boolean checkAvailableVehicleInDatePrenotazione(Prenotazioni prenotazione);

    boolean checkDataEndAfterDataStart(Prenotazioni prenotazione);*/ //TODO mettere questa funzione nella insert

}
