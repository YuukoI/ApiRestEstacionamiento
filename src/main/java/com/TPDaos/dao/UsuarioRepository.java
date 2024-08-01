package com.TPDaos.dao;

import com.TPDaos.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    @Query("SELECT u FROM Usuario u WHERE u.contrasenia = :contrasenia")
    Usuario findByContrasenia(@Param("contrasenia") String contrasenia);
}
