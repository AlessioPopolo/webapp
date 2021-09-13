package com.rentalcar.webapp.service;

import com.rentalcar.webapp.entity.Prenotazioni;
import com.rentalcar.webapp.repository.PrenotazioniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
}
