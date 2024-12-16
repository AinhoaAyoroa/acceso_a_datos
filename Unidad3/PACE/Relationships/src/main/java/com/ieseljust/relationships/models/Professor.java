/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ieseljust.relationships.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;

/**
 *
 * @author jasanchez
 */
@Data
@NoArgsConstructor
@Entity
@Table(name="teacher")
public class Professor implements Serializable{
    
    static final long serialVersionUID = 17L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
    private Long idProfe;
        
    @Column
    private String nom;

    public Professor(String nom) {
        this.nom = nom;
    }
    
    @ToString.Exclude
    @OneToOne(mappedBy = "tutor")
    private Grup socTutor;
    
      
    @ManyToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
        @JoinTable(name="Docencia",
          joinColumns = {
              @JoinColumn(name="Profesor",foreignKey = @ForeignKey(name = "FK_DOC_PROF" ))},
          inverseJoinColumns = {
              @JoinColumn(name="Modulo",foreignKey = @ForeignKey(name = "FK_DOC_MOD" ))})   
    @EqualsAndHashCode.Exclude
    private List<Modul> elsModuls = new ArrayList<>();
    
    
    public void addModul(Modul m){ 
        if (!elsModuls.contains(m)){  
            elsModuls.add(m);    
            m.addProfessor(this);
        }
    }
}

