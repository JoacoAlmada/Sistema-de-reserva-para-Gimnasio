package com.gimnasio.reservassistema.repositories;

import com.gimnasio.reservassistema.entitie.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocioRepository extends JpaRepository<Socio, Long> {
}
