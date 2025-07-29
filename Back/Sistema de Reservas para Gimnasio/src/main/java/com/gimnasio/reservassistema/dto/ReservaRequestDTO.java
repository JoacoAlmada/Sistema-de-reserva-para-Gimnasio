package com.gimnasio.reservassistema.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class ReservaRequestDTO {
    @JsonProperty("socio_id")
    @NotNull
    private SocioDTO socio;
    @JsonProperty("actividad_id")
    @NotNull
    private ActividadDTO actividad;
    @JsonProperty("instructor_id")
    @NotNull
    private InstructorDTO instructor;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @NotNull
    private LocalDateTime fechaHora;

    private String observaciones;
}
