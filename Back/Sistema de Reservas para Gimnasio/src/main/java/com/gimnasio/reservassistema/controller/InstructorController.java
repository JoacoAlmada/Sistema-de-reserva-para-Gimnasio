package com.gimnasio.reservassistema.controller;

import com.gimnasio.reservassistema.dto.InstructorDTO;
import com.gimnasio.reservassistema.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class InstructorController {
    private final InstructorService instructorService;

    @GetMapping("/instructores")
    public ResponseEntity<List<InstructorDTO>> getInstructores() {
      return  ResponseEntity.ok(instructorService.findAllInstructores());
    }
}
