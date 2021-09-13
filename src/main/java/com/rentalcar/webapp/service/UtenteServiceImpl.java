package com.rentalcar.webapp.service;

import com.rentalcar.webapp.entity.Utente;
import com.rentalcar.webapp.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
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

    @Override
    public List<Utente> searchBy(String theSearchName){
        return utenteRepository.findByNomeContainingOrCognomeContainingOrSsoIdContainingOrRuolo_RuoloContaining(theSearchName, theSearchName, theSearchName, theSearchName);
    }

    @Override
    public List<Utente> searchByDateBetween(Date start, Date end) {
        Calendar c = Calendar.getInstance();
        c.setTime(end);
        c.add(Calendar.DATE, 1);
        end = c.getTime();
        return utenteRepository.findByDatadinascitaBetween(start, end);
    }

    @Override
    public Utente getUtente(Long id) {
        return utenteRepository.findById(id).orElse(null);
    }

    @Override
    public Utente getCustomer(Long id) {
        return utenteRepository.findById(id).orElse(null);
    }

    @Override
    public Utente findUserBySSO(String sso) {
        return utenteRepository.findBySsoIdLike(sso);
    }

    @Override
    @Transactional
    public void insertUtente(Utente utente) {
        utenteRepository.save(utente);
    }

    @Override
    @Transactional
    public void deleteUtente(Utente utente)
    {
        utenteRepository.delete(utente);
    }

}
