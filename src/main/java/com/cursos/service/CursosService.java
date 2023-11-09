package com.cursos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursos.entities.Cursos;
import com.cursos.repository.CursosRepository;

@Service
public class CursosService { 

    private final CursosRepository cursosRepository; 

    @Autowired
    public CursosService(CursosRepository cursosRepository) { 
        this.cursosRepository = cursosRepository;
    }

    public List<Cursos> buscaTodosCursos() { 
        return cursosRepository.findAll();
    }

    public Cursos buscaCursosId(Long id) { 
        Optional<Cursos> cursos = cursosRepository.findById(id); 
        return cursos.orElse(null);
    }

    public Cursos salvaCursos(Cursos cursos) { 
        return cursosRepository.save(cursos);
    }

    public Cursos alterarCursos(Long id, Cursos alterarCursos) { 
        Optional<Cursos> existeCursos = cursosRepository.findById(id);
        if (existeCursos.isPresent()) {
            alterarCursos.setId(id);
            return cursosRepository.save(alterarCursos);
        }
        return null;
    }

    public boolean apagarCursos(Long id) {
        Optional<Cursos> existeCursos = cursosRepository.findById(id);
        if (existeCursos.isPresent()) {
            cursosRepository.deleteById(id);
            return true;
        }
        return false;
    }
}