/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyecto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author ainho
 */
public class Proyecto {

  /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Byte readed from source
        int bytes;
        // Bytes (effectively) writen to dest
        long bytesCopied = 0;

        // Streams 
        FileInputStream fis = null;
        FileOutputStream fos = null;

        // To provide information about source
        File f;

        // Are the arguments ok?
        if (args.length != 2) {
            System.out.println("Nombre d'arguments erroni. Sintaxi:\n FileCopy fitxerOrigen fitxerDesti");
            return;
        }

        try {

            // show source size
            f = new File(args[0]);
            System.out.println("Total: " + f.length() + " bytes");

            // Create streams          
            fis = new FileInputStream(args[0]);
            fos = new FileOutputStream(args[1]);

            do {
                // read one byte from source
                bytes = fis.read();
                // write in destination
                if (bytes != -1) {
                    fos.write(bytes);
                }
                // Update number of bytes
                bytesCopied++;

                // Show progress (think alternatives as exercise)
                System.out.print("\rCopiats " + (bytesCopied - 1) + " bytes...");
            } while (bytes != -1);
            System.out.println("Done it!");

        } catch (IOException exc) {
            System.out.println("Error d'entrada i eixida: " + exc);
        } finally {
            // At the end, we have to close the files, either an error exists or not.
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException exc) {
                System.out.println("Error en tancar el fitxer d'origen.");
            }
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException exc) {
                System.out.println("Error en tancar el fitxer destí.");
            }
        }
    }
}
