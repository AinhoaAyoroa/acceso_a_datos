/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ad.ieseljust;

import java.io.Serializable;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author jasb
 */
public class Transaction implements Serializable{

    private int numBloque;
    private String IBANOrigen;
    private String IBANDestino;
    private double importe;

    /**
     *
     * @param nb
     */
    public Transaction(int nb) {
        this.numBloque = nb;
    }

    /**
     *
     * @param nb
     * @param IBANOrigen
     * @param IBANDestino
     * @param importe
     */
    public Transaction(int nb, String IBANOrigen, String IBANDestino, double importe) {
        this.numBloque = nb;
        this.IBANOrigen = IBANOrigen;
        this.IBANDestino = IBANDestino;
        this.importe = importe;
    }

    /**
     *
     * @return
     */
    public int getNumBloque() {
        return numBloque;
    }

    /**
     *
     * @param numBloque
     */
    public void setNumBloque(int numBloque) {
        this.numBloque = numBloque;
    }

    /**
     *
     * @return
     */
    public String getIBANOrigen() {
        return IBANOrigen;
    }

    /**
     *
     * @param IBANOrigen
     */
    public void setIBANOrigen(String IBANOrigen) {
        this.IBANOrigen = IBANOrigen;
    }

    /**
     *
     * @return
     */
    public String getIBANDestino() {
        return IBANDestino;
    }

    /**
     *
     * @param IBANDestino
     */
    public void setIBANDestino(String IBANDestino) {
        this.IBANDestino = IBANDestino;
    }

    /**
     *
     * @return
     */
    public double getImporte() {
        return importe;
    }

    /**
     *
     * @param importe
     */
    public void setImporte(double importe) {
        this.importe = importe;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Transaction " + this.numBloque + " " + this.IBANOrigen + " " + this.IBANDestino + " " + this.importe + "\n";
    }

    /**
     * **************************************
     * TO-DO: Mostrarà la informació de la transaccio per la consola (numero i propietari)
     * i per cada transaccio una linea amb el seu contingut. En text, evidentment.
     * 
     * Per exemple: 
     * "Transaction 1 a b 225"
     * 
     * ************************************************************************
     * @return 
     */
    public String toTXT() {
        return "Transaction " + this.numBloque + " " + this.IBANOrigen + " " + this.IBANDestino + " " + this.importe;               
    }
    
    
    /**
     * **************************************
     * TO-DO: Convertira la informació de la transaccio a xml 
     *        Necessitarem passar-li el Document i un element pare
     *        al qual annexar el nou element del dom per de la transaccio  
     * 
     * Per exemple: 
     * 
     * <transaccion destino="b" importe="225" origen="a"/>     
     * 
     * ***************************************
     * @param document
     * @param padre
     */
    public void toXML(Document document, Element padre) {

        Element e = document.createElement("transaccion");
        if (padre == null) {
            document.appendChild(e);
        } else {
            padre.appendChild(e);
        }

        e.setAttribute("origen", this.IBANOrigen);
        e.setAttribute("destino", this.IBANDestino);
        e.setAttribute("importe", String.valueOf(this.importe));
        padre.appendChild(e);

    }
    
    /**
     * **************************************
     * TO-DO: Convertira la informació de la transaccio a JSON
     *        - Es creara un nou JSONObject (que retornarem) i 
     *        - afegirem la informacio de la transaccio amb el 
     *          metode put de JSONObject
     *   
        "transaccion": 
         {
            "origen": "a",
            "destino": "b",
            "importe": 225
         }        
     * 
     * ***************************************
     * @return 
     */
    public JSONObject toJSON() {
        JSONObject objJSON = new JSONObject();

        objJSON.put("origen", this.IBANOrigen);
        objJSON.put("destino", this.IBANDestino);
        objJSON.put("importe", this.importe);

        return new JSONObject().put("transaccion", objJSON);
    }
}
