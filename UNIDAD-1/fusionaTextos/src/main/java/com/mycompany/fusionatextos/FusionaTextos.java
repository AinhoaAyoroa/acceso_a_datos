/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.fusionatextos;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author ainho
 */
public class FusionaTextos {
    public static void main(String[] args) throws Exception {

        File dir; // Source dir
        // Collection of files from that dir
        File[] files;

        // readed characters
        int characters;

        // Input and Output Streams 
        FileReader fin = null;
        FileWriter fout = null;

        // Check the args
        if (args.length != 2) {
            System.out.println("Nombre d'arguments erroni. Sintaxi:\n mergeTexts DirectoriOrigen fitxerDesti");
            return;
        }

        try {

            // We get the list of Files
            dir = new File(args[0]);
            files = dir.listFiles();

            // Open and close output stream (in order to create the file)
            fout = new FileWriter(args[1]);
            fout.close();

            // Re-open it
            fout = new FileWriter(args[1], true);

            // Iterate among the list
            for (int i = 0; i < files.length; i++) {
                // open input stream
                fin = new FileReader(args[0] + "/" + files[i].getName());
                System.out.println("Merging " + args[0] + "/" + files[i].getName());
                // and merge to the output one
                do {
                    characters = fin.read();
                    if (characters != -1) {
                        fout.write(characters);
                    }
                } while (characters != -1);
                fin.close(); //close the file merged

            }
            fout.close(); //close the output file

        } catch (Exception exc) {
            // Catch all the exception (we coud improve it)
            System.out.println("Input/Output error: " + exc);
        }
    }
}

