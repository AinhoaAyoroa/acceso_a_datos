package com.mycompany.pace3;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Entity
@Table(name = "Productora")
@Data
@NoArgsConstructor
public class Productora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "productora", cascade = CascadeType.ALL)
    private List<Pelicula> peliculas = new ArrayList<>();

    public static void añadirProductora(Session session, String nombre) {
        Transaction transaction = null;
        try {
            if (!session.getTransaction().isActive()) {
                transaction = session.beginTransaction(); 
            }
            Productora productora = new Productora();
            productora.setNombre(nombre);
            session.save(productora);

            if (transaction != null && transaction.isActive()) {
                transaction.commit();
            }

            System.out.println("Productora añadida correctamente con ID: " + productora.getId());
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            System.out.println("Error al añadir la productora.");
        }
    }

    public static void mostrarProductoras(Session session) {
        Query<Productora> q = session.createQuery("from Productora", Productora.class);
        List<Productora> productoras = q.list();
        for (Productora productora : productoras) {
            System.out.println(productora);
        }
    }

    public static void eliminarProductora(Session session, int id) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String hql = "delete from Productora where id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
            System.out.println("Productora eliminada con éxito.");
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
                System.out.println("Se realizó un rollback debido a un error.");
            }
            e.printStackTrace();
        }
    }

    public static void editarProductora(Session session, int id, String nuevoNombre) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String hql = "update Productora set nombre = :nuevoNombre where id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("nuevoNombre", nuevoNombre);
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
            System.out.println("Productora editada correctamente.");
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
                System.out.println("Se realizó un rollback debido a un error.");
            }
            e.printStackTrace();
        }
    }

}
