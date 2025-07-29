package com.gimnasio.reservassistema.repositories;

import com.gimnasio.reservassistema.entitie.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
