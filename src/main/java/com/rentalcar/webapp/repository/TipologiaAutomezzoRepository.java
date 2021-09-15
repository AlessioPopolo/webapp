package com.rentalcar.webapp.repository;

import com.rentalcar.webapp.entity.TipologiaAutomezzo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TipologiaAutomezzoRepository extends JpaRepository<TipologiaAutomezzo, Long> {
    @Query
    Optional<TipologiaAutomezzo> findById(Long id);
}
