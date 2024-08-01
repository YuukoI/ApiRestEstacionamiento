package com.TPDaos.dto;

public class RecargaDTO {


    private String dniUsuario;
    private String numeroPatente;
    private Long numeroComercio;
    private double precio;

    public RecargaDTO() {
    }

    public RecargaDTO(String dniUsuario, String numeroPatente, Long numeroComercio, double precio) {
        this.dniUsuario = dniUsuario;
        this.numeroPatente = numeroPatente;
        this.numeroComercio = numeroComercio;
        this.precio = precio;
    }

    public String getDniUsuario() {
        return dniUsuario;
    }

    public void setDniUsuario(String dniUsuario) {
        this.dniUsuario = dniUsuario;
    }

    public String getNumeroPatente() {
        return numeroPatente;
    }

    public void setNumeroPatente(String numeroPatente) {
        this.numeroPatente = numeroPatente;
    }

    public Long getNumeroComercio() {
        return numeroComercio;
    }

    public void setNumeroComercio(Long numeroComercio) {
        this.numeroComercio = numeroComercio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
