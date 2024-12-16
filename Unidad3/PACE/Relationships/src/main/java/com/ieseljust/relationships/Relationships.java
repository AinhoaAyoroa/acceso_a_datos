/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.ieseljust.relationships;

import com.ieseljust.relationships.models.Autor;
import com.ieseljust.relationships.models.Car;
import com.ieseljust.relationships.models.Engine;
import com.ieseljust.relationships.models.Grup;
import com.ieseljust.relationships.models.Libro;
import com.ieseljust.relationships.models.Modul;
import com.ieseljust.relationships.models.Professor;
import com.ieseljust.relationships.utils.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author jasanchez
 */
public class Relationships {

    public static void main(String[] args) {
        Session laSesion = HibernateUtil.getSessionFactory().getCurrentSession();

        laSesion.getTransaction().begin();
        /* 
        Engine e1 = new Engine("Diésel", 2000, "Seat");
        Engine e2 = new Engine("Diésel", 2010, "Ford");
        Car c = new Car("Audi", "Q3", "2525-FGV", 2020, e1, e2);
        
        laSesion.persist(c);
         */
 /*  
        Professor p = new Professor("José Luis López");
                
        Grup g = new Grup("2n DAM", 2);
        
        g.setTutor(p);
        p.setSocTutor(g);
       
        laSesion.persist(g);
        
        
        System.out.println(g);
         */

        /*Autor a1 = new Autor("J.K. Rowling", "Britain");
        Autor a2 = new Autor("J.R.R. Tolkien", "USA");

        Libro l1 = new Libro("La piedra filosofal", "Fantasia");
        l1.setElAutor(a1);
        Libro l2 = new Libro("La cámara secreta", "Fantasia");
        l2.setElAutor(a1);
        Libro l3 = new Libro("El prisionero de Azkaban", "Fantasia");
        l3.setElAutor(a1);

        laSesion.persist(l1);
        laSesion.persist(l2);
        laSesion.persist(l3);

        Libro l4 = new Libro("La Comunidad del Anillo", "Fantasia");
        Libro l5 = new Libro("Las dos Torres", "Fantasia");
        Libro l6 = new Libro("El retorno del Rey", "Fantasia");
        
        l4.setElAutor(a2);
        l5.setElAutor(a2);
        l6.setElAutor(a2);
        
        a2.addLibro(l4);
        a2.addLibro(l5);
        a2.addLibro(l6);
        
        laSesion.persist(a2);
        
        Autor a2 = laSesion.get(Autor.class, 2);
        System.out.println(a2);
        //a2.mostrarLibros();
                */

        Professor p1 = new Professor("José Antonio");
        Professor p2 = new Professor("José Alfredo");
        
        Modul m1 = new Modul("AD", 8);
        Modul m2 = new Modul("PSP", 3);
        Modul m3 = new Modul("SGE", 5);
        Modul m4 = new Modul("EEIE", 3);
        Modul m5 = new Modul("PMDM", 5);
        Modul m6 = new Modul("DI", 6);
        
        /*p1.addModul(m1);
        p1.addModul(m3);
        p1.addModul(m5);
        
        p2.addModul(m2);
        p2.addModul(m5);
        p2.addModul(m6);
        */
        m1.addProfessor(p1);
        m1.addProfessor(p2);
        
        m2.addProfessor(p2);
        
        m3.addProfessor(p1);
        m4.addProfessor(p2);
        
        m6.addProfessor(p1);
        m6.addProfessor(p2);
        
        laSesion.persist(p1);
        laSesion.persist(p2);
        
        
        //close all
        laSesion.getTransaction().commit();
        laSesion.close();

        System.out.println("Hibernate funcionant");
    }
}
