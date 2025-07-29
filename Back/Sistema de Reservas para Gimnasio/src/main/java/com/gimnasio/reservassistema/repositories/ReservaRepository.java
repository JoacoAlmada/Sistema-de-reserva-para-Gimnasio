package com.gimnasio.reservassistema.repositories;

import com.gimnasio.reservassistema.entitie.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findReservaByInstructor_Id(Long instructorId);

    List<Reserva> findReservaBySocio_Id(Long socioId);

    List<Reserva> findReservaByActividad_Id(Long actividadId);

    List<Reserva> findReservaByFechaHora(LocalDateTime fechaHora);
}
