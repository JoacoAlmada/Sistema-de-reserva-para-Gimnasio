package com.gimnasio.reservassistema.repositories;

import com.gimnasio.reservassistema.entitie.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActividadRepository extends JpaRepository<Actividad, Long> {
}
