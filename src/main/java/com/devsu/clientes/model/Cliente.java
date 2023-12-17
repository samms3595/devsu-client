package com.devsu.clientes.model;

import com.devsu.clientes.enums.Estado;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cliente extends Persona  implements Serializable{

    @Column(unique = true)
    private Long clientId;

    @ElementCollection
    private Set<Long> cuentas = new HashSet<>();

    @NotEmpty(message = "Contraseña debe ser ingresado")
    private String password;

    @NotNull(message = "Estado debe ser Activo o Inactivo")
    private Estado estado;

    public void agregarCuenta(Long cuentaId) {
        this.cuentas.add(cuentaId);
    }
    public void removerCuenta(Long cuentaId) {
        this.cuentas.remove(cuentaId);
    }
}
