/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.unoamuchos;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author noa
 */
@Entity
@Table(name="Libro")
@Data
@NoArgsConstructor
public class Libro implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idLibro;
    @Column
    private String titol;
    @Column
    private String tipus;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="idAutor",
                foreignKey = @ForeignKey(name = "FK_LIB_AUT" ))
    private Autor elAutor;
}