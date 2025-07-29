package com.gimnasio.reservassistema.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gimnasio.reservassistema.entitie.Actividad;
import com.gimnasio.reservassistema.entitie.Instructor;
import com.gimnasio.reservassistema.entitie.Socio;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDTO {
    private Long id;

    private SocioDTO socio;
    @NotNull
    private ActividadDTO actividad;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaHora;

    @NotNull
    private InstructorDTO instructor;


    private String observaciones;


    private String status;
}
