/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ieseljust.relationships.models;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author jasanchez
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "Coche")
public class Car implements Serializable {

    static final long serialVersionUID = 17L;

    public Car(String brand, String model, String matricula, int any_matriculacio, Engine motor1, Engine motor2) {
        this.brand = brand;
        this.model = model;
        this.matricula = matricula;
        this.any_matriculacio = any_matriculacio;
        this.motor1 = motor1;
        this.motor2 = motor2;
    }
    
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idCar;

    @Column
    private String brand;

    @Column
    private String model;

    @Column
    private String matricula;

    @Column
    private int any_matriculacio;
    
    @Embedded
    Engine motor1;
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name="tipus_combustible", column = @Column(name="tipus_combustible_m2") ),
        @AttributeOverride(name="cc", column = @Column(name="cc_m2")),
        @AttributeOverride(name="brand_engine", column = @Column(name="brand_engine_m2"))
        })        
    Engine motor2;
    
}
