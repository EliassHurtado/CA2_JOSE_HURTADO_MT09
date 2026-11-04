package mx.edu.utez.MT09_Jose_Hurtado_CA2.repository;

import mx.edu.utez.MT09_Jose_Hurtado_CA2.model.inscripcion.Estado;
import mx.edu.utez.MT09_Jose_Hurtado_CA2.model.inscripcion.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
        List<Inscripcion> findByCorreo(String correo);
        List<Inscripcion> findByCursoId(Long cursoId);
        List<Inscripcion> findByEstado(Estado estado);
}
