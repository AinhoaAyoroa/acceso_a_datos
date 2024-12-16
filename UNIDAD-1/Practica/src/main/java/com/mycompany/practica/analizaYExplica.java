/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.practica;

import java.io.File;

/**
 *
 * @author ainho
 */
public class analizaYExplica {

    public static void main(String[] args) {
        // creamos un String llamado ruta en el que se almacenará la ruta que le pongamos desde
        // la línea de comandos, luego, creamos un File en el que le ponemos dicha ruta
        String ruta = args[0];
        File f = new File(ruta);
        // comprueba que la ruta existe y en caso de existir comprueba si es un fichero
        if (f.exists()) {
            // si es un fichero te dice el tamaño y sus permisos
            if (f.isFile()) {
                System.out.println("El tamaño es de " + f.length());
                System.out.println("Puede ejecturase: " + f.canExecute());
                System.out.println("Puede leerse: " + f.canRead());
                System.out.println("Puede escribirse: " + f.canWrite());
            // si no es un fichero asume que es un directorio y te dice lo que contiene    
            } else {
                String[] losArchivos = f.list();
                System.out.println("El directorio " + ruta + " contiene:");
                for (String archivo : losArchivos) {
                    System.out.println("\t" + archivo);
                }
            }
        // si no es niguna de esas dos opciones entonces está mal    
        } else {
            System.out.println("El fichero o ruta no existe");
        }
    }
}
