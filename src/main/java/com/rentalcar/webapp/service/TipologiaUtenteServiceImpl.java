package com.rentalcar.webapp.service;

import com.rentalcar.webapp.entity.TipologiaUtente;
import com.rentalcar.webapp.repository.TipologiaUtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TipologiaUtenteServiceImpl implements TipologiaUtenteService {

    @Autowired
    private TipologiaUtenteRepository tipologiaUtenteRepository;

    @Override
    public List<TipologiaUtente> getAll() {
        return tipologiaUtenteRepository.findAll();
    }

    @Override
    public TipologiaUtente getRuolo(Long id)  {
        return tipologiaUtenteRepository.findById(id).orElse(null);
    }
}
