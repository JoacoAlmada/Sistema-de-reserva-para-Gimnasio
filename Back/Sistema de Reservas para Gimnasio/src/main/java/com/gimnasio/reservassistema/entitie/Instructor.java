package com.gimnasio.reservassistema.entitie;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "instructores")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotBlank
    @Column
    private String nombre_completo;
    @NotNull
    @NotBlank
    @Column
    private String especialidad;
    @Column
    private String certificacion;

}
