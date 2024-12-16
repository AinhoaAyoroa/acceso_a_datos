
package com.mycompany.unoamuchos;

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
import java.util.Set;
import lombok.Data;

/**
 *
 * @author noa
 */

@Entity
@Table(name="Libro")
@Data
public class Autor implements Serializable{

    static final long serialVersionUID = 137L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idAutor;

    @Column
    private String nom;

    @Column
    private String nacionalitat;

    @OneToMany(mappedBy="elAutor",
            cascade=CascadeType.PERSIST,
            fetch = FetchType.LAZY)
    private Set<Libro> elsLlibres;

}