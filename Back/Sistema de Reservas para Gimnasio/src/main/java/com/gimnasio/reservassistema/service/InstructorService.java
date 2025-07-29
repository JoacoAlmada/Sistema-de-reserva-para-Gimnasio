package com.gimnasio.reservassistema.service;

import com.gimnasio.reservassistema.dto.InstructorDTO;
import com.gimnasio.reservassistema.entitie.Instructor;
import com.gimnasio.reservassistema.repositories.InstructorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorRepository instructorRepository;
    private final ModelMapper modelMapper;

    public List<InstructorDTO> findAllInstructores() {
        List<Instructor> instructorList = instructorRepository.findAll();
        return modelMapper.map(instructorList,new TypeToken<List<InstructorDTO>>(){}.getType());
    }

    public InstructorDTO findInstructorById(Long id) {
        Optional<Instructor> instructor = instructorRepository.findById(id);
        if(instructor.isPresent()) {
            return modelMapper.map(instructor.get(),InstructorDTO.class);
        }
        else {
            throw new EntityNotFoundException("Instructor no encontrado por id: " + id);
        }

        //OTRA FORMA DE HACERLO
        //Instructor instructorid = instructorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Instructor no encontrado por id: " + id));
        //return modelMapper.map(instructorid , InstructorDTO.class);
    }

}
