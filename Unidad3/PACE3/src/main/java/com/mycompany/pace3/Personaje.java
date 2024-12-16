package com.mycompany.pace3;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Entity
@Table(name = "Personaje")
@Data
@NoArgsConstructor
public class Personaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nombre;

    @OneToOne
    @JoinColumn(name = "id_actor", unique = true)
    private Actor actor;

    @ManyToOne
    @JoinColumn(name = "id_pelicula")
    private Pelicula pelicula;

    public static void añadirPersonaje(Session session, String nombre, Actor actor, Pelicula pelicula) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Personaje personaje = new Personaje();
            personaje.setNombre(nombre);
            personaje.setActor(actor);
            personaje.setPelicula(pelicula);
            session.save(personaje);
            transaction.commit();
            System.out.println("Personaje añadido correctamente con ID: " + personaje.getId());
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            System.out.println("Error al añadir el personaje.");
        }
    }

    public static void mostrarPersonajes(Session session) {
        Query<Personaje> query = session.createQuery("from Personaje", Personaje.class);
        List<Personaje> personajes = query.list();
        personajes.forEach(System.out::println);
    }

    public static void eliminarPersonaje(Session session, int id) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String hql = "delete from Personaje where id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
            System.out.println("Personaje eliminado correctamente.");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public static void editarPersonaje(Session session, int id, String nuevoNombre) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String hql = "update Personaje set nombre = :nuevoNombre where id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("nuevoNombre", nuevoNombre);
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
            System.out.println("Personaje editado correctamente.");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}