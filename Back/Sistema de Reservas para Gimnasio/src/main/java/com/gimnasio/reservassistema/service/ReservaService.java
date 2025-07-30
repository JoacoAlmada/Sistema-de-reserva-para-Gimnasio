package com.gimnasio.reservassistema.service;

import com.gimnasio.reservassistema.dto.*;
import com.gimnasio.reservassistema.entitie.*;
import com.gimnasio.reservassistema.repositories.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservaService {
    private final ReservaRepository reservaRepository;
    private final InstructorService instructorService;
    private final SocioService socioService;
    private final ActividadService actividadService;
    private final ModelMapper modelMapper;

    public List<ReservaDTO> findAllTurnos() {
        List<Reserva> reservaList = reservaRepository.findAll();
        return  modelMapper.map(reservaList , new TypeToken<List<ReservaDTO>>(){}.getType());
    }

    public List<ReservaDTO> findAllTurnosByInstructor(Long instructorId) {
        List<Reserva> reservaList = reservaRepository.findReservaByInstructor_Id(instructorId);
        return modelMapper.map(reservaList , new TypeToken<List<ReservaDTO>>(){}.getType());
    }

    public List<ReservaDTO> findAllTurnosBySocio(Long socioId) {
        List<Reserva> reservaList = reservaRepository.findReservaBySocio_Id(socioId);
        return modelMapper.map(reservaList , new TypeToken<List<ReservaDTO>>(){}.getType());
    }
    public List<ReservaDTO> findAllTurnosByActividad(Long actividadId) {
        List<Reserva> reservaList = reservaRepository.findReservaByActividad_Id(actividadId);
        return modelMapper.map(reservaList , new TypeToken<List<ReservaDTO>>(){}.getType());
    }

    public List<ReservaDTO> findAllTurnosByFecha(LocalDateTime fechaHora) {
        List<Reserva> reservaList = reservaRepository.findReservaByFechaHora(fechaHora);
        return modelMapper.map(reservaList , new TypeToken<List<ReservaDTO>>(){}.getType());
    }

    public List<ReservaDTO> programarReserva(LocalDate  fechaHora) {

        List<Reserva> existentes = reservaRepository.findReservaByFechaHoraBetween(
                fechaHora.atTime(6, 0), fechaHora.atTime(22, 0)
        );
        if (!existentes.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Las reservas ya están programadas para la fecha indicada");
        }

        List<ReservaDTO> reservasProgramadas = new ArrayList<>();

        List<InstructorDTO> instructoresDisponibles = instructorService.findAllInstructores();

        List<LocalTime> horarios = new ArrayList<>();
        LocalTime hora = LocalTime.of(6, 0);
        while (!hora.isAfter(LocalTime.of(21, 45))) {
            horarios.add(hora);
            hora = hora.plusMinutes(45);
        }

        for (InstructorDTO instructorDTO : instructoresDisponibles) {
            Instructor instructor = modelMapper.map(instructorDTO, Instructor.class);
            int clasesAgendadas = 0;

            for (LocalTime h : horarios) {
                if (clasesAgendadas >= 20) break;

                Reserva reserva = new Reserva();
                reserva.setInstructor(instructor);
                reserva.setSocio(null);
                reserva.setActividad(null);
                reserva.setObservaciones(null);
                reserva.setFechaHora(LocalDateTime.of(fechaHora, h));
                reserva.setStatus(ReservaStatus.DISPONIBLE);

                reservaRepository.save(reserva);
                reservasProgramadas.add(modelMapper.map(reserva, ReservaDTO.class));

                clasesAgendadas++;
            }
        }

        return reservasProgramadas;
    }

    public ReservaDTO NuevaReserva(ReservaRequestDTO reservaRequestDTO) {
        if (reservaRequestDTO.getActividadId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El campo actividad es obligatorio");
        }
        ActividadDTO actividad = actividadService.getActividadById(reservaRequestDTO.getActividadId());
        SocioDTO socio = socioService.getSocioById(reservaRequestDTO.getSocioId());
        InstructorDTO instructor = instructorService.getInstructorById(reservaRequestDTO.getInstructorId());

        LocalDate fecha = reservaRequestDTO.getFechaHora().toLocalDate();

        // Validar máximo 2 reservas por socio ese día
        LocalDate f = reservaRequestDTO.getFechaHora().toLocalDate();
        LocalDateTime start = f.atStartOfDay();
        LocalDateTime end = f.plusDays(1).atStartOfDay();

        int reservasSocio = reservaRepository.countBySocioIdAndFechaHoraBetween(reservaRequestDTO.getSocioId(), start, end);
        if (reservasSocio >= 2) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Máximo 2 reservas por socio por día");
        }
        // Buscar reserva disponible para instructor en fecha y hora
        Optional<Reserva> optReserva = reservaRepository.findReservaByFechaHoraAndInstructor_Id(
                reservaRequestDTO.getFechaHora(), instructor.getId());
        Reserva r = optReserva
                .filter(p -> p.getStatus().equals(ReservaStatus.DISPONIBLE))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No hay reserva disponible"));


        // Validar que instructor no tenga otra clase a esa hora (además de la encontrada)
        boolean instructorOcupado = reservaRepository.existsByInstructorIdAndFechaHoraAndStatus((
                instructor.getId()), reservaRequestDTO.getFechaHora(), ReservaStatus.OCUPADO);
        if (instructorOcupado) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Instructor ya tiene clase a esa hora");
        }

        // Validar máximo 60 reservas totales por día
        LocalDate F = reservaRequestDTO.getFechaHora().toLocalDate();
        LocalDateTime s = F.atStartOfDay();
        LocalDateTime e = F.plusDays(1).atStartOfDay();

        int reservasDia = reservaRepository.countByFechaHoraBetween(s, e);
        if (reservasDia >= 60) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Máximo 60 reservas por día alcanzado");
        }

        r.setSocio(modelMapper.map(socio, Socio.class));
        r.setActividad(modelMapper.map(actividad, Actividad.class));
        r.setObservaciones(reservaRequestDTO.getObservaciones());
        r.setStatus(ReservaStatus.OCUPADO);

        reservaRepository.save(r);
        return modelMapper.map(r, ReservaDTO.class);

    }

}
