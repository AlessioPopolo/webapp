package com.rentalcar.webapp.service;

import com.rentalcar.webapp.entity.TipologiaAutomezzo;
import com.rentalcar.webapp.repository.TipologiaAutomezzoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TipologiaAutomezzoServiceImpl implements TipologiaAutomezzoService {

    @Autowired
    private TipologiaAutomezzoRepository tipologiaAutomezzoRepository;

    @Override
    public List<TipologiaAutomezzo> getAll() {
        return tipologiaAutomezzoRepository.findAll();
    }

    @Override
    public TipologiaAutomezzo getCategoria(Long id)  {
        return tipologiaAutomezzoRepository.findById(id).orElse(null);
    }

}
