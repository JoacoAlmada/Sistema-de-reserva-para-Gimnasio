package com.gimnasio.reservassistema.repositories;

import com.gimnasio.reservassistema.entitie.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
