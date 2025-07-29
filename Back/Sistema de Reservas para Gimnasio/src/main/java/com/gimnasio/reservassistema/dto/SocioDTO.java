package com.gimnasio.reservassistema.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocioDTO {
    private Long id;
    @NotBlank
    @NotNull
    private String nombre_completo;
    private Date fecha_nacimiento;
    @NotBlank
    @NotNull
    private String numero_socio;

}
