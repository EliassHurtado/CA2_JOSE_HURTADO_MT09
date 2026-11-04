package mx.edu.utez.MT09_Jose_Hurtado_CA2.model.curso;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "cursos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Column
    private String descripcion;

    @Column
    private LocalDate fechaInicio;

    @Column
    private LocalDate fechaFin;

    @Enumerated(EnumType.STRING)
    private Estado estado;
}
