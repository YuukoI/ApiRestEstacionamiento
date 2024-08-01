package com.TPDaos.dao;

import com.TPDaos.domain.Recarga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecargaRepository extends JpaRepository<Recarga, Long> {

    @Query("SELECT r FROM Recarga r JOIN r.usuario u WHERE r.patente.numero = :numero")
    List<Recarga> findByPatenteNumero(@Param("numero") String numero);

    @Query("SELECT r FROM Recarga r JOIN r.usuario u WHERE r.usuario.dni = :dni")
    List<Recarga> findByDni(@Param("dni") String dni);

    @Query("SELECT r FROM Recarga r WHERE r.comercio.cuit = :numeroComercio")
    List<Recarga> findByNroComercio(@Param("numeroComercio") Long numeroComercio);
}
