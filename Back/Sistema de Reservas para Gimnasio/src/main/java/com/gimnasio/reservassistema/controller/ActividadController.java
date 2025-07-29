package com.gimnasio.reservassistema.controller;

import com.gimnasio.reservassistema.dto.ActividadDTO;
import com.gimnasio.reservassistema.service.ActividadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ActividadController {
    private final ActividadService actividadService;

    @GetMapping("/actividades")
    public ResponseEntity<List<ActividadDTO>> getActividades() {
        return ResponseEntity.ok(actividadService.findAllActividad());
    }
}
