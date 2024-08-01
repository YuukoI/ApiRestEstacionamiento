package com.TPDaos.service;

import com.TPDaos.dao.ComercioRepository;
import com.TPDaos.dao.PatenteRepository;
import com.TPDaos.dao.RecargaRepository;
import com.TPDaos.dao.UsuarioRepository;
import com.TPDaos.domain.Comercio;
import com.TPDaos.domain.Patente;
import com.TPDaos.domain.Recarga;
import com.TPDaos.domain.Usuario;
import com.TPDaos.dto.RecargaDTO;
import com.TPDaos.enums.Estado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecargaServiceImp implements RecargaService{

    @Autowired
    private RecargaRepository recargaRepository;

    @Autowired
    private ComercioRepository comercioRepository;

    @Autowired
    private PatenteRepository patenteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public List<Recarga> getRecargas() {
        return recargaRepository.findAll();
    }

    @Override
    public Recarga getRecarga(Long id) {
        Recarga recarga = recargaRepository.findById(id).orElse(null);

        if(recarga==null){
            throw new RuntimeException("Recarga no encontrada");
        }

        return recarga;

    }

    @Override
    public Recarga addRecarga(RecargaDTO recargaDTO) {
        Comercio comercioExistente = comercioRepository.findById(recargaDTO.getNumeroComercio()).orElse(null);

        Usuario usuarioExistente = usuarioRepository.findById(recargaDTO.getDniUsuario()).orElse(null);

        Patente patenteExistente = patenteRepository.findByNumero(recargaDTO.getNumeroPatente());

        if(comercioExistente == null){
            throw new RuntimeException("Comercio no encontrado");
        }

        if(comercioExistente.getEstado().equals(Estado.SUSPENDIDO)){
            throw new RuntimeException("El comercio no se encuentra habilitado para realizar recargas");
        }

        if(usuarioExistente == null){
            throw new RuntimeException("Usuario no encontrado");
        }

        if(patenteExistente == null){
            throw new RuntimeException("Patente no encontrada");
        }

        Recarga recarga = new Recarga();
        recarga.setComercio(comercioExistente);
        recarga.setPatente(patenteExistente);
        recarga.setUsuario(usuarioExistente);
        recarga.setImporte(recargaDTO.getPrecio());

        usuarioService.agregarSaldo(recargaDTO.getPrecio(), usuarioExistente);

        return recargaRepository.save(recarga);
    }

    @Override
    public Recarga updateRecarga(RecargaDTO recargaDTO) {
        return null;
    }

    @Override
    public Recarga deleteRecarga(Long id) {
        return null;
    }

    @Override
    public List<Recarga> buscarRecargaPorDni(String dni) {
        Usuario usuario = usuarioRepository.findById(dni).orElse(null);
        if(usuario==null){
            throw new RuntimeException("Usuario no encontrado");
        }

        return recargaRepository.findByDni(dni);
    }

    @Override
    public List<Recarga> buscarRecargaPorNumeroPatente(String numeroPatente) {
        Patente patente = patenteRepository.findByNumero(numeroPatente);
        if(patente==null){
            throw new RuntimeException("Patente no encontrada");
        }
        return recargaRepository.findByPatenteNumero(numeroPatente);
    }

    @Override
    public List<Recarga> buscarRecargaPorNumeroComercio(Long numeroComercio) {
        Comercio comercio = comercioRepository.findById(numeroComercio).orElse(null);
        if(comercio==null){
            throw new RuntimeException("Comercio no encontrado");
        }
        return recargaRepository.findByNroComercio(numeroComercio);
    }
}
