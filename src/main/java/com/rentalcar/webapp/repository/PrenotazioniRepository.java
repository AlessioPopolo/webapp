package com.rentalcar.webapp.repository;

import com.rentalcar.webapp.entity.Prenotazioni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PrenotazioniRepository extends JpaRepository<Prenotazioni, Long> {

    @Query(value = "select * from prenotazioni where utente_id = ?1", nativeQuery = true)
    List<Prenotazioni> findAllByUtente(Long id);

    @Query
    Optional<Prenotazioni> findById(Long id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE Prenotazioni SET approved = TRUE where id = ?1")
    void approve(Long id);

    @Query(value = "SELECT * FROM Prenotazioni WHERE automezzo_id = ?1 AND approved = TRUE AND (start_date BETWEEN ?2 AND ?3 OR end_date BETWEEN ?2 AND ?3 OR ?2 BETWEEN start_date AND end_date)", nativeQuery = true)
    List<Prenotazioni> checkAvailableVehicleInDatePrenotazione(/*Long automezzoId, */Long idAuto, Date start, Date end);

}
