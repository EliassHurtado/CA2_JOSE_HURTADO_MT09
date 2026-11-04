package mx.edu.utez.MT09_Jose_Hurtado_CA2.model.inscripcion;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.MT09_Jose_Hurtado_CA2.model.curso.Curso;

import java.time.LocalDate;

@Entity
@Table(name = "Incripciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombreParticipante;

    @Column
    private int edadParticipante;

    @Column
    private String correo;

    @Column
    private LocalDate fechaInscripcion;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Column
    private Double calificacion=null;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
}