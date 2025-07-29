package com.gimnasio.reservassistema.controller;

import com.gimnasio.reservassistema.dto.SocioDTO;
import com.gimnasio.reservassistema.entitie.Socio;
import com.gimnasio.reservassistema.service.SocioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SocioController {
    private final SocioService socioService;

    @GetMapping("/socios")
    public ResponseEntity<List<SocioDTO>> getSocio() {
        return ResponseEntity.ok(socioService.getAllSocios());
    }
}
