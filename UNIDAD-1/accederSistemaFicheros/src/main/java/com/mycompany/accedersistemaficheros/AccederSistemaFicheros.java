/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.accedersistemaficheros;

import java.io.File;

/**
 *
 * @author noa
 */
public class AccederSistemaFicheros {

    public static void main(String[] args) {
        String ruta = args[0];
        File f = new File(ruta);

        if (f.exists()) {
            if (f.isFile()) {
                System.out.println("El tamaño es de " + f.length());
                System.out.println("Puede ejecturase: " + f.canExecute());
                System.out.println("Puede leerse: " + f.canRead());
                System.out.println("Puede escribirse: " + f.canWrite());
            } else {
                String[] losArchivos = f.list();
                System.out.println("El directorio " + ruta + " contiene:");
                for (String archivo : losArchivos) {
                    System.out.println("\t" + archivo);
                }
            }

        } else {
            System.out.println("El fichero o ruta no existe");
        }
    }
}
