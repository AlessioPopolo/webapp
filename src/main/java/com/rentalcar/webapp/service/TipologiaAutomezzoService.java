package com.rentalcar.webapp.service;

import com.rentalcar.webapp.entity.TipologiaAutomezzo;

import java.util.List;

public interface TipologiaAutomezzoService {

    List<TipologiaAutomezzo> getAll();

    TipologiaAutomezzo getCategoria(Long id);
}
