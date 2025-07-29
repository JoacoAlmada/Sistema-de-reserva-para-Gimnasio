package com.gimnasio.reservassistema.entitie;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "actividades")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Actividad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Column
    private String nombreActividad;

    @Column
    private String descripcion;

    @NotNull
    @NotBlank
    @Column
    private Integer duracionMinutos;
}
