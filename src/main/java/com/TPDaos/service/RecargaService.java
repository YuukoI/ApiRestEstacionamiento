package com.TPDaos.service;

import com.TPDaos.domain.Recarga;
import com.TPDaos.dto.RecargaDTO;

import java.util.List;

public interface RecargaService {

    public List<Recarga> getRecargas();

    public Recarga getRecarga(Long id);

    public Recarga addRecarga(RecargaDTO recargaDTO);

    public Recarga updateRecarga(RecargaDTO recargaDTO);

    public Recarga deleteRecarga(Long id);

    public List<Recarga> buscarRecargaPorDni(String dni);

    public List<Recarga> buscarRecargaPorNumeroPatente(String numeroPatente);

    public List<Recarga> buscarRecargaPorNumeroComercio(Long numeroComercio);
}
