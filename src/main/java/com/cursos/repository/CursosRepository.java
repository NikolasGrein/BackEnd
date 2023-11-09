package com.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursos.entities.Cursos;

public interface CursosRepository extends JpaRepository<Cursos, Long>{

}
