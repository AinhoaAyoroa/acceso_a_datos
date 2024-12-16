package hibernate;

import lombok.Data;

/**
 *
 * @author dani
 */
@Data
public class carrera {

    private long id;
    private String nom;
    private String localitat;
    private int distancia_km;
    private long organitzador;
    private String num_participants;
}
