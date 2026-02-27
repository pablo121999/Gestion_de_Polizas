package com.pablo.polizas.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/core-mock")
public class CoreMockController {

    @PostMapping("/evento")
    public ResponseEntity<String> evento(
            @RequestHeader(value = "x-api-key", required = false) String apiKey,
            @RequestBody Map<String, Object> body) {

        if (!"123456".equals(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("API KEY inválida");
        }

        System.out.println("Evento enviado al CORE: " + body);

        return ResponseEntity.ok("Evento registrado");
    }
}