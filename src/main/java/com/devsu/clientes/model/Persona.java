package com.devsu.clientes.model;

import com.devsu.clientes.enums.Genero;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Range;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Persona implements Serializable {

    @Id
    private Long id;

    @NotEmpty(message = "Nombre debe ser ingresado")
    private String nombre;

    @NotEmpty(message = "Apellidos debe ser ingresado")
    private String apellidos;

    @NotEmpty(message = "Identificacion debe ser ingresado")
    @Pattern(regexp = "^[0-9]{10,11}$", message = "Identificación incorrecta y debe tener entre 10 y 11 numeros")
    private String identificacion;

    @NotNull(message = "Genero debe ser ingresado MASCULINO, FEMENINO O OTRO")
    @Enumerated(EnumType.STRING)
    private Genero genero;

    @NotNull(message = "Edad debe ser ingresado")
    @Range(min=1, max=150,message = "Edad minima 1, edad maxima 150")
    private Integer edad;

    @NotEmpty(message = "Direccion debe ser ingresado")
    private String direccion;

    @NotEmpty(message = "Telefono debe ser ingresado")
    @Pattern(regexp = "^[0-9]{8,10}$", message = "Numero de Telefono invalido y debe tener entre 8 y 10 numeros")
    private String telefono;
}
