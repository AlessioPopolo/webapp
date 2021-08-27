package com.rentalcar.webapp.service;

import com.rentalcar.webapp.entity.Utente;

import java.util.List;

public interface UtenteService {

    public List<Utente> getAll();

    public List<Utente> getAllCustomers();

    List<Utente> searchCustomers(String theSearchName);

    /*Utente getCustomer(Long id);

    Utente findUserBySSO(String sso);


    void save(Utente theCustomer);

    void update(Utente theCustomer);

    void delete(Long id);*/
}
