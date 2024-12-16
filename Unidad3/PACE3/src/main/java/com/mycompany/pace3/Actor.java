package com.mycompany.pace3;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.Query;

@Entity
@Table(name = "Actor")
@Data
@NoArgsConstructor
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nombre;

    @Column
    private int edad;

    @OneToOne(mappedBy = "actor", cascade = CascadeType.ALL)
    private Personaje personaje;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "Actua",
            joinColumns = {
                @JoinColumn(name = "Escena", foreignKey = @ForeignKey(name = "FK_DOC_ESC"))},
            inverseJoinColumns = {
                @JoinColumn(name = "Actor", foreignKey = @ForeignKey(name = "FK_DOC_ACT"))})
    @EqualsAndHashCode.Exclude
    private List<Escena> escenas = new ArrayList<>();

    public static void añadirActor(Session session, String nombre, int edad) {
        String sql = "INSERT INTO ACTOR (nombre, edad) VALUES (?1, ?2)";
        Query query = session.createQuery(sql);
        query.setParameter(1, nombre);
        query.setParameter(2, edad);
        session.beginTransaction();
        query.executeUpdate();
        session.getTransaction().commit();
    }

    public static void mostrarActores(Session session) {
        Query<Actor> query = session.createQuery("FROM Actor", Actor.class);
        List<Actor> actores = query.list();
        for (Actor actor : actores) {
            System.out.println(actor);
        }
    }

    public static void eliminarActor(Session session, int id) {
        String sql = "DELETE FROM Actor WHERE ID = ?1";
        Query query = session.createQuery(sql);
        query.setParameter(1, id);
        session.beginTransaction();
        query.executeUpdate();
        session.getTransaction().commit();
    }

    public static void editarActor(Session session, int id) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("¿Qué deseas editar del actor?");
        System.out.println("1. Nombre");
        System.out.println("2. Edad");
        System.out.print("Selecciona una opción: ");
        int opcion = scanner.nextInt();

        String hql = "UPDATE Actor SET ";
        String nuevoValor = "";
        boolean campoSeleccionado = false;

        switch (opcion) {
            case 1:
                System.out.print("Ingresa el nuevo nombre: ");
                nuevoValor = scanner.nextLine();
                hql += "nombre = ?1 ";
                campoSeleccionado = true;
                break;
            case 2:
                System.out.print("Ingresa la nueva edad: ");
                nuevoValor = String.valueOf(scanner.nextInt());
                hql += "edad = ?1 ";
                campoSeleccionado = true;
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }

        if (campoSeleccionado) {
            hql += "WHERE id = ?2";
            Query query = session.createQuery(hql);
            query.setParameter(1, nuevoValor);
            query.setParameter(2, id);
            session.beginTransaction();
            query.executeUpdate();
            session.getTransaction().commit();
            System.out.println("Actor actualizado correctamente.");
        }
    }
}
