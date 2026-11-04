package mx.edu.utez.MT09_Jose_Hurtado_CA2.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CursoDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaInicio;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaFin;
}
