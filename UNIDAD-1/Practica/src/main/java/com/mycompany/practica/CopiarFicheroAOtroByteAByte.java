package com.mycompany.practica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

/**
 *
 * @author noa
 */
public class CopiarFicheroAOtroByteAByte {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // creo un entero para almacenar el byte leído
        int bytesLeidos = 0;
        // cre un entero largo para contar los bytes copiados
        long bytesCopiados = 0;

        // creo los flujos de entrada y salida para leer y escribir en el archivo
        FileInputStream flujoEntrada = null;
        FileOutputStream flujoSalida = null;
        
        // creo un buffer para copiar de 32 en 32 bytes en vez de 1 en 1
        byte[] buffer = new byte[32];
        

        // yo prefiero pedir la informacion asi que pido la informacion
        System.out.println("Introduce la ruta del archivo de origen: ");
        String rutaOrigen = scanner.nextLine();
        System.out.println("Introduce la ruta del archivo de destino: ");
        String rutaDestino = scanner.nextLine();
        scanner.close();
        try {
            //Creo un objeto File para el archivo origen y destino y mostrar su tamaño
            File archivoOrigen = new File(rutaOrigen);
            System.out.println("Total: " + archivoOrigen.length() + " bytes");  
            // Inicializo los flujos de entrada
            flujoEntrada = new FileInputStream(archivoOrigen); // abre el archivo de origen para leerlo
            flujoSalida = new FileOutputStream(rutaDestino); // abre o crea el archivo de destino para escribir en él
            
            do{
                // leemos hasta 32 bytes desde el archivo origen
                bytesLeidos = flujoEntrada.read(buffer);
                // si el bloque leido no está vacío, escribimos
                if (bytesLeidos != -1) {
                    flujoSalida.write(buffer, 0, bytesLeidos);
                    // incrementamos el contados de bytes copiados
                    bytesCopiados += bytesLeidos;
                }
                System.out.println("\r Copiados " + (bytesCopiados - 1) + " bytes...");
            }while(bytesLeidos != -1); // Continuar hasta que se llegue al final del archivo
            System.out.println("Copia completada");
            }catch(Exception e){
            System.out.println("Error de entrada y salida: " + e);
            // cerramos los flujos
            }finally{
            try{
                if (flujoEntrada != null) flujoEntrada.close();
            }catch(Exception e){
                System.out.println("Error al cerrar el archivo de origen:" + e);
            }
            try{
                if (flujoSalida != null) flujoSalida.close();
            }catch(Exception e){
                System.out.println("Error al cerrar el archivo de destino:" + e);
            }
        }
        }
    }
