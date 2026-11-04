package mx.edu.utez.MT09_Jose_Hurtado_CA2.service;

import mx.edu.utez.MT09_Jose_Hurtado_CA2.controller.dto.InscripcionDTO;
import mx.edu.utez.MT09_Jose_Hurtado_CA2.exception.BusinessException;
import mx.edu.utez.MT09_Jose_Hurtado_CA2.model.curso.Curso;
import mx.edu.utez.MT09_Jose_Hurtado_CA2.model.inscripcion.Estado;
import mx.edu.utez.MT09_Jose_Hurtado_CA2.model.inscripcion.Inscripcion;
import mx.edu.utez.MT09_Jose_Hurtado_CA2.repository.CursoRepository;
import mx.edu.utez.MT09_Jose_Hurtado_CA2.repository.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InscripcionService {

    private final InscripcionRepository inscripcionRepository;
    private final CursoRepository cursoRepository;
    @Autowired
    public InscripcionService(InscripcionRepository inscripcionRepository, CursoRepository cursoRepository) {
        this.inscripcionRepository = inscripcionRepository;
        this.cursoRepository = cursoRepository;
    }

    public Inscripcion inscribir(InscripcionDTO dto) {
        Curso curso = cursoRepository.findById(dto.getCurso_id())
                .orElseThrow(() -> new BusinessException("El curso no existe"));

        if (curso.getEstado() != mx.edu.utez.MT09_Jose_Hurtado_CA2.model.curso.Estado.DISPONIBLE) {
            throw new BusinessException("El curso no se encuentra disponible");
        }

        Inscripcion inc = new Inscripcion();
        inc.setNombreParticipante(dto.getNombreParticipante());
        inc.setEdadParticipante(dto.getEdadParticipante());
        inc.setCorreo(dto.getCorreo());
        inc.setCurso(curso);
        inc.setFechaInscripcion(LocalDate.now());
        inc.setEstado(Estado.ACTIVA);
        return inscripcionRepository.save(inc);
    }

    public Inscripcion cancelar(Long id) {
        Inscripcion inscrip = inscripcionRepository.findById(id)
                .orElseThrow(() -> new BusinessException("No se encontró la inscripción"));

        if (inscrip.getEstado() !=Estado.ACTIVA) {
            throw new BusinessException("Solo se puede cancelar una inscripción activa");
        }

        if (inscrip.getCurso().getFechaInicio() != null && !LocalDate.now().isBefore(inscrip.getCurso().getFechaInicio())) {
            throw new BusinessException("No se puede cancelar porque el curso está iniciado");
        }

        inscrip.setEstado(Estado.CANCELADA);
        return inscripcionRepository.save(inscrip);
    }

    public List<Inscripcion> buscarPorCorreo(String correo) {
        return inscripcionRepository.findByCorreo(correo);
    }

    public List<Inscripcion> buscarPorCurso(Long cursoId) {
        if (!cursoRepository.existsById(cursoId)) {
            throw new BusinessException("El curso no existe");
        }
        return inscripcionRepository.findByCursoId(cursoId);
    }

    public Inscripcion consultarDetalle(Long id) {
        return inscripcionRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Inscripción no encontrada"));
    }

    public List<Inscripcion> buscarPorEstado(Estado estado) {
        return inscripcionRepository.findByEstado(estado);
    }

    public Inscripcion registrarCalificacion(Long id, Double calificacion) {
        if (calificacion < 0|| calificacion>10) {
            throw new BusinessException("La calificación debe estar entre 0 y 10");
        }
        Inscripcion inscrip = inscripcionRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Inscripción no encontrada"));
        if (inscrip.getEstado() != Estado.ACTIVA) {
            throw new BusinessException("La inscripción debe estar activa para poder ser ser calificada");
        }
        if (inscrip.getCurso().getEstado() != mx.edu.utez.MT09_Jose_Hurtado_CA2.model.curso.Estado.FINALIZADO) {
            throw new BusinessException("El curaso debe estar finalizado para poder calificar");
        }

        inscrip.setCalificacion(calificacion);
        inscrip.setEstado(Estado.COMPLETADA);
        return inscripcionRepository.save(inscrip);
    }
}

