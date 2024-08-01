package com.TPDaos.dto;

public class ComercioDTO {
    private Long cuit;

    private String razonSocial;

    private String direccion;

    public ComercioDTO() {
    }

    public ComercioDTO(Long cuit, String razonSocial, String direccion) {
        this.cuit = cuit;
        this.razonSocial = razonSocial;
        this.direccion = direccion;
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
}
