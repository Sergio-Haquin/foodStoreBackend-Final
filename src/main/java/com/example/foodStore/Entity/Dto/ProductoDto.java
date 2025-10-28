package com.example.foodStore.Entity.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDto {

    String nombre;
    String src;
    double precio;
    CategoriaDto categoria;

}
