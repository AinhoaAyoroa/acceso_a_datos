/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ad.ieseljust.ModulsToJSON;

/**
 *
 * @author jasb
 */
public class Modul {
    
    private String nom;
    private int hores;
    private double qualificacio;

    public Modul(String nom, int hores, double qualificacio) {
        this.nom = nom;
        this.hores = hores;
        this.qualificacio = qualificacio;
    }

    public double getQualificacio() {
        return qualificacio;
    }

    public void setQualificacio(double qualificacio) {
        this.qualificacio = qualificacio;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getHores() {
        return hores;
    }

    public void setHores(int hores) {
        this.hores = hores;
    }

    @Override
    public String toString() {
        return "Modul{" + "nom=" + nom + ", hores=" + hores + ", qualificacio=" + qualificacio + '}';
    }
    
    
    
}
