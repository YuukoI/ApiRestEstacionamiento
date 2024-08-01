package com.TPDaos.service;

import com.TPDaos.dao.EstacionamientoRepository;
import com.TPDaos.dao.PatenteRepository;
import com.TPDaos.dao.UsuarioRepository;
import com.TPDaos.domain.Estacionamiento;
import com.TPDaos.domain.Patente;
import com.TPDaos.domain.Usuario;
import com.TPDaos.dto.EstacionamientoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstacionamientoServiceImp implements EstacionamientoService{

    @Autowired
    private EstacionamientoRepository estacionamientoRepository;

    @Autowired
    private PatenteRepository patenteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Estacionamiento> getEstacionamientos() {
        return estacionamientoRepository.findAll();
    }

    @Override
    public Estacionamiento getEstacionamientoById(Long id) {
        return estacionamientoRepository.findById(id).orElse(null);
    }

    @Override
    public Estacionamiento addEstacionamiento(EstacionamientoDTO estacionamientoDTO) {
        Estacionamiento estacionamientoExistente = estacionamientoRepository.findEstacionamientoByPatenteAndEstadoLibre(estacionamientoDTO.getNumeroPatente());

        Patente patenteExistente = patenteRepository.findByNumero(estacionamientoDTO.getNumeroPatente());

        Usuario usuarioExistente = usuarioRepository.findByContrasenia(estacionamientoDTO.getContraseniaUsuario());
        Usuario usuarioExistente2 = usuarioRepository.findById(estacionamientoDTO.getDniUsuario()).orElse(null);

        if(patenteExistente == null){
            throw new RuntimeException("Patente no encontrada.");
        }

        if (estacionamientoExistente != null) {
            throw new RuntimeException("El vehículo ya está estacionado");
        }

        if(usuarioExistente != usuarioExistente2){
            throw new RuntimeException("Usuario o contrasenia incorrecto.");
        }

        Estacionamiento e = new Estacionamiento();
        e.setPatente(patenteExistente);
        e.setEstado(true);
        e.setDniUsuario(estacionamientoDTO.getDniUsuario());
        e.setContraseniaUsuario(estacionamientoDTO.getContraseniaUsuario());

        return estacionamientoRepository.save(e);
    }

    @Override
    public Estacionamiento updateEstacionamiento(EstacionamientoDTO estacionamientoDTO) {
        Estacionamiento estacionamientoExistente = estacionamientoRepository.findEstacionamientoByPatenteAndEstadoOcupado(estacionamientoDTO.getNumeroPatente());

        Patente patenteExistente = patenteRepository.findByNumero(estacionamientoDTO.getNumeroPatente());

        if(patenteExistente == null){
            throw new RuntimeException("Patente no encontrada.");
        }

        if(estacionamientoExistente == null){
            throw new RuntimeException("No se encuentra el vehiculo.");
        }

        estacionamientoExistente.setEstado(estacionamientoDTO.getEstado());
        return estacionamientoRepository.save(estacionamientoExistente);
    }

    @Override
    public void deleteEstacionamiento(Long id) {
        estacionamientoRepository.deleteById(id);
    }
}
