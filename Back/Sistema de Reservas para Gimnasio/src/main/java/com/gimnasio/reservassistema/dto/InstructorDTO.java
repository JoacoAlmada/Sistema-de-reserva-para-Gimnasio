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
public class InstructorDTO {
    private Long id;
    @NotNull
    @NotBlank
    private String nombre_completo;
    @NotNull
    @NotBlank
    private String especialidad;
    private String certificacion;
}
