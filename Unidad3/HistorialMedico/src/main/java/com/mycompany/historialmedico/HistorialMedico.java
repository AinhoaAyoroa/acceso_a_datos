package com.mycompany.historialmedico;

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
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author noa
 */
@Data
@Entity
@Table(name="Historial")
@NoArgsConstructor
public class HistorialMedico {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int idHistorial;
    
    @Column
    private Date fecha;
    @Column
    private boolean baja;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(
            name = "persona",
            foreignKey = @ForeignKey(name = "FK_GRP_PERSONA"))
    private Persona persona;    

    public HistorialMedico(boolean baja) {
        this.baja = baja;
    }  
    
}
