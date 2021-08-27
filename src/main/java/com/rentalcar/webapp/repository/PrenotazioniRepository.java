package com.rentalcar.webapp.repository;

import com.rentalcar.webapp.entity.Prenotazioni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrenotazioniRepository extends JpaRepository<Prenotazioni, Long> {

}
