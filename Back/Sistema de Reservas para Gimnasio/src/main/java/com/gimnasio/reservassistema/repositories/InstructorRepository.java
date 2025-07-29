package com.gimnasio.reservassistema.repositories;

import com.gimnasio.reservassistema.entitie.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
