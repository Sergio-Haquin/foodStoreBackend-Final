package com.example.foodStore.Entity.Dto;

import com.example.foodStore.Entity.Enums.Rol;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {

   Long idUsuario;
    String nombre;
    String apellido;
    String email;
    Rol rol;

}
