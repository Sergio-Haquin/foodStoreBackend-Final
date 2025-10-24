package com.example.foodStore.Entity.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class UsuarioLogin {

    String email;
    String contrasena;

}
