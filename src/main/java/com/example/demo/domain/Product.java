package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Entidad Product - representa un coche en el concesionario Lamborghini
 * Los campos "producto" del enunciado se mapean a características del vehículo
 * @author Alexander
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // nombre = modelo del coche (ej: "Huracán EVO")
    @Column(nullable = false)
    private String name;

    // descripción = descripción del vehículo
    @Column(length = 2000)
    private String description;

    // precio = precio del coche en euros
    @Column(nullable = false)
    private Double price;

    // stock = unidades disponibles
    @Column(nullable = false)
    private Integer stock;

    // categoría = línea del modelo (ej: "Huracán", "Urus", "Revuelto")
    @Column(nullable = false)
    private String category;

    // Campos extra del proyecto personal
    @Column(name = "model_year")   // ✅ "year" es palabra reservada en H2
    private Integer year;

    private Integer horsepower;    // caballos de potencia (CV)
    private String transmission;   // "Manual" o "Automático"
    private String color;          // color del coche
    private String imageUrl;       // URL de la imagen del vehículo
}