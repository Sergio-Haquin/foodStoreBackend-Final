package com.example.foodStore.Entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;
    private String descripcion;

    @Builder.Default
    private boolean eliminado = false;

}