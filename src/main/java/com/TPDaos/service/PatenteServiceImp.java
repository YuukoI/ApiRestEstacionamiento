package com.TPDaos.service;

import com.TPDaos.dao.PatenteRepository;
import com.TPDaos.domain.Patente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatenteServiceImp implements PatenteService{

    @Autowired
    private PatenteRepository patenteRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Patente> getPatentes() {
        return patenteRepository.findAll();
    }

    @Override
    public Patente getPatente(Long id) {
        return patenteRepository.findById(id).orElse(null);
    }

    @Override
    public Patente addPatente(Patente patente) {
        return null;
    }

    @Override
    public Patente updatePatente(Patente patente) {
        return null;
    }

    @Override
    public Patente deletePatente(Long id) {
        return null;
    }

}
