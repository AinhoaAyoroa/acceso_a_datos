/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.ad.ieseljust.consolefiles;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author noa
 */
public class FicheroConsolas {
     
    final int MAX_FILES_BY_COLUMN = 4;

    public void showFiles(String path, String format) {
        Scanner sc = new Scanner(System.in);

        File dir = new File(path);
        String[] lista = null;

        if (path.length() != 1) {
            System.out.println("Nombre de argumentos erroneos. Sintaxis:\n Directorio path");
            return;
        }

        // 1) guardar en una lista los arhivos del directorio path (parámetro de la función
        try {
            dir = new File(path);
            lista = dir.list();
        } catch (Exception e) {
            System.out.println("Directorio incorrecto o no existente: " + e);
        }

        /**
         * depenent del valor del parametre format:
         *
         * 'c': es cridarà la funcio listaColumnas amb el llistat d'arxius del
         * path (obtes anteriorment)
         *
         * 'l': es mostrarà per l'eixida estandar els arxius del path amb les
         * seues propietats, tal i com es demana en l'enunciat
         *
         */
        System.out.println("Selecciona una opcion: \n1. Listar columnas\n2. Mostrar estandar de archivos");

        switch (format) {
            case "c" ->
                listaColumnas(lista);
            case "l" -> {
                for (String archivo : lista) {
                    File fichero = new File(dir, archivo);
                    long tamaño = fichero.length();
                    long ultModificacion = fichero.lastModified();
                    Date fecha = new Date("dd/mm/yyyy");

                    boolean canRead = fichero.canRead();
                    boolean canWrite = fichero.canWrite();
                    boolean canExecute = fichero.canExecute();
                }
            }
            default ->
                System.out.println("Formato no valido. Usa 'c' para columnas o 'l' para lista con propiedades");
        }
    }

    public void start() {

        try {

            Scanner sc = new Scanner(System.in);
            String[] ordre;

            do {
                boolean ok = false;
                System.out.print("# alumnat@DAM: ");

                ordre = sc.nextLine().split(" ");

                switch (ordre.length) {
                    case 1 -> {
                        switch (ordre[0]) {
                            case "quit", "exit" ->

                                System.exit(0);
                            case "ls" -> {
                                //  cridar a showFiles() amb el path directori actual i sense cap opcio de format (cadena buida)
                                String dirActual = System.getProperty("user.dir");
                                showFiles(dirActual, "l");
                            }
                            default ->
                                System.out.println("Error: La sintaxis debe ser: \n {ls | ls -l | ls -la} [path]");
                        }
                    }

                    case 2 -> {
                        switch (ordre[0]) {
                            case "ls":
                                ok = true;
                                switch (ordre[1]) {
                                    case "-c" -> {
                                    }
                                    case "-l" -> {
                                    }
                                    default -> {
                                    }
                                }

                                // cridar a showFiles() amb el path directori actual i opcio de format 'c'
                                String dirActual = System.getProperty("user.dir");
                                showFiles(dirActual, "c");
                                // cridar a showFiles() amb el path directori actual i opcio de format 'l'
                                showFiles(dirActual, "l");
                                // cridar a showFiles() amb el path de ordre[1] i sense cap opcio de format (cadena buida)
                                showFiles(ordre[1], "");
                            default:
                                if (!ok) {
                                    System.out.println("Error: La sintaxi ha de ser: \\n {ls | ls -c | ls -l} [path]");
                                }
                                break;

                        }
                    }
                    case 3 -> {
                        switch (ordre[0]) {
                            case "ls":
                                switch (ordre[1]) {
                                    case "-c" -> {
                                        ok = true;
                                        // cridar a showFiles() amb el path de ordre[2] i opcio de format 'c'
                                        showFiles(ordre[1], "");
                                    }
                                    case "-l" -> {
                                        ok = true;
                                        // TO-DO: cridar a showFiles() amb el path de ordre[2] i opcio de format 'l'
                                        showFiles(ordre[2], "");
                                    }
                                    default ->  System.out.println("el case 3 de start() ha fallado");                                  
                                }

                            default:
                                if (!ok) {
                                    System.out.println("Error: La sintaxi ha de ser: \\n {ls | ls -c | ls -l} [path]");
                                }
                                break;
                               
                        }
                    }
                    default ->
                        System.out.println("Error: La sintaxi ha de ser: \\n {ls | ls -c | ls -l} [path]");
                }

            } while (true);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void listaColumnas(String[] filenames) {
        int columnas = (filenames.length / MAX_FILES_BY_COLUMN) + 1;

        String[][] salida = new String[MAX_FILES_BY_COLUMN][columnas];

        // Llenar la matriz 'salida' con los nombres de archivo
        for (int i = 0; i < filenames.length; i++) {
            salida[i % MAX_FILES_BY_COLUMN][i / MAX_FILES_BY_COLUMN] = filenames[i];
        }

        // Encontrar el nombre de archivo más largo para establecer el ancho de columna
        int maxLength = 0;
        for (String filename : filenames) {
            if (filename.length() > maxLength) {
                maxLength = filename.length();
            }
        }

        // Ajustar el formato para que cada columna tenga el mismo ancho
        String format = "%-" + (maxLength + 2) + "s"; // +2 para espacio adicional entre columnas

        // Bucle para mostrar salida con formato simétrico
        for (int i = 0; i < MAX_FILES_BY_COLUMN; i++) {
            for (int j = 0; j < columnas; j++) {
                if (salida[i][j] != null) {
                    System.out.printf(format, salida[i][j]); // Usa printf para aplicar el formato
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        ConsoleFiles app = new ConsoleFiles();
        
         // cridar a la funció start()
        app.start();
    }
}