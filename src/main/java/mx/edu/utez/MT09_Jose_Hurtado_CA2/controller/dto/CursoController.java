package mx.edu.utez.MT09_Jose_Hurtado_CA2.controller;

import mx.edu.utez.MT09_Jose_Hurtado_CA2.controller.dto.CursoDTO;
import mx.edu.utez.MT09_Jose_Hurtado_CA2.model.curso.Curso;
import mx.edu.utez.MT09_Jose_Hurtado_CA2.model.curso.Estado;
import mx.edu.utez.MT09_Jose_Hurtado_CA2.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
@CrossOrigin(origins = "*")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity<Curso> registrarCurso(@RequestBody CursoDTO dto) {
        Curso cursoCreado = cursoService.registrarCurso(dto);
        return new ResponseEntity<>(cursoCreado, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Curso>> listarTodos() {
        return ResponseEntity.ok(cursoService.listarTodos());
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<Curso>> listarDisponibles() {
        return ResponseEntity.ok(cursoService.listarDisponibles());
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Curso> cambiarEstado(@PathVariable Long id, @RequestParam Estado nuevoEstado) {
        Curso cursoActualizado = cursoService.cambiarEstado(id, nuevoEstado);
        return ResponseEntity.ok(cursoActualizado);
    }
}