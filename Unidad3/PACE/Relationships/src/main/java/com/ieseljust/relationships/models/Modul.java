/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ieseljust.relationships.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author jasanchez
 */

@Data
@NoArgsConstructor
@Entity
@Table(name="modul")
public class Modul implements Serializable{
    
    static final long serialVersionUID = 17L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
    private Long idModul;
        
    @Column
    private String nom;
    
    @Column
    private int hores;
    
       public Modul(String nom, int hores) {
        this.nom = nom;
        this.hores = hores;
    }
    
    
    @ManyToMany(mappedBy="elsModuls",cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private List<Professor> elsProfessors = new ArrayList<>();
    
    public void addProfessor(Professor p){   
        if (!elsProfessors.contains(p)){            
            elsProfessors.add(p);    
            p.addModul(this);
        }
    }

 

    
    
    
}
