package com.example.foodStore.Entity.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class ProductoCreate {
    Long id;
    String nombre;
    String src;
    double precio;
    Long idCategoria;
    Long stock;

}
