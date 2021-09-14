package com.rentalcar.webapp.service;

import com.rentalcar.webapp.entity.Prenotazioni;
import com.rentalcar.webapp.repository.PrenotazioniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PrenotazioniServiceImpl implements PrenotazioniService{

    @Autowired
    private PrenotazioniRepository prenotazioniRepository;

    @Override
    public List<Prenotazioni> getAll()
    {
        return prenotazioniRepository.findAll();
    }

    @Override
    public List<Prenotazioni> getPrenotazioniByUser(Long id) {
        return prenotazioniRepository.findAllByUtente(id);
    }

    @Override
    public Prenotazioni getPrenotazione(Long id) {
        return prenotazioniRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Prenotazioni insertPrenotazione(Prenotazioni prenotazione) {
        return prenotazioniRepository.save(prenotazione);
    }

    @Override
    @Transactional
    public void deletePrenotazione(Prenotazioni prenotazione) {
        prenotazioniRepository.delete(prenotazione);
    }

    @Override
    @Transactional
    public void approvePrenotazione(Long id) {
        prenotazioniRepository.approve(id);
    }

    @Override
    public boolean checkEditableOrDeletableBeforeXDaysPrenotazione(Date start) {
        LocalDateTime ldt = LocalDateTime.ofInstant(start.toInstant(), ZoneId.systemDefault()).minusDays(2);
        return ldt.isAfter(LocalDateTime.now());
    }

    @Override
    public boolean checkDataEndAfterDataStart(Prenotazioni prenotazione) {
        LocalDateTime ldtStart = LocalDateTime.ofInstant(prenotazione.getStartdate().toInstant(), ZoneId.systemDefault());
        LocalDateTime ldtEnd = LocalDateTime.ofInstant(prenotazione.getEnddate().toInstant(), ZoneId.systemDefault());
        return ldtEnd.isAfter(ldtStart) && ldtStart.isAfter(LocalDateTime.now());
    }

    @Override
    public Long checkAvailableVehicleInDatePrenotazione(Prenotazioni prenotazione) {
        return prenotazioniRepository.checkAvailableVehicleInDatePrenotazione(/*prenotazione.getId(), */prenotazione.getAutomezzo().getId(), prenotazione.getStartdate(), prenotazione.getEnddate());
    }
}
