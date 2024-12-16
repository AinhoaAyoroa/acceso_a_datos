/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.historialmedico;

import org.hibernate.Session;

/**
 *
 * @author noa
 */
public class Relationships {

    public static void main(String[] args) {
        try (Session laSesion = HibernateUtil.getSessionFactory().getCurrentSession()) {
            laSesion.getTransaction().begin();

            Persona persona = new Persona("114568937B", 20, "Calle street", "56344");
            HistorialMedico historial = new HistorialMedico(true);

            historial.setPersona(persona);

            laSesion.persist(historial);
            
            laSesion.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en las relaciones: " + e);
        }

        System.out.println("Hibernate funcionando");

    }
}
