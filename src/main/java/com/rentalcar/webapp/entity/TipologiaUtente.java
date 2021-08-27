package com.rentalcar.webapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "tipologia_utente")
public class TipologiaUtente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ruolo")
    private String ruolo;

    public TipologiaUtente() {
    }

    public TipologiaUtente(Long id) {
        this.id = id;
    }

    public TipologiaUtente(Long id, String ruolo) {
        this.id = id;
        this.ruolo = ruolo;
    }

    public TipologiaUtente(String ruolo) {
        this.ruolo = ruolo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

}

