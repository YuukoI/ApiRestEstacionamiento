package com.TPDaos.service;

import com.TPDaos.domain.Estacionamiento;
import com.TPDaos.dto.EstacionamientoDTO;

import java.util.List;

public interface EstacionamientoService {

    public List<Estacionamiento> getEstacionamientos();

    public Estacionamiento getEstacionamientoById(Long id);

    public Estacionamiento addEstacionamiento(EstacionamientoDTO estacionamientoDTO);

    public Estacionamiento updateEstacionamiento(EstacionamientoDTO estacionamientoDTO);

    public void deleteEstacionamiento(Long id);

}
