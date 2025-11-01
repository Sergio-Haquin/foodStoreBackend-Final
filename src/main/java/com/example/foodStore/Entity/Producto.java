package com.example.foodStore.Entity;


import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;
    private String src;
    private double precio;
    Long stock;
    @ManyToOne
    private Categoria categoria;

    @Builder.Default
    private boolean eliminado = false;

}
