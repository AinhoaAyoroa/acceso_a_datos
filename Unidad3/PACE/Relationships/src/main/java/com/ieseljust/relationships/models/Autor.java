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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author jasanchez
 */

@Data
@NoArgsConstructor
@Entity
@Table(name="author")
public class Autor implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
    private Long idAutor;
    
    @Column
    private String nom;
    
    @Column
    private String nacionalitat;
    
    @ToString.Exclude
    @OneToMany(mappedBy="elAutor",
               cascade=CascadeType.PERSIST,
               fetch = FetchType.EAGER)        
    private List<Libro> loslibros = new ArrayList<>();

    public Autor(String nom, String nacionalitat) {
        this.nom = nom;
        this.nacionalitat = nacionalitat;
    }
    
    public void addLibro(Libro l){
            this.loslibros.add(l);      
    }
    
    public void mostrarLibros(){
        for (Libro l : loslibros) {
            System.out.println(l);
            
        }
    }
    
}
