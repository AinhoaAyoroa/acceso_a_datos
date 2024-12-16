
package com.mycompany.hibernate.Tablas;

import lombok.Data;

/**
 *
 * @author noa
 */

@Data
public class carrera {
    private int id;
    private String nom;
    private String localitat;
    private int distancia;
    private String organitzador;
    private int num_participants;
           
}
