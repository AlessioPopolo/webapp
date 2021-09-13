package com.rentalcar.webapp.repository;

import com.rentalcar.webapp.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {

    @Query(value = "select * from utenti where ruolo = '1'", nativeQuery = true)
    List<Utente> getAllCustomers();

    @Query("select u from Utente u where u.nome like %?1% OR u.cognome like %?1% OR u.ssoId like %?1% OR u.ruolo.ruolo like %?1%")
    List<Utente> searchCustomers(String theSearchName);

    @Query
    List<Utente> findByNomeContainingOrCognomeContainingOrSsoIdContainingOrRuolo_RuoloContaining(String nome, String cognome, String ssoid, String ruolo);

    @Query
    List<Utente> findByDatadinascitaBetween(Date start, Date end);

    @Query
    Optional<Utente> findById(Long id);

    @Query
    Utente findBySsoIdLike(String ssoid);

}
