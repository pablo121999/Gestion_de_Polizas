package com.pablo.polizas.service;

import com.pablo.polizas.model.*;
import com.pablo.polizas.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PolizaService {

    private final PolizaRepository polizaRepository;
    private final RiesgoRepository riesgoRepository;

    public PolizaService(PolizaRepository polizaRepository,
                         RiesgoRepository riesgoRepository) {
        this.polizaRepository = polizaRepository;
        this.riesgoRepository = riesgoRepository;
    }

    public List<Poliza> listar(TipoPoliza tipo, Estado estado) {
        return polizaRepository.findByTipoAndEstado(tipo, estado);
    }

    @Transactional
    public Poliza renovar(Long id, Double ipc) {
        Poliza poliza = polizaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Póliza no encontrada"));

        if (poliza.getEstado() == Estado.CANCELADA) {
            throw new RuntimeException("No se puede renovar una póliza cancelada");
        }

        poliza.setCanonMensual(poliza.getCanonMensual() * (1 + ipc));
        poliza.setPrima(poliza.getPrima() * (1 + ipc));
        poliza.setEstado(Estado.RENOVADA);

        return polizaRepository.save(poliza);
    }

    @Transactional
    public void cancelar(Long id) {
        Poliza poliza = polizaRepository.findById(id)
                .orElseThrow();

        poliza.setEstado(Estado.CANCELADA);

        poliza.getRiesgos()
              .forEach(r -> r.setEstado(Estado.CANCELADO));
    }
}