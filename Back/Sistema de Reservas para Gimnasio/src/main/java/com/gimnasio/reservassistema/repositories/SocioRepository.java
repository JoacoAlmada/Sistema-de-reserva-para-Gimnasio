package com.gimnasio.reservassistema.repositories;

import com.gimnasio.reservassistema.entitie.Socio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocioRepository extends JpaRepository<Socio, Long> {
}
