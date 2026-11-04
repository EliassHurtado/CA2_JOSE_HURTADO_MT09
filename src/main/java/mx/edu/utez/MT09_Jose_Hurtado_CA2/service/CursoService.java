package mx.edu.utez.MT09_Jose_Hurtado_CA2.service;

import mx.edu.utez.MT09_Jose_Hurtado_CA2.controller.dto.CursoDTO;
import mx.edu.utez.MT09_Jose_Hurtado_CA2.exception.BusinessException;
import mx.edu.utez.MT09_Jose_Hurtado_CA2.model.curso.Curso;
import mx.edu.utez.MT09_Jose_Hurtado_CA2.model.curso.Estado;
import mx.edu.utez.MT09_Jose_Hurtado_CA2.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public Curso registrarCurso(CursoDTO dto) {
        Curso curso = new Curso();
        curso.setNombre(dto.getNombre());
        curso.setDescripcion(dto.getDescripcion());
        curso.setFechaInicio(dto.getFechaInicio());
        curso.setFechaFin(dto.getFechaFin());
        curso.setEstado(Estado.DISPONIBLE);
        return cursoRepository.save(curso);
    }

    public List<Curso> listarTodos() {
        return cursoRepository.findAll();
    }
    public List<Curso> listarDisponibles() {
        return cursoRepository.findByEstado(Estado.DISPONIBLE);
    }
    public Curso cambiarEstado(Long id, Estado nuevoEstado) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Curso no encontrado"));

        if (curso.getEstado() == Estado.FINALIZADO) {
            throw new BusinessException("No se puede cambiar un curso finalizado");
        }
        if (curso.getEstado() == Estado.DISPONIBLE && nuevoEstado != Estado.EN_CURSO) {
            throw new BusinessException("un curso disponible solo puede pasar a  ser en curso");
        }


        if (curso.getEstado() == Estado.EN_CURSO && nuevoEstado != Estado.FINALIZADO) {
            throw new BusinessException("Un curso en curso solo puede pasar a ser finalizado");
        }


        curso.setEstado(nuevoEstado);
        return cursoRepository.save(curso);
    }
}
