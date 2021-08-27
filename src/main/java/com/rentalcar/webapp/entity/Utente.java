package com.rentalcar.webapp.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "utenti")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name="SSO_ID", unique=true)
    private String ssoId;

    @Column(name = "password")
    private String password;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "datadinascita")
    private Date datadinascita;

    @ManyToOne
    @JoinColumn(name = "ruolo")
    private TipologiaUtente ruolo;

    @OneToMany(mappedBy = "utente")
    private Set<Prenotazioni> prenotazioni;

    public Utente(){}

    public Utente(Long id, String ssoId, String password, String nome, String cognome, Date datadinascita, TipologiaUtente ruolo) {
        this.id = id;
        this.ssoId = ssoId;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.datadinascita = datadinascita;
        this.ruolo = ruolo;
    }

    public Utente(String ssoId, String password, String nome, String cognome, Date datadinascita, TipologiaUtente ruolo) {
        this.ssoId = ssoId;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.datadinascita = datadinascita;
        this.ruolo = ruolo;
    }

    public Utente(Long id, String nome, String cognome, Date datadinascita, TipologiaUtente ruolo) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.datadinascita = datadinascita;
        this.ruolo = ruolo;
    }

    public Long  getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSsoId() {
        return ssoId;
    }

    public void setSsoId(String ssoId) {
        this.ssoId = ssoId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Date getDatadinascita() {
        return datadinascita;
    }

    public void setDatadinascita(Date datadinascita) {
        this.datadinascita = datadinascita;
    }

    public TipologiaUtente getRuolo() {
        return ruolo;
    }

    public void setRuolo(TipologiaUtente ruolo) {
        this.ruolo = ruolo;
    }

    public Set<Prenotazioni> getPrenotazioni() {
        return prenotazioni;
    }

    public void setPrenotazioni(Set<Prenotazioni> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }
    @Override
    public String toString() {
        return "Utente [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", data di nascita=" + this.getDatadinascita() + ", ruolo=" + this.getRuolo() + "]";
    }
}
