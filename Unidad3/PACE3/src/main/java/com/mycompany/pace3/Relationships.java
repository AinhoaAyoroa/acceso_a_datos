package com.mycompany.pace3;

import java.util.Scanner;
import org.hibernate.Session;

public class Relationships {

    public static void main(String[] args) {
        // Iniciar sesión y transacción
        try (Session laSesion = HibernateUtil.getSessionFactory().getCurrentSession()) {
            laSesion.beginTransaction();  // Iniciar la transacción

            try (Scanner scanner = new Scanner(System.in)) {
                int opcion = 0;

                while (opcion != 7) {
                    System.out.println("====== Menú principal ======");
                    System.out.println("1. Gestionar Pelicula");
                    System.out.println("2. Gestionar Escena");
                    System.out.println("3. Gestionar Personaje");
                    System.out.println("4. Gestionar Productora");
                    System.out.println("5. Gestionar Actor");
                    System.out.println("6. Gestionar Director");
                    System.out.println("7. Salir");
                    System.out.println("0. Ayuda");
                    System.out.print("Selecciona una opcion: ");

                    opcion = scanner.nextInt();

                    switch (opcion) {
                        case 1:
                            System.out.println("====== Menú pelicula ======");
                            int opcionPelicula;
                            do {
                                System.out.println("1. Añadir Pelicula");
                                System.out.println("2. Editar Pelicula");
                                System.out.println("3. Eliminar Pelicula");
                                System.out.println("4. Mostrar peliculas");
                                System.out.println("5. Volver al menú principal");
                                System.out.print("Selecciona una opcion: ");
                                opcionPelicula = scanner.nextInt();

                                switch (opcionPelicula) {

                                    case 1:
                                        Scanner pelicula = new Scanner(System.in);

                                        System.out.println("Introduce el titulo de la pelicula: ");
                                        String titulo = pelicula.nextLine();

                                        System.out.println("Introduce el año de la pelicula: ");
                                        int year = Integer.parseInt(pelicula.nextLine());

                                        System.out.println("Introduce el genero de la pelicula: ");
                                        String genero = pelicula.nextLine();

                                        System.out.println("Introduce el id de la productora: ");
                                        int idProductora = Integer.parseInt(pelicula.nextLine());

                                        System.out.println("Introduce el id del director: ");
                                        int idDirector = Integer.parseInt(pelicula.nextLine());

                                        Pelicula.añadirPelicula(laSesion, titulo, year, genero, idProductora, idDirector);
                                        break;

                                    case 2:
                                        System.out.println("Introduce el ID de la pelicula a modificar: ");
                                        int id = scanner.nextInt();
                                        Pelicula.editarPelicula(laSesion, id);
                                        break;
                                    case 3:
                                        System.out.println("Introduce el ID de la pelicula a eliminar: ");
                                        id = scanner.nextInt();
                                        Pelicula.eliminarPelicula(laSesion, id);
                                        break;
                                    case 4:
                                        Pelicula.mostrarPeliculas(laSesion);
                                        break;
                                    case 5:
                                        System.out.println("volviendo al menú principal...");
                                    default:
                                        System.out.println("Opcion invalida en el menú pelicula.");
                                        break;
                                }
                            } while (opcionPelicula != 5);
                            break;

                        case 2:
                            System.out.println("====== Menú escena ======");
                            int opcionEscena;
                            do {
                                System.out.println("1. Añadir escena");
                                System.out.println("2. Editar escena");
                                System.out.println("3. Eliminar escena");
                                System.out.println("4. Mostrar escenas");
                                System.out.println("5. Volver al menú principal");
                                System.out.print("Selecciona una opcion: ");
                                opcionEscena = scanner.nextInt();

                                switch (opcionEscena) {
                                    case 1:
                                        Scanner escena = new Scanner(System.in);
                                        System.out.println("Introduce la descripcion de la escena: ");
                                        String descripcion = escena.nextLine();

                                        Escena.añadirEscena(laSesion, descripcion, opcion);
                                        break;
                                    case 2:
                                        System.out.println("Editando escena...");
                                        break;
                                    case 3:
                                        System.out.println("Eliminando escena...");
                                        break;
                                    case 4:
                                        Escena.mostrarEscenas(laSesion);
                                        break;
                                    case 5:
                                        System.out.println("Volviendo al menú principal...");
                                    default:
                                        System.out.println("Opcion invalida en el menú escena.");
                                        break;
                                }
                            } while (opcionEscena != 5);
                            break;

                        case 3:
                            System.out.println("====== Menú personaje ======");
                            int opcionPersonaje;
                            do {
                                System.out.println("1. Añadir personaje");
                                System.out.println("2. Editar personaje");
                                System.out.println("3. Eliminar personaje");
                                System.out.println("4. Mostrar personajes");
                                System.out.println("5. Volver al menú principal");
                                System.out.print("Selecciona una opcion: ");
                                opcionPersonaje = scanner.nextInt();

                                switch (opcionPersonaje) {
                                    case 1:
                                        Scanner personaje = new Scanner(System.in);
                                        System.out.println("Introduce el nombre del personaje: ");
                                        String nombre = personaje.nextLine();

                                        System.out.println("Introduce el ID del actor: ");
                                        int actorId = personaje.nextInt();

                                        Actor actor = laSesion.get(Actor.class, actorId);
                                        if (actor == null) {
                                            System.out.println("Actor no encontrado con el ID: " + actorId);
                                            break;
                                        }

                                        System.out.println("Introduce el ID de la pelicula: ");
                                        int peliculaId = personaje.nextInt();
                                        personaje.nextLine();

                                        Pelicula pelicula = laSesion.get(Pelicula.class, peliculaId);
                                        if (pelicula == null) {
                                            System.out.println("Pelicula no encontrada con el ID: " + peliculaId);
                                            break;
                                        }

                                        Personaje.añadirPersonaje(laSesion, nombre, actor, pelicula);
                                        break;
                                    case 2:
                                        System.out.println("Editando personaje...");
                                        break;
                                    case 3:
                                        System.out.println("Eliminando personaje...");
                                        break;
                                    case 4:
                                        Personaje.mostrarPersonajes(laSesion);
                                        break;
                                    case 5:
                                        System.out.println("Volviendo al menú principal...");
                                    default:
                                        System.out.println("Opcion invalida en el menú personaje.");
                                        break;
                                }
                            } while (opcionPersonaje != 5);
                            break;

                        case 4:
                            System.out.println("====== Menú productora ======");
                            int opcionProductora;
                            do {
                                System.out.println("1. Añadir productora");
                                System.out.println("2. Editar productora");
                                System.out.println("3. Eliminar productora");
                                System.out.println("4. Mostrar productoras");
                                System.out.println("5. Volver al menú principal");
                                System.out.print("Selecciona una opcion: ");
                                opcionProductora = scanner.nextInt();

                                switch (opcionProductora) {
                                    case 1:

                                        Scanner productora = new Scanner(System.in);
                                        System.out.println("Introduce el nombre de la productora: ");
                                        String nombreProductora = productora.nextLine();

                                        if (!laSesion.getTransaction().isActive()) {
                                            laSesion.beginTransaction();
                                        }

                                        try {
                                            Productora.añadirProductora(laSesion, nombreProductora);
                                            laSesion.getTransaction().commit();
                                            System.out.println("Productora añadida correctamente.");
                                        } catch (Exception e) {
                                            if (laSesion.getTransaction().isActive()) {
                                                laSesion.getTransaction().rollback();
                                                System.out.println("Se realizó un rollback debido a un error.");
                                            }
                                            e.printStackTrace();
                                        }
                                        break;

                                    case 2:
                                        System.out.println("Editando productora...");
                                        break;
                                    case 3:
                                        System.out.println("Eliminando productora...");
                                        break;
                                    case 4:
                                        Productora.mostrarProductoras(laSesion);
                                        break;
                                    case 5:
                                        System.out.println("Volviendo al menú principal...");
                                    default:
                                        System.out.println("Opcion invalida en el menú productora.");
                                        break;
                                }
                            } while (opcionProductora != 5);
                            break;

                        case 5:
                            System.out.println("====== Menú actor ======");
                            int opcionActor;
                            do {
                                System.out.println("1. Añadir actor");
                                System.out.println("2. Editar actor");
                                System.out.println("3. Eliminar actor");
                                System.out.println("4. Mostrar actores");
                                System.out.println("5. Volver al menú principal");
                                System.out.print("Selecciona una opcion: ");
                                opcionActor = scanner.nextInt();

                                switch (opcionActor) {
                                    case 1:
                                        Scanner actor = new Scanner(System.in);
                                        System.out.println("Introduce el nombre del actor: ");
                                        String nombre = actor.nextLine();
                                        System.out.println("Introduce la edad del actor: ");
                                        int edad = actor.nextInt();
                                        
                                        Actor.añadirActor(laSesion, nombre, edad);
                                        break;
                                    case 2:
                                        System.out.println("Editando actor...");
                                        break;
                                    case 3:
                                        System.out.println("Eliminando actor...");
                                        break;
                                    case 4:
                                        Actor.mostrarActores(laSesion);
                                        break;
                                    case 5:
                                        System.out.println("Volviendo al menú principal...");
                                    default:
                                        System.out.println("Opcion invalida en el menú actor.");
                                        break;
                                }
                            } while (opcionActor != 5);
                            break;

                        case 6:
                            System.out.println("====== Menú director ======");
                            int opcionDirector;
                            do {
                                System.out.println("1. Añadir director");
                                System.out.println("2. Editar director");
                                System.out.println("3. Eliminar director");
                                System.out.println("4. Mostrar directores");
                                System.out.println("5. Volver al menú principal");
                                System.out.print("Selecciona una opcion: ");
                                opcionDirector = scanner.nextInt();

                                switch (opcionDirector) {
                                    case 1:
                                        Scanner director = new Scanner(System.in);
                                        System.out.println("Introduce el nombre del director: ");
                                        String nombre = director.nextLine();
                                        System.out.println("Introduce la experiencia del director (en años): ");
                                        int experiencia = director.nextInt();
                                        
                                        Director.añadirDirector(laSesion, nombre, experiencia);
                                        break;
                                    case 2:
                                        System.out.println("Editando director...");
                                        break;
                                    case 3:
                                        System.out.println("Eliminando director...");
                                        break;
                                    case 4:
                                        Director.mostrarDirectores(laSesion);
                                        break;
                                    case 5:
                                        System.out.println("Volviendo al menú principal...");
                                    default:
                                        System.out.println("Opcion invalida en el menú director.");
                                        break;
                                }
                            } while (opcionDirector != 5);
                            break;

                        case 7:
                            // Al salir, hacer commit para guardar cambios
                            laSesion.getTransaction().commit();
                            System.out.println("Transacción completada y cambios guardados.");
                            break;

                        case 0:
                            System.out.println("Ayuda:");
                            break;

                        default:
                            System.out.println("Opción no válida.");
                            break;
                    }
                }
            } catch (Exception e) {
                if (laSesion.getTransaction().isActive()) {
                    laSesion.getTransaction().rollback(); 
                    System.out.println("Error en la transacción. Se realizó un rollback.");
                }
                e.printStackTrace();
            }
        }
    }
}
