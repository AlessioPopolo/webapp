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
}
