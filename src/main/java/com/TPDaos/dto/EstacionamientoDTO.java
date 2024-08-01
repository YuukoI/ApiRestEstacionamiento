package com.TPDaos.dto;

public class EstacionamientoDTO {
    private String numeroPatente;
    private String dniUsuario;
    private String contraseniaUsuario;
    private Boolean estado;

    public EstacionamientoDTO() {
    }

    public EstacionamientoDTO(String numeroPatente, String dniUsuario, String contraseniaUsuario, Boolean estado) {
        this.numeroPatente = numeroPatente;
        this.dniUsuario = dniUsuario;
        this.contraseniaUsuario = contraseniaUsuario;
        this.estado = estado;
    }

    public String getNumeroPatente() {
        return numeroPatente;
    }

    public void setNumeroPatente(String numeroPatente) {
        this.numeroPatente = numeroPatente;
    }

    public String getDniUsuario() {
        return dniUsuario;
    }

    public void setDniUsuario(String dniUsuario) {
        this.dniUsuario = dniUsuario;
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
}
