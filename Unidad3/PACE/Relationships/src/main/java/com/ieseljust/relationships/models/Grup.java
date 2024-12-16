/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ieseljust.relationships.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name="grup")
public class Grup implements Serializable{
    
    static final long serialVersionUID = 17L;
        
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
    private Long idGroup;
        
    @Column
    private String nivel;
    
    @Column
    private int curso;
    
        
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(
      name="elTutor",
      foreignKey = @ForeignKey(name = "FK_GRP_PROFE"))
    private Professor tutor;

    public Grup(String nivel, int curso) {
        this.nivel = nivel;
        this.curso = curso;
    }
    
    
    
}
