package com.gimnasio.reservassistema.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gimnasio.reservassistema.dto.ReservaDTO;
import com.gimnasio.reservassistema.dto.ReservaRequestDTO;
import com.gimnasio.reservassistema.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReservaController {
    private final ReservaService reservaService;

    @GetMapping("/reserva")
    public ResponseEntity<List<ReservaDTO>> getReservas(
            @RequestParam(name = "socio_id") Optional<Long> socioId,
            @RequestParam(name = "actividad_id") Optional<Long> actividadId,
            @RequestParam(name = "instructor_id") Optional<Long> instructorId,
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") @RequestParam Optional<LocalDateTime> fechaHora
    ) {
        if (socioId.isPresent()) {
            return ResponseEntity.ok(reservaService.findAllTurnosBySocio(socioId.get()));
        }
        else if (actividadId.isPresent()) {
            return ResponseEntity.ok(reservaService.findAllTurnosByActividad(actividadId.get()));
        }
        else if (instructorId.isPresent()) {
            return ResponseEntity.ok(reservaService.findAllTurnosByInstructor(instructorId.get()));
        }
        else if (fechaHora.isPresent()) {
            return ResponseEntity.ok(reservaService.findAllTurnosByFecha(fechaHora.get()));
        }
        else{
            return ResponseEntity.ok(reservaService.findAllTurnos());
        }
    }

    @PostMapping("reserva/programar")
    public ResponseEntity<List<ReservaDTO>> ProgramarReservas(
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") @RequestParam LocalDate fecha
    ){
        return ResponseEntity.ok(reservaService.programarReserva(fecha));
    }

    @PutMapping("/reserva")
    public ResponseEntity<ReservaDTO> createNewReserva(@RequestBody ReservaRequestDTO ReservaRequestDTO) {
        return ResponseEntity.ok(reservaService.NuevaReserva(ReservaRequestDTO));
    }
}
