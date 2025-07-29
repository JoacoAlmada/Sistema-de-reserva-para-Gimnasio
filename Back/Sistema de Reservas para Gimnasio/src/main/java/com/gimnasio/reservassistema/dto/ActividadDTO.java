package com.gimnasio.reservassistema.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActividadDTO {
    private Long id;

    @NotBlank
    @NotNull
    private String nombreActividad;

    private String descripcion;
    @NotBlank
    @NotNull
    private Integer duracionMinutos;
}
