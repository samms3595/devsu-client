package com.devsu.clientes.model;

import com.devsu.clientes.enums.Estado;
import com.devsu.clientes.enums.Genero;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cliente extends Persona  implements Serializable{

    @Column(unique = true)
    private Long clientId;

    @NotEmpty(message = "Contraseña debe ser ingresado")
    private String password;

    @NotNull(message = "Estado debe ser Activo o Inactivo")
    private Estado estado;
}
