package com.mycompany.historialmedico;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.ToString;

/**
 *
 * @author noa
 */
@Data
@Entity
@Table(name="Persona")
public class Persona implements Serializable{
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int idPersona;
    @Column
    private String dni;
    @Column
    private int edad;
    @Column
    private Date nacimiento;
    @Column
    private String direccion;
    @Column
    private String cp;
    
    @OneToOne(mappedBy="persona")
    @PrimaryKeyJoinColumn
    @ToString.Exclude
    private HistorialMedico historial;

    public Persona(String dni, int edad, String direccion, String cp) {
        this.dni = dni;
        this.edad = edad;
        this.direccion = direccion;
        this.cp = cp;
    }
    
    
}
