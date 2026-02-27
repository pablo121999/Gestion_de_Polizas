package com.pablo.polizas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pablo.polizas.service.RiesgoService;

@RestController
@RequestMapping("/riesgos")
public class RiesgoController {

    private final RiesgoService riesgoService;

    public RiesgoController(RiesgoService riesgoService) {
        this.riesgoService = riesgoService;
    }

    @PostMapping("/{id}/cancelar")
    public ResponseEntity<String> cancelar(@PathVariable Long id) {
        riesgoService.cancelar(id);
        return ResponseEntity.ok("Riesgo cancelado con éxito");
    }
}