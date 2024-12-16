package com.ad.ieseljust;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.*;
import org.json.JSONArray;
import org.json.JSONObject;

class Block implements Serializable {

    private int numbloque;
    private String propietario;
    List<Transaction> ListaTransacciones = new ArrayList<>();

    // Constructors
    Block() { // By default (empty arguments)   

    }

    Block(int num, String nomPropietario) {
        // Amb paràmetres:        
        this.numbloque = num;
        this.propietario = nomPropietario;
    }

    public int getNumbloque() {
        return numbloque;
    }

    public void addTransaction(Transaction tr) {
        this.ListaTransacciones.add(tr);
    }

    public String toTXT() {
        String txt = "Bloque " + this.numbloque + " " + this.propietario;

        if (!this.ListaTransacciones.isEmpty()) {
            for (Transaction transaccion : this.ListaTransacciones) {
                txt = txt + "\n" + transaccion.toTXT();
            }
        }
        return txt;
    }

    /**
     * **************************************
     * TO-DO: Convertira la informació del bloc a xml Necessitarem passar-li el
     * Document i un element pare al qual annexar el nou element del dom per de
     * la transaccio
     *
     * Per exemple:
     *
     * <bloque numbloque="1" propietario="Jose">
     * <transaccion destino="b" importe="225" origen="a"/>
     * <transaccion destino="c" importe="-75" origen="a"/>
     * </bloque> ***************************************
     */
    public void toXML(Document document, Element padre) {
        Element elemento = document.createElement("bloque");
        if (padre == null) {
            padre.appendChild(elemento);
        } else {
            padre.appendChild(elemento);
        }

        elemento.setAttribute("numbloque", String.valueOf(this.numbloque));
        elemento.setAttribute("propietario", this.propietario);

        padre.appendChild(elemento);

        for (Transaction transaccion : this.ListaTransacciones) {
            transaccion.toXML(document, elemento);
        }
    }

    /**
     * **************************************
     * TO-DO: Convertira la informació del bloc a JSON - Es creara un nou
     * JSONObject (que retornarem) - afegirem la informacio del bloc amb el
     * metode put de JSONObject - per cada transaccio, si en té, es cridara al
     * metode toJSON de la classe Transaction per omplir el JSONArray amb la
     * info de les tornada (metode put de JSONArray)
     * ***************************************
     */
    public JSONObject toJSON() {
        JSONObject jsonBloque = new JSONObject();
        jsonBloque.put("numbloque", numbloque);
        jsonBloque.put("propietario", propietario);

        JSONArray transaccionesArray = new JSONArray();
        for (Transaction transaccion : ListaTransacciones) {
            transaccionesArray.put(transaccion.toJSON());
        }

        if (!transaccionesArray.isEmpty()) {
            jsonBloque.put("transacciones", transaccionesArray);
        }

        return jsonBloque;
    }
}
