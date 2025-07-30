package com.gimnasio.reservassistema.service;

import com.gimnasio.reservassistema.dto.ActividadDTO;
import com.gimnasio.reservassistema.entitie.Actividad;
import com.gimnasio.reservassistema.repositories.ActividadRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActividadService {
    private final ActividadRepository actividadRepository;
    private final ModelMapper modelMapper;

    public List<ActividadDTO> findAllActividad() {
        List<Actividad> actividadList = actividadRepository.findAll();
        return modelMapper.map(actividadList, new TypeToken<List<ActividadDTO>>(){}.getType());
    }

    public ActividadDTO getActividadById(Long id) {
        Optional<Actividad> actividad = actividadRepository.findById(id);
        if (actividad.isPresent()) {
            return modelMapper.map(actividad.get(), ActividadDTO.class);
        }
        else
        {
            throw new EntityNotFoundException("Actividad no encontrado por id: " + id);
        }
    }



    //OTRA FORMA DE HACERLO
    //Actividad actividadid = actividadRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Actividad no encontrado por id: " + id));
    //return modelMapper.map(actividadid , ActividadDTO.class);
}
