package com.TPDaos.service;

import com.TPDaos.domain.Patente;

import java.util.List;

public interface PatenteService {

    public List<Patente> getPatentes();

    public Patente getPatente(Long id);

    public Patente addPatente(Patente patente);

    public Patente updatePatente(Patente patente);

    public Patente deletePatente(Long id);
}
