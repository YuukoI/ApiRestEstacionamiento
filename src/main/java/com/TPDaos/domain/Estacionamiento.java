package com.TPDaos.domain;

import jakarta.persistence.*;

@Entity
public class Estacionamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dniUsuario;
    private String contraseniaUsuario;
    private Boolean estado;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patente_id", referencedColumnName = "id")
    private Patente patente;

    public Estacionamiento() {
    }

    public Estacionamiento(String dniUsuario, String contraseniaUsuario, Boolean estado, Patente patente) {
        this.dniUsuario = dniUsuario;
        this.contraseniaUsuario = contraseniaUsuario;
        this.estado = estado;
        this.patente = patente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContraseniaUsuario() {
        return contraseniaUsuario;
    }

    public void setContraseniaUsuario(String contraseniaUsuario) {
        this.contraseniaUsuario = contraseniaUsuario;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Patente getPatente() {
        return patente;
    }

    public void setPatente(Patente patente) {
        this.patente = patente;
    }

    public String getDniUsuario() {
        return dniUsuario;
    }

    public void setDniUsuario(String dniUsuario) {
        this.dniUsuario = dniUsuario;
    }
}
