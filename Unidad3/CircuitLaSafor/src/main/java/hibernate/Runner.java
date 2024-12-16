package hibernate;

import lombok.Data;

/**
 *
 * @author dani
 */
@Data
public class Runner {

    private long idCorredor;
    private String dni;
    private String nom;
    private String cognoms;
    private int edat;
    private long dorsal;
    private String categoria;
    private String localitat;
    private String email;
}
