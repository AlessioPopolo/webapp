package com.rentalcar.webapp.repository;

import com.rentalcar.webapp.entity.Automezzo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutomezzoRepository extends JpaRepository<Automezzo, Long> {
}
