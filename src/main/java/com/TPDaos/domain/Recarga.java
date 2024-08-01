package com.TPDaos.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Recarga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_dni")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "comercio_cuit")
    private Comercio comercio;

    @ManyToOne
    @JoinColumn(name = "patente_id")
    private Patente patente;

    private Double importe;

    public Recarga() {
    }

    public Recarga(Usuario usuario, Comercio comercio, Patente patente, Double importe) {
        this.usuario = usuario;
        this.comercio = comercio;
        this.patente = patente;
        this.importe = importe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Comercio getComercio() {
        return comercio;
    }

    public void setComercio(Comercio comercio) {
        this.comercio = comercio;
    }

    public Patente getPatente() {
        return patente;
    }

    public void setPatente(Patente patente) {
        this.patente = patente;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }
}

