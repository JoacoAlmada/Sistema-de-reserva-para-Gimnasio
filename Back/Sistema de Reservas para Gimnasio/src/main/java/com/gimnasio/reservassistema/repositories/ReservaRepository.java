package com.gimnasio.reservassistema.repositories;

import com.gimnasio.reservassistema.entitie.Reserva;
import com.gimnasio.reservassistema.entitie.ReservaStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findReservaByInstructor_Id(Long instructorId);

    List<Reserva> findReservaBySocio_Id(Long socioId);

    List<Reserva> findReservaByActividad_Id(Long actividadId);

    List<Reserva> findReservaByFechaHora(LocalDateTime fechaHora);


    List<Reserva> findReservaByFechaHoraBetween(LocalDateTime localDateTime, LocalDateTime localDateTime1);

    Optional<Reserva> findReservaByFechaHoraAndInstructor_Id(LocalDateTime fechaHora, Long instructorId);

    int countByFechaHoraBetween(LocalDateTime start, LocalDateTime end);

    boolean existsByInstructorIdAndFechaHoraAndStatus(Long instructorId, LocalDateTime attr0, ReservaStatus status);

    int countBySocioIdAndFechaHoraBetween(Long socioId, LocalDateTime start, LocalDateTime end);
}
