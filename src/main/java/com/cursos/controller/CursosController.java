package com.cursos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cursos.entities.Cursos;
import com.cursos.service.CursosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cursos", description = "API REST DE GERENCIAMENTO DE CURSOS")
@RestController
@RequestMapping("/cursos")
public class CursosController {
    
    private final CursosService cursosService;
    
    @Autowired
    public CursosController(CursosService cursosService) {
        this.cursosService = cursosService;
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Localiza cursos por ID")
    public ResponseEntity<Cursos> buscaCursosControlId(@PathVariable Long id) {
        Cursos cursos = cursosService.buscaCursosId(id);
        if (cursos != null) {
            return ResponseEntity.ok(cursos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping
    @Operation(summary = "Apresenta todos os cursos")
    public ResponseEntity<List<Cursos>> buscaTodosCursosControl() { 
        List<Cursos> cursos = cursosService.buscaTodosCursos();
        return ResponseEntity.ok(cursos);
    }
    
    @PostMapping
    public ResponseEntity<Cursos> salvaCursosControl(@RequestBody Cursos cursos) { 
        Cursos salvaCursos = cursosService.salvaCursos(cursos);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvaCursos);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Altera um curso")
    public ResponseEntity<Cursos> alteraCursosControl(@PathVariable Long id, @RequestBody Cursos cursos) { 
        Cursos alteraCursos = cursosService.alterarCursos(id, cursos);
        if (alteraCursos != null) {
            return ResponseEntity.ok(cursos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui um curso")
    public ResponseEntity<String> apagaCursosControl(@PathVariable Long id) { 
        boolean apagar = cursosService.apagarCursos(id);
        if (apagar) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
