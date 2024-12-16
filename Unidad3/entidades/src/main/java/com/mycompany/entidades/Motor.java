package com.mycompany.entidades;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.*;

/**
 *
 * @author noa
 */

@Data
@NoArgsConstructor
public class Motor {
    private boolean tipo_combustible;
    private int cc;
    private String marca;
    
}
