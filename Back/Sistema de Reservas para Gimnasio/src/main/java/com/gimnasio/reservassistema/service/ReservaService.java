package com.gimnasio.reservassistema.service;

import com.gimnasio.reservassistema.dto.ReservaDTO;
import com.gimnasio.reservassistema.entitie.Reserva;
import com.gimnasio.reservassistema.repositories.ActividadRepository;
import com.gimnasio.reservassistema.repositories.InstructorRepository;
import com.gimnasio.reservassistema.repositories.ReservaRepository;
import com.gimnasio.reservassistema.repositories.SocioRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaService {
    private final ReservaRepository reservaRepository;
    private final InstructorRepository instructorRepository;
    private final SocioRepository socioRepository;
    private final ActividadRepository actividadRepository;
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

    //FALTA

}
