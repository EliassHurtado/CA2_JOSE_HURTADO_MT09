package mx.edu.utez.MT09_Jose_Hurtado_CA2.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.MT09_Jose_Hurtado_CA2.model.inscripcion.Estado;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InscripcionDTO {

    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombreParticipante;

    @NotNull(message = "Ingrese la edad")
    private int edadParticipante;

    @Email
    private String correo;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaInscripcion;

}
