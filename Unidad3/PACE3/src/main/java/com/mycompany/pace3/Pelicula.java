package com.mycompany.pace3;

import com.mycompany.pace3.Director;
import com.mycompany.pace3.Personaje;
import com.mycompany.pace3.Productora;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lombok.Data;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Entity
@Table(name = "Pelicula")
@Data
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String titulo;

    @Column
    private int year;

    @Column
    private String genero;

    @ManyToOne
    @JoinColumn(name = "id_productora")
    private Productora productora;

    @ManyToOne
    @JoinColumn(name = "id_director", unique = true)
    private Director director;

    @OneToMany(mappedBy = "pelicula", cascade = CascadeType.ALL)
    private List<Personaje> personajes = new ArrayList<>();

    public static void añadirPelicula(Session session, String titulo, int año, String genero, int productoraId, int directorId) {
        Transaction transaction = null;
        try {
            if (!session.getTransaction().isActive()) {
                transaction = session.beginTransaction(); 
            }

            Pelicula pelicula = new Pelicula();
            pelicula.setTitulo(titulo);
            pelicula.setYear(año);
            pelicula.setGenero(genero);

            Productora productora = session.get(Productora.class, productoraId);
            Director director = session.get(Director.class, directorId);

            if (productora == null || director == null) {
                System.out.println("Productora o Director no encontrados.");
                return;
            }

            pelicula.setProductora(productora);
            pelicula.setDirector(director);

            session.save(pelicula);
            
            if (transaction != null && transaction.isActive()) {
                transaction.commit();
            }

            System.out.println("Película añadida correctamente.");
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            System.out.println("Error al añadir la película.");
        }
    }

    public static void mostrarPeliculas(Session session) {
        try {
            session.beginTransaction();

            Query<Pelicula> query = session.createQuery("FROM Pelicula", Pelicula.class);
            List<Pelicula> peliculas = query.list();
            for (Pelicula pelicula : peliculas) {
                System.out.println(pelicula);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        }
    }

    public static void eliminarPelicula(Session session, int id) {
        String hql = "delete from Pelicula where id = ?1";
        Query query = session.createQuery(hql);
        query.setParameter(1, id);
        session.beginTransaction();
        query.executeUpdate();
        session.getTransaction().commit();
    }

    public static void editarPelicula(Session session, int id) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("¿Qué campo deseas editar?");
        System.out.println("1. Título");
        System.out.println("2. Año");
        System.out.println("3. Género");
        System.out.println("4. Productora");
        System.out.println("5. Director");
        System.out.print("Elige el número del campo (1-5) o presiona 0 para salir: ");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        String sql = "update Pelicula set ";
        boolean campoEditado = false;

        String tituloNuevo = null;
        Integer yearNuevo = null;
        String generoNuevo = null;
        Integer idProductora = null;
        Integer idDirector = null;

        switch (opcion) {
            case 1:
                System.out.print("Introduce el nuevo título: ");
                tituloNuevo = scanner.nextLine();
                sql += "titulo = :nuevoTitulo, ";
                campoEditado = true;
                break;
            case 2:
                System.out.print("Introduce el nuevo año: ");
                yearNuevo = scanner.nextInt();
                sql += "year = :nuevoYear, ";
                campoEditado = true;
                break;
            case 3:
                System.out.print("Introduce el nuevo género: ");
                generoNuevo = scanner.nextLine();
                sql += "genero = :nuevoGenero, ";
                campoEditado = true;
                break;
            case 4:
                System.out.print("Introduce el nuevo ID de la productora: ");
                idProductora = scanner.nextInt();
                sql += "id_productora = :idProductora, ";
                campoEditado = true;
                break;
            case 5:
                System.out.print("Introduce el nuevo ID del director: ");
                idDirector = scanner.nextInt();
                sql += "id_director = :idDirector, ";
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

            if (tituloNuevo != null) {
                query.setParameter("nuevoTitulo", tituloNuevo);
            }
            if (yearNuevo != null) {
                query.setParameter("nuevoYear", yearNuevo);
            }
            if (generoNuevo != null) {
                query.setParameter("nuevoGenero", generoNuevo);
            }
            if (idProductora != null) {
                query.setParameter("idProductora", idProductora);
            }
            if (idDirector != null) {
                query.setParameter("idDirector", idDirector);
            }
            query.setParameter("id", id);

            session.beginTransaction();
            query.executeUpdate();
            session.getTransaction().commit();

            System.out.println("Película actualizada correctamente");
        } else {
            System.out.println("No se ha realizado ninguna modificación");
        }

    }
}
