package com.rentalcar.webapp.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "prenotazioni")
public class Prenotazioni {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "utenteId", nullable = false)
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "automezzoId", nullable = false)
    private Automezzo automezzo;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "startDate")
    private Date startdate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "endDate")
    private Date enddate;

    @Column(name = "approved")
    private boolean approved = false;

    public Prenotazioni() {
    }

    public Prenotazioni(Long id, Utente utente, Automezzo automezzo, Date startdate, Date enddate) {
        this.id = id;
        this.utente = utente;
        this.automezzo = automezzo;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public Prenotazioni(Utente utente, Automezzo automezzo, Date startdate, Date enddate) {
        this.utente = utente;
        this.automezzo = automezzo;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Automezzo getAutomezzo() {
        return automezzo;
    }

    public void setAutomezzo(Automezzo automezzo) {
        this.automezzo = automezzo;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
