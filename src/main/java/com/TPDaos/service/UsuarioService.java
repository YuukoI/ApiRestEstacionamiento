package com.TPDaos.service;

import com.TPDaos.domain.Usuario;
import java.util.List;

public interface UsuarioService {

    public List<Usuario> getUsuarios();

    public Usuario getUsuario(String dni);

    public Usuario addUsuario(Usuario usuario);

    public Usuario updateUsuario(Usuario usuario);

    public void deleteUsuario(String dni);
}
