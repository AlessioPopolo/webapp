package com.rentalcar.webapp.repository;

import com.rentalcar.webapp.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {

    @Query(value = "select * from utenti where ruolo = '1'", nativeQuery = true)
    List<Utente> getAllCustomers();

    @Query("select u from Utente u where u.nome like %?1% OR u.cognome like %?1% OR u.ssoId like %?1%")
    List<Utente> searchCustomers(String theSearchName);

    /*List<Utente> getAllUtenti();

    Utente getCustomer(Long id);

    void save(Utente theCustomer);

    void update(Utente theCustomer);

    void delete(Long id);

    Utente findUserBySSO(String sso);*/
}
