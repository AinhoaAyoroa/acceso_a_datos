package com.ad.ieseljust;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

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

    /**
     * **************************************
     * TO-DO: Mostrarà la informació del bloc per la consola (numero i propietari)
     * i per cada transaccio una linea amb el seu contingut. En text, evidentment.
     * 
     * Per exemple: 
     * 
     * "Bloque 1 Jose"
     * "Transaction 1 a b 225"
     * "Transaction 1 a c -75"
     * 
     * ************************************************************************
     */
    public String toTXT() {
        String txt = "Bloque " + this.numbloque + " " + this.propietario;

        if (!this.ListaTransacciones.isEmpty()) {
            for (Transaction tr : this.ListaTransacciones) {
                txt = txt + "\n" + tr.toTXT();
            }
        }
        return txt;
    }
    
    /**
     * **************************************
     * TO-DO: Convertira la informació del bloc a xml
     * 
     * Per exemple: 
     * 
     * <bloque numbloque="1" propietario="Jose">
     *      <transaccion destino="b" importe="225" origen="a"/>
     *      <transaccion destino="c" importe="-75" origen="a"/>
     * </bloque>
     * 
     * ***************************************
     */
    public void toXML(Document document, Element padre) {

        Element e = document.createElement("bloque");
        if (padre == null) {
            padre.appendChild(e);
        } else {
            padre.appendChild(e);
        }

        e.setAttribute("numbloque", String.valueOf(this.numbloque));
        e.setAttribute("propietario", this.propietario);

        padre.appendChild(e);

        for (Transaction tr : this.ListaTransacciones) {
            tr.toXML(document, e);
        }

    }
    
    /**
     * **************************************
     * TO-DO: Convertira la informació del bloc a JSON
     *        - Es creara un nou JSONObject (que retornarem) 
     *        - afegirem la informacio del bloc amb el metode put de JSONObject
     *        - per cada transaccio, si en té, es cridara al metode toJSON de la 
     *          classe Transaction per omplir el JSONArray amb la info de les
     *          tornada (metode put de JSONArray)
     * 
     * "bloque": 
        {
            "propietario": "Jose",
            "numbloque": "1",
            "transacciones": [
                {
                    "transaccion": 
                    {
                        "origen": "a",
                        "destino": "b",
                        "importe": 225
                    }
                },
                    {
                    "transaccion": 
                    {
                        "origen": "a",
                        "destino": "c",
                        "importe": -75
                     }
                }
            ]
        }
     * 
     * ***************************************
     */
    public JSONObject toJSON() {
        JSONObject objJSON = new JSONObject();

        JSONArray listaTransacciones = new JSONArray();

        for (Transaction transaccion : this.ListaTransacciones) {
            listaTransacciones.put(transaccion.toJSON());
        }
        objJSON.put("numbloque", String.valueOf(this.numbloque));
        objJSON.put("propietario", this.propietario);
        objJSON.put("transacciones", listaTransacciones);

        return new JSONObject().put("bloque", objJSON);
    }

}
