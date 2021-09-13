package com.rentalcar.webapp.service;

import com.rentalcar.webapp.entity.Automezzo;
import com.rentalcar.webapp.repository.AutomezzoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

}
/*findByNomeContainingOrCognomeContainingOrSsoIdContainingOrRuolo_RuoloContaining*/