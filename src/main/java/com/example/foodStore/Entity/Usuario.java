package com.example.foodStore.Entity;

import com.example.foodStore.Entity.Enums.Rol;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;
    private String Apellido;
    private String email;
    private int celular;
    private String contrasena;
    private Rol rol;

}
