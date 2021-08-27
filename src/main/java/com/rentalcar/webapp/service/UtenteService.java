package com.rentalcar.webapp.service;

import com.rentalcar.webapp.entity.Utente;

import java.util.Date;
import java.util.List;

public interface UtenteService {

    List<Utente> getAll();

    List<Utente> getAllCustomers();

    List<Utente> searchCustomers(String theSearchName);

    List<Utente> searchBy(String theSearchName);

    List<Utente> searchByDateBetween(Date start, Date end);

    Utente getUtente(Long id);

    Utente getCustomer(Long id);

    Utente findUserBySSO(String sso);

    void insertUtente(Utente utente);

    void deleteUtente(Utente utente);

    /*void save(Utente theCustomer);

    void update(Utente theCustomer);

    void delete(Long id);*/
}
