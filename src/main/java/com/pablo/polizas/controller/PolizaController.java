package com.pablo.polizas.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pablo.polizas.model.Estado;
import com.pablo.polizas.model.Poliza;
import com.pablo.polizas.model.Riesgo;
import com.pablo.polizas.model.TipoPoliza;
import com.pablo.polizas.service.PolizaService;
import com.pablo.polizas.service.RiesgoService;

@RestController
@RequestMapping("/polizas")
public class PolizaController {

    private final PolizaService polizaService;
    private final RiesgoService riesgoService;

    public PolizaController(PolizaService polizaService,
            RiesgoService riesgoService) {
        this.polizaService = polizaService;
        this.riesgoService = riesgoService;
    }

    @GetMapping
    public List<Poliza> listar(@RequestParam TipoPoliza tipo,
            @RequestParam Estado estado) {
        return polizaService.listar(tipo, estado);
    }

    @GetMapping("/{id}/riesgos")
    public List<Riesgo> listarRiesgos(@PathVariable Long id) {
        return riesgoService.listarPorPoliza(id);
    }

    @PostMapping("/{id}/renovar")
    public ResponseEntity<String> renovar(
            @PathVariable Long id,
            @RequestParam Double ipc) {

        polizaService.renovar(id, ipc);

        return ResponseEntity.ok("Póliza renovada con éxito");
    }

    @PostMapping("/{id}/cancelar")
    public ResponseEntity<String> cancelar(@PathVariable Long id) {
        polizaService.cancelar(id);
        return ResponseEntity.ok("Póliza cancelada con éxito");
    }

    @PostMapping("/{id}/riesgos")
    public ResponseEntity<String> agregarRiesgo(@PathVariable Long id) {
        riesgoService.agregarRiesgo(id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Riesgo agregado con éxito");
    }
}
