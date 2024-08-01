package com.TPDaos.service;

import com.TPDaos.dao.ComercioRepository;
import com.TPDaos.domain.Comercio;
import com.TPDaos.dto.ComercioDTO;
import com.TPDaos.enums.Estado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComercioServiceImp implements ComercioService{

    @Autowired
    private ComercioRepository comercioRepository;

    @Override
    public List<Comercio> obtenerComercios() {
        return comercioRepository.findAll();
    }

    @Override
    public Comercio obtenerComercio(Long cuit) {
        return comercioRepository.findById(cuit).orElse(null);
    }

    @Override
    public Comercio guardarComercio(ComercioDTO comercioDTO) {
        Comercio comercioExistente = comercioRepository.findById(comercioDTO.getCuit()).orElse(null);

        if (comercioExistente != null) {
            throw new RuntimeException("Ya hay un comercio con ese Cuit.");
        }

        Comercio comercio = new Comercio();
        comercio.setCuit(comercioDTO.getCuit());
        comercio.setDireccion(comercioDTO.getDireccion());
        comercio.setRazonSocial(comercioDTO.getRazonSocial());
        comercio.setEstado(Estado.AUTORIZADO);

        return comercioRepository.save(comercio);
    }

    @Override
    public Comercio actualizarComercio(Long cuit, ComercioDTO comercioDTO) {
        Comercio comercioExistente = comercioRepository.findById(cuit).orElse(null);

        if(cuit != comercioDTO.getCuit()){
            throw new RuntimeException("Usted no puede cambiar el cuit del comercio.");
        }

        if (comercioExistente == null) {
            throw new RuntimeException("No existe un comercio con el cuit ingresado.");
        }

        comercioExistente.setRazonSocial(comercioDTO.getRazonSocial());
        comercioExistente.setDireccion(comercioDTO.getDireccion());

        return comercioRepository.save(comercioExistente);

    }

    @Override
    public void eliminarComercio(Long cuit) {
        Comercio comercioExistente = obtenerComercio(cuit);
        comercioExistente.setEstado(Estado.SUSPENDIDO);
        comercioRepository.save(comercioExistente);
    }
}
