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
    @JsonProperty("socioId")
    @NotNull
    private Long socioId;
    @JsonProperty("actividadId")
    @NotNull
    private Long actividadId;
    @JsonProperty("instructorId")
    @NotNull
    private Long instructorId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @NotNull
    private LocalDateTime fechaHora;

    private String observaciones;
}
