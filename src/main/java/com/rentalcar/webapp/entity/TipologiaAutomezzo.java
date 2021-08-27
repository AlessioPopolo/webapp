package com.rentalcar.webapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "tipologia_automezzo")
public class TipologiaAutomezzo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "categoria")
    private String categoria;

    public TipologiaAutomezzo() {
    }

    public TipologiaAutomezzo(Long id, String categoria) {
        this.id = id;
        this.categoria = categoria;
    }

    public TipologiaAutomezzo(String categoria) {
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
