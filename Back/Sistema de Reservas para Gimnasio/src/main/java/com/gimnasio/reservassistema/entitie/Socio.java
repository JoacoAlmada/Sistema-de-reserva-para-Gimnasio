package com.gimnasio.reservassistema.entitie;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "socios")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Socio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column
    private String nombre_completo;
    @Column
    private Date fecha_nacimiento;
    @NotBlank
    @Column
    private String numero_socio;

}
