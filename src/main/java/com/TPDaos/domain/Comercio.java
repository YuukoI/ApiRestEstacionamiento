package com.TPDaos.domain;

import com.TPDaos.enums.Estado;
import jakarta.persistence.*;

@Entity
public class Comercio {

    @Id
    private Long cuit;

    private String razonSocial;

    private String direccion;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    public Comercio() {
    }

    public Comercio(Long cuit, String razonSocial, String direccion, Estado estado) {
        this.cuit = cuit;
        this.razonSocial = razonSocial;
        this.direccion = direccion;
        this.estado = estado;
    }

    public Long getCuit() {
        return cuit;
    }

    public void setCuit(Long cuit) {
        this.cuit = cuit;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
