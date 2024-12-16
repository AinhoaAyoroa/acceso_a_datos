/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ieseljust.relationships.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author jasanchez
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Engine {
    
    static final long serialVersionUID = 17L;
     
    @Column
    String tipus_combustible;
    
    @Column
    int cc;
    
    @Column
    private String brand_engine;
        
}
