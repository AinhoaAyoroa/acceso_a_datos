package com.mycompany.repasoud1_examen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author noa
 */
public class RepasoUD1_Examen {

    public static void main(String[] args) {
        int bytesLeidos;
        long bytesCopiados = 0;

        FileInputStream input;
        FileOutputStream output;

        byte[] buffer;
        String rutaOrigen;
        String rutaDestino;

        Scanner scanner = new Scanner(System.in);

        input = null;
        output = null;

        buffer = new byte[32];
        try {
            System.out.println("Introduce la ruta del archivo de origen: ");
            rutaOrigen = scanner.nextLine();
            System.out.println("Introduce la ruta del archivo de destino: ");
            rutaDestino = scanner.nextLine();

            File archivoOrigen = new File(rutaOrigen);
            System.out.println("Total: " + archivoOrigen.length() + " bytes");

            input = new FileInputStream(archivoOrigen);
            output = new FileOutputStream(rutaDestino);

            do {

                bytesLeidos = input.read(buffer);

                if (bytesLeidos != -1) {
                    output.write(buffer, 0, bytesLeidos);

                    bytesCopiados += bytesLeidos;
                }
                System.out.println("\r Copiados " + bytesCopiados + " bytes...");
                
            } while (bytesLeidos != -1);
            System.out.println("Copia completada");
            
        } catch (IOException error) {    
            System.out.println("Error: " + error);
            
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo de origen:" + e);
            }
            try {
                if (output != null) {
                    output.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo de destino:" + e);
            }
        }
    }
}
