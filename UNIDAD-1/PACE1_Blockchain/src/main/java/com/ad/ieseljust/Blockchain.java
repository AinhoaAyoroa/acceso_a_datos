package com.ad.ieseljust;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Blockchain implements Serializable {

    //Atributs de la classe
    private int numBloques; //numero total de blocs    
    List<Block> ListaBloques= new ArrayList<>(); //Els blocs de la Blockchain s'emmagatzemen en una llista

    // Constructors
    // Constructor per defecte
    Blockchain() {        
        this.numBloques = 0;

        // Inicialitzem la llista de blocs
        ListaBloques = new ArrayList<Block>();

    }

    public int getNumBloques() {
        return numBloques;
    }

    // Retorna si la Blockchain té o no blocs
    public Boolean esBuida() {
        return this.ListaBloques.isEmpty();
    }

    /*  
        Aquest mètode afig un objecte de tipus Block
        al Blockchain.
    */
    public void add(Block bloque) {        
        // Si tot és correte, afegim el nou block a la llista.
        this.ListaBloques.add(bloque);
    }

    /* Mostrarà per l'eixida estael contingut de la Blockchain */
    @Override
    public String toString() {
        String txt="";
        
        for (Block b : this.ListaBloques) {
            txt = txt + b.toTXT() + "\n";               
        }        
        return txt;

    }
}
