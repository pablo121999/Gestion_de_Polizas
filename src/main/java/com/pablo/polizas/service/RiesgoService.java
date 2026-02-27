package com.pablo.polizas.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pablo.polizas.model.Estado;
import com.pablo.polizas.model.Poliza;
import com.pablo.polizas.model.Riesgo;
import com.pablo.polizas.model.TipoPoliza;
import com.pablo.polizas.repository.PolizaRepository;
import com.pablo.polizas.repository.RiesgoRepository;
@Service
public class RiesgoService {

    private final RiesgoRepository riesgoRepository;
    private final PolizaRepository polizaRepository;

    public RiesgoService(RiesgoRepository riesgoRepository,
                         PolizaRepository polizaRepository) {
        this.riesgoRepository = riesgoRepository;
        this.polizaRepository = polizaRepository;
    }

    public List<Riesgo> listarPorPoliza(Long polizaId) {
        return riesgoRepository.findByPolizaId(polizaId);
    }

    public Riesgo agregarRiesgo(Long polizaId) {
        Poliza poliza = polizaRepository.findById(polizaId)
                .orElseThrow();

        if (poliza.getTipo() == TipoPoliza.INDIVIDUAL) {
            throw new RuntimeException("Una póliza individual solo puede tener 1 riesgo");
        }

        Riesgo riesgo = new Riesgo();
        riesgo.setEstado(Estado.ACTIVO);
        riesgo.setPoliza(poliza);

        return riesgoRepository.save(riesgo);
    }


    @Transactional
    public void cancelar(Long id) {
        Riesgo riesgo = riesgoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Riesgo no encontrado"));

        riesgo.setEstado(Estado.CANCELADO);
    }
}