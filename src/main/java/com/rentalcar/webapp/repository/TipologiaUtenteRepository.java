package com.rentalcar.webapp.repository;

import com.rentalcar.webapp.entity.TipologiaUtente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipologiaUtenteRepository extends JpaRepository<TipologiaUtente, Long> {

    @Query
    Optional<TipologiaUtente> findById(Long id);
}
