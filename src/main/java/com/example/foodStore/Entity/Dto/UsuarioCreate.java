package com.example.foodStore.Entity.Dto;

import com.example.foodStore.Entity.Enums.Rol;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class UsuarioCreate {

    String nombre;
    String apellido;
    String email;
    String contrasena;

}
