package com.rentalcar.webapp.service;

import com.rentalcar.webapp.entity.Automezzo;
import com.rentalcar.webapp.entity.Utente;
import com.rentalcar.webapp.repository.AutomezzoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AutomezzoServiceImpl implements AutomezzoService{

    @Autowired
    private AutomezzoRepository automezzoRepository;

    @Override
    public List<Automezzo> getAll()
    {
        return automezzoRepository.findAll();
    }

    @Override
    public List<Automezzo> searchBy(String theSearchName){
        return automezzoRepository.findByCasacostruttriceContainingOrModelloContainingOrTargaContainingOrCategoria_CategoriaContaining(theSearchName, theSearchName, theSearchName, theSearchName);
    }

    @Override
    public List<Automezzo> searchByDateBetween(Date start, Date end) {
        Calendar c = Calendar.getInstance();
        c.setTime(end);
        c.add(Calendar.DATE, 1);
        end = c.getTime();
        return automezzoRepository.findByImmatricolazioneBetween(start, end);
    }

    @Override
    public Automezzo getAutomezzo(Long id) {
        return automezzoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Automezzo> findAutoByCategoria(String categoria) {
        return automezzoRepository.findByCategoria_Categoria(categoria);
    }

    @Override
    @Transactional
    public void insertAutomezzo(Automezzo automezzo) {
        automezzoRepository.save(automezzo);
    }

}