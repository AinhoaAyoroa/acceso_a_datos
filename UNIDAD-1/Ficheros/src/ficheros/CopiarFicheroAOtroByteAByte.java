package ficheros;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author noa
 */
public class CopiarFicheroAOtroByteAByte {

    public static void main(String[] args) {
        int bytesLeidos;
        long bytesCopiados = 0;

        FileInputStream input;
        FileOutputStream ouput;
        
        byte[] buffer;
        File rutaOrigen;
        File rutaDestino;

        try (Scanner scanner = new Scanner(System.in)) {
            input = null;
            ouput = null;

            if (args.length != 3) {
                System.out.println("Nombre de argumentos erroneo. Sintaxis: ruta/origen/fichero.txt /ruta/destino/fichero.txt");
            }
           
            rutaOrigen = new File(args[0]);
            rutaDestino = new File(args[1]);
            
            System.out.println("Introduce la cantidad de bytes para cada tanda: ");
            int bytesTanda = scanner.nextInt();
            
            buffer = new byte[bytesTanda];
        }
        try {
            System.out.println("Total: " + rutaOrigen.length() + " bytes");

            input = new FileInputStream(rutaOrigen);
            ouput = new FileOutputStream(rutaDestino);

            do {
                bytesLeidos = input.read(buffer);
                if (bytesLeidos != -1) {
                    ouput.write(buffer, 0, bytesLeidos);
                    bytesCopiados += bytesLeidos;
                }
                System.out.println("\r Copiados " + (bytesCopiados - 1) + " bytes...");
            } while (bytesLeidos != -1);
            System.out.println("Copia completada");
            
        } catch (IOException e) {
            System.out.println("Error de entrada y salida: " + e);
            
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo de origen:" + e);
            }
            try {
                if (ouput != null) {
                    ouput.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo de destino:" + e);
            }
        }
    }
}
