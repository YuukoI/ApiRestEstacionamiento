package com.TPDaos.dao;

import com.TPDaos.domain.Patente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PatenteRepository extends JpaRepository<Patente, Long> {

    @Query("SELECT p FROM Patente p WHERE p.numero = :numero")
    Patente findByNumero(@Param("numero") String numero);

}
