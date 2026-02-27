package com.pablo.polizas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pablo.polizas.model.Estado;
import com.pablo.polizas.model.Poliza;
import com.pablo.polizas.model.TipoPoliza;

public interface PolizaRepository extends JpaRepository<Poliza, Long> {

    @EntityGraph(attributePaths = {"riesgos"})
    List<Poliza> findByTipoAndEstado(TipoPoliza tipo, Estado estado);
}