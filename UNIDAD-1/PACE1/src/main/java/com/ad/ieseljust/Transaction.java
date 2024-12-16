package com.ad.ieseljust;

import java.io.Serializable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.json.JSONObject;

/**
 *
 * @author jasb
 */
public class Transaction implements Serializable {

    private int numBloque;
    private String IBANOrigen;
    private String IBANDestino;
    private double importe;

    public Transaction(int nb) {
        this.numBloque = nb;
    }

    public Transaction(int nb, String IBANOrigen, String IBANDestino, double importe) {
        this.numBloque = nb;
        this.IBANOrigen = IBANOrigen;
        this.IBANDestino = IBANDestino;
        this.importe = importe;
    }

    public int getNumBloque() {
        return numBloque;
    }

    public void setNumBloque(int numBloque) {
        this.numBloque = numBloque;
    }

    public String getIBANOrigen() {
        return IBANOrigen;
    }

    public void setIBANOrigen(String IBANOrigen) {
        this.IBANOrigen = IBANOrigen;
    }

    public String getIBANDestino() {
        return IBANDestino;
    }

    public void setIBANDestino(String IBANDestino) {
        this.IBANDestino = IBANDestino;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    /**
     * **************************************
     * TO-DO: Mostrarà la informació de la transaccio per la consola (numero i
     * propietari) i per cada transaccio una linea amb el seu contingut. En
     * text, evidentment. Equivaldria al metode toSting()
     *
     * Per exemple: "Transaction 1 a b 225"
     *
     * ************************************************************************
     */
    public String toTXT() {
        return "transaccion " + this.numBloque + " " + this.IBANOrigen + " " + this.IBANDestino + " " + this.importe;
    }

    /**
     * **************************************
     * TO-DO: Convertira la informació de la transaccio a xml Necessitarem
     * passar-li el Document i un element pare al qual annexar el nou element
     * del dom per de la transaccio
     *
     * Per exemple:
     *
     * <transaccion destino="b" importe="225" origen="a"/>
     *
     * ***************************************
     */
    public void toXML(Document document, Element padre) {
        // Crear un nuevo elemento <transaccion>
        Element transaccionElement = document.createElement("transaccion");

        // Añadir atributos "origen", "destino", y "importe"
        transaccionElement.setAttribute("origen", this.IBANOrigen);
        transaccionElement.setAttribute("destino", this.IBANDestino);
        transaccionElement.setAttribute("importe", String.valueOf(this.importe));

        // Añadir el elemento transaccion al elemento padre
        padre.appendChild(transaccionElement);
    }

    /**
     * **************************************
     * TO-DO: Convertira la informació de la transaccio a JSON - Es creara un
     * nou JSONObject (que retornarem) i - afegirem la informacio de la
     * transaccio amb el metode put de JSONObject
     *
     * "transaccion": { "origen": "a", "destino": "b", "importe": 225 }
     *
     * ***************************************
     */
    public JSONObject toJSON() {
        // Crear un nuevo JSONObject
        JSONObject jsonTransaccion = new JSONObject();

        // Añadir los valores de la transacción al objeto JSON
        jsonTransaccion.put("origen", this.IBANOrigen);
        jsonTransaccion.put("destino", this.IBANDestino);
        jsonTransaccion.put("importe", this.importe);

        return jsonTransaccion;
    }
}
