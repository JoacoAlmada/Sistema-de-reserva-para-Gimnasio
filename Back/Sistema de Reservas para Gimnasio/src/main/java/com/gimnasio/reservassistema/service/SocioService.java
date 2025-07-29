package com.gimnasio.reservassistema.service;

import com.gimnasio.reservassistema.dto.SocioDTO;
import com.gimnasio.reservassistema.entitie.Socio;
import com.gimnasio.reservassistema.repositories.SocioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SocioService {

    private final SocioRepository socioRepository;
    private final ModelMapper modelMapper;

    public List<SocioDTO> getAllSocios() {
        List<Socio> listaSocios = socioRepository.findAll();
        return modelMapper.map(listaSocios, new TypeToken<List<SocioDTO>>() {}.getType());
    }

    public SocioDTO getSocioById(Long id) {
        Optional<Socio> socio = socioRepository.findById(id);
        if (socio.isPresent()) {
            return modelMapper.map(socio.get(), SocioDTO.class);
        }
        else
        {
            throw new EntityNotFoundException("Socio no encontrado por id: " + id);
        }

        //OTRA FORMA DE HACERLO
        //Socio socioid = socioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Socio no encontrado por id: " + id));
        //return modelMapper.map(socioid, SocioDTO.class);
    }
}
