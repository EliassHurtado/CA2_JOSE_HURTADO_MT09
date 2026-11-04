package mx.edu.utez.MT09_Jose_Hurtado_CA2.repository;

import mx.edu.utez.MT09_Jose_Hurtado_CA2.model.curso.Curso;
import mx.edu.utez.MT09_Jose_Hurtado_CA2.model.curso.Estado;
import mx.edu.utez.MT09_Jose_Hurtado_CA2.model.inscripcion.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findByEstado(Estado estado);
    List<Curso> findById(Estado curso_id);
}
