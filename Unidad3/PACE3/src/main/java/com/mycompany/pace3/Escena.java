package com.mycompany.pace3;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.Query;

@Entity
@Table(name = "Escena")
@Data
@NoArgsConstructor
public class Escena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String descripcion;

    @Column
    private int duracion;

    @ManyToMany(mappedBy = "escenas")
    private List<Actor> actores = new ArrayList<>();

    public Escena(int id, String descripcion, int duracion) {
        this.id = id;
        this.descripcion = descripcion;
        this.duracion = duracion;
    }

    public static void añadirEscena(Session session, String descripcion, int duracion) {
        String hql = "insert into Escena (descripcion, duracion) values (?1, ?2)";
        Query query = session.createQuery(hql);
        query.setParameter(1, descripcion);
        query.setParameter(2, duracion);
        session.beginTransaction();
        query.executeUpdate();
        session.getTransaction().commit();
    }

    public static void mostrarEscenas(Session session) {
        Query<Escena> q = session.createQuery("from Escena", Escena.class);
        List<Escena> escenas = q.list();
        for (Escena escena : escenas) {
            System.out.println(escena);
        }
    }

    public static void eliminarEscena(Session session, int id) {
        String hql = "delete from Escena where id = ?1";
        Query query = session.createQuery(hql);
        query.setParameter(1, id);
        session.beginTransaction();
        query.executeUpdate();
        session.getTransaction().commit();
    }

    public static void editarEscena(Session session, int id) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("¿Qué campo deseas editar?");
        System.out.println("1. Descripción");
        System.out.println("2. Duración");
        int opcion = scanner.nextInt();
        scanner.nextLine(); 

        if (opcion == 1) {
            System.out.print("Ingresa la nueva descripción: ");
            String nuevaDescripcion = scanner.nextLine();

            String hql = "update Escena set descripcion = ?1 where id = ?2";
            Query query = session.createQuery(hql);
            query.setParameter(1, nuevaDescripcion);
            query.setParameter(2, id);
            session.beginTransaction();
            query.executeUpdate();
            session.getTransaction().commit();
        } else if (opcion == 2) {
            System.out.print("Ingresa la nueva duración: ");
            int nuevaDuracion = scanner.nextInt();

            String hql = "update Escena set duracion = ?1 where id = ?2";
            Query query = session.createQuery(hql);
            query.setParameter(1, nuevaDuracion);
            query.setParameter(2, id);
            session.beginTransaction();
            query.executeUpdate();
            session.getTransaction().commit();
        }
    }
}
