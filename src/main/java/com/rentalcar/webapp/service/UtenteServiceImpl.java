package com.rentalcar.webapp.service;

import com.rentalcar.webapp.entity.Utente;
import com.rentalcar.webapp.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UtenteServiceImpl implements UtenteService{

    @Autowired
    private UtenteRepository utenteRepository;

    @Override
    public List<Utente> getAll()
    {
        return utenteRepository.findAll();
    }

    @Override
    public List<Utente> getAllCustomers() {
        return utenteRepository.getAllCustomers();
    }

    @Override
    public List<Utente> searchCustomers(String theSearchName) {
        return utenteRepository.searchCustomers(theSearchName);
    }

    /*@Override
    public List<Utente> getAllUtenti() {
        return utenteRepository.getAllUtenti();
    }

    @Override
    public Utente getCustomer(Long id) {
        return utenteRepository.getCustomer(id);
    }

    @Override
    public Utente findUserBySSO(String sso) {
        return utenteRepository.findUserBySSO(sso);
    }



    @Override
    public void save(Utente theCustomer) {
        utenteRepository.save(theCustomer);
    }

    @Override
    public void update(Utente theCustomer) {
        utenteRepository.update(theCustomer);
    }

    @Override
    public void delete(Long id) {
        utenteRepository.delete(id);
    }*/
}
