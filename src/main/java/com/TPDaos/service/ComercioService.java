package com.TPDaos.service;

import com.TPDaos.domain.Comercio;
import com.TPDaos.dto.ComercioDTO;

import java.util.List;

public interface ComercioService {

    public List<Comercio> obtenerComercios();

    public Comercio obtenerComercio(Long cuit);

    public Comercio guardarComercio(ComercioDTO comercioDTO);

    public Comercio actualizarComercio(Long cuit, ComercioDTO comercioDTO);

    public void eliminarComercio(Long cuit);
}
