package com.TPDaos.service;

import com.TPDaos.dao.PatenteRepository;
import com.TPDaos.dao.UsuarioRepository;
import com.TPDaos.domain.Patente;
import com.TPDaos.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImp implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PatenteRepository patenteRepository;

    @Override
    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario getUsuario(String dni) {
        return usuarioRepository.findById(dni).orElse(null);
    }

    @Override
    public Usuario addUsuario(Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findById(usuario.getDni()).orElse(null);
        Patente patenteExistente = patenteRepository.findByNumero(usuario.getPatente().getNumero());

        if (usuarioExistente != null) {
            throw new RuntimeException("Usuario ya existe");
        }

        if (patenteExistente != null) {
            throw new RuntimeException("Patente ya existe");
        }

        Usuario u = new Usuario();
        u.setDni(usuario.getDni());
        u.setNombre(usuario.getNombre());
        u.setApellido(usuario.getApellido());
        u.setContrasenia(usuario.getContrasenia());
        u.setDomicilio(usuario.getDomicilio());
        u.setMail(usuario.getMail());
        u.setSaldo(usuario.getSaldo());
        u.setFechaNacimiento(usuario.getFechaNacimiento());

        Patente p = new Patente();
        p.setNumero(usuario.getPatente().getNumero());
        p.setUsuario(u);

        u.setPatente(p);

        return usuarioRepository.save(u);
    }

    @Override
    public Usuario updateUsuario(Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findById(usuario.getDni()).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        Patente patenteExistente = patenteRepository.findById(usuario.getPatente().getId()).orElseThrow(() -> new IllegalArgumentException("Patente no encontrada"));

        usuarioExistente.setNombre(usuario.getNombre());
        usuarioExistente.setApellido(usuario.getApellido());
        usuarioExistente.setContrasenia(usuario.getContrasenia());
        usuarioExistente.setDomicilio(usuario.getDomicilio());
        usuarioExistente.setMail(usuario.getMail());
        usuarioExistente.setSaldo(usuario.getSaldo());
        usuarioExistente.setFechaNacimiento(usuario.getFechaNacimiento());

        patenteExistente.setNumero(usuario.getPatente().getNumero());
        usuarioExistente.setPatente(patenteExistente);

        return usuarioRepository.save(usuarioExistente);
    }

    @Override
    public void deleteUsuario(String dni) {
        Usuario usuario = usuarioRepository.findById(dni).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        usuarioRepository.deleteById(dni);
    }

}
