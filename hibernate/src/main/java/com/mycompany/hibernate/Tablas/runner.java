package com.mycompany.hibernate.Tablas;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author noa
 */
@Data
//@AllArgsConstructor 
public class runner {
    private int idCorredor;
    private int dni;
    private String nom;
    private String cognoms;
    private int edat;
    private int dorsal;
    private String categoria;
    private String localitat;
    private String email;

    public runner(int dni, String nom, String cognoms, int edat, int dorsal, String categoria, String localitat, String email) {
        this.dni = dni;
        this.nom = nom;
        this.cognoms = cognoms;
        this.edat = edat;
        this.dorsal = dorsal;
        this.categoria = categoria;
        this.localitat = localitat;
        this.email = email;
    }
    
    
}
