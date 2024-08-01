package com.TPDaos.dao;

import com.TPDaos.domain.Estacionamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EstacionamientoRepository extends JpaRepository<Estacionamiento, Long> {

    @Query("SELECT e FROM Estacionamiento e WHERE e.patente.numero = :numero AND e.estado = false")
    Estacionamiento findEstacionamientoByPatenteAndEstadoLibre(@Param("numero") String numero);

    @Query("SELECT e FROM Estacionamiento e WHERE e.patente.numero = :numero AND e.estado = true")
    Estacionamiento findEstacionamientoByPatenteAndEstadoOcupado(@Param("numero") String numero);

    @Query("SELECT e FROM Estacionamiento e WHERE e.patente.numero = :numero")
    Estacionamiento findEstacionamientoByPatente(@Param("numero") String numero);
}
