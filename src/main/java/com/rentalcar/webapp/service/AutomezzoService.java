package com.rentalcar.webapp.service;

import com.rentalcar.webapp.entity.Automezzo;

import java.util.Date;
import java.util.List;

public interface AutomezzoService {

    List<Automezzo> getAll();

    List<Automezzo> searchBy(String theSearchName);

    List<Automezzo> searchByDateBetween(Date start, Date end);

    Automezzo getAutomezzo(Long id);

    List<Automezzo> findAutoByCategoria(String categoria);

    void insertAutomezzo(Automezzo automezzo);
}
