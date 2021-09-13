package com.rentalcar.webapp.repository;

import com.rentalcar.webapp.entity.Automezzo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AutomezzoRepository extends JpaRepository<Automezzo, Long> {

    @Query
    List<Automezzo> findByCasacostruttriceContainingOrModelloContainingOrTargaContainingOrCategoria_CategoriaContaining(String casacostruttrice, String modello, String targa, String categoria);

    @Query
    List<Automezzo> findByImmatricolazioneBetween(Date start, Date end);

    @Query
    Optional<Automezzo> findById(Long id);

    @Query
    List<Automezzo> findByCategoria_Categoria(String categoria);
}
