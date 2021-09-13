package com.rentalcar.webapp.service;

import com.rentalcar.webapp.entity.Automezzo;

import java.util.List;

public interface AutomezzoService {

    List<Automezzo> getAll();

    List<Automezzo> searchBy(String theSearchName);

}
