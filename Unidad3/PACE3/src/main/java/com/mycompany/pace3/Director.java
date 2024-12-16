package com.mycompany.pace3;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Scanner;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Entity
@Table(name = "Director")
@Data
@NoArgsConstructor
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private int experiencia;

    public static void añadirDirector(Session session, String nombre, int experiencia) {
        try {
            Director director = new Director();
            director.setNombre(nombre);
            director.setExperiencia(experiencia);
            session.save(director);
            System.out.println("Director añadido correctamente con ID: " + director.getId());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al añadir el director.");
        }
    }

    public static void mostrarDirectores(Session session) {
        try {
            session.beginTransaction();
            Query<Director> query = session.createQuery("FROM Director", Director.class);
            List<Director> directores = query.list();
            for (Director director : directores) {
                System.out.println(director);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public static void eliminarDirector(Session session, int id) {
        try {
            String hql = "delete from Director where id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            query.executeUpdate();
            System.out.println("Director eliminado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al eliminar el director.");
        }
    }

    public static void editarDirector(Session session, int id) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("¿Qué campo deseas editar?");
            System.out.println("1. Nombre");
            System.out.println("2. Experiencia");
            System.out.print("Elige el número del campo (1-2) o presiona 0 para salir: ");
            int opcion = scanner.nextInt();
            String sql = "update Director set ";
            boolean campoEditado = false;
            String nombreNuevo = null;
            Integer experienciaNueva = null;
            switch (opcion) {
                case 1:
                    scanner.nextLine();  
                    System.out.print("Introduce el nuevo nombre: ");
                    nombreNuevo = scanner.nextLine();
                    sql += "nombre = :nuevoNombre, ";
                    campoEditado = true;
                    break;
                case 2:
                    System.out.print("Introduce la nueva experiencia: ");
                    experienciaNueva = scanner.nextInt();
                    sql += "experiencia = :nuevaExperiencia, ";
                    campoEditado = true;
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida.");
                    return;
            }
            if (campoEditado) {
                sql = sql.substring(0, sql.length() - 2);
                sql += " where id = :id";
                Query query = session.createQuery(sql);
                if (nombreNuevo != null) {
                    query.setParameter("nuevoNombre", nombreNuevo);
                }
                if (experienciaNueva != null) {
                    query.setParameter("nuevaExperiencia", experienciaNueva);
                }
                query.setParameter("id", id);

                session.beginTransaction();
                query.executeUpdate();
                session.getTransaction().commit();

                System.out.println("Director actualizado correctamente.");
            } else {
                System.out.println("No se ha realizado ninguna modificación.");
            }
            scanner.close();
        }
    }
}
