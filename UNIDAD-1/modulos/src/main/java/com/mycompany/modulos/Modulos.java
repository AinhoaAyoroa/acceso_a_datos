package com.mycompany.modulos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author ainho
 */
public class Modulos{

    // several arrays with modules data
    String[] asignatura={"Accés a Dades", "Programació de serveis i processos", "Desenvolupament d'interfícies", "Programació Multimèdia i dispositiud mòbils", "Sistemes de Gestió Empresarial", "Empresa i iniciativa emprenedora"};
    int[]  horas={6, 3, 6, 5, 5, 3};
    double[] notas={8.45, 9.0, 8.0, 7.34, 8.2, 7.4};

    public void readFiLe(String name) throws IOException {
        // Per lleginr el fitxer binari, creem un DataInputStream
        // a partir del FileInputStream creat a partir del nom
        DataInputStream f = new DataInputStream(new FileInputStream(name));

        // Mentre el DataInputStream tinga dades disponibles
        while (f.available()>0){
            // Llegirem del fitxer cada dada, amb l'ordre corresponent
            // en funció del tipus
            // (per tant, hem de saber l'ordre en què guardem!)
            System.out.println("Mòdul: " + f.readUTF());
            System.out.println("Hores: " + f.readInt());
            System.out.println("Notes: " + f.readDouble());
            System.out.println();
        }
        f.close();
    }

    public void writeFile(String name) throws IOException{
        // Per escriure el fitxer, fem ús de DataOutputStream
        DataOutputStream f = new DataOutputStream(new FileOutputStream(name));

        // Recorrerem qualsevol dels vectors (tots haurien de tindre)
        // la mateixa longitud
        for (int i=0;i<this.asignatura.length;i++){
            // I per a cada posició, escriurem en funció del tipus de dada
            f.writeUTF(this.asignatura[i]);
            f.writeInt(this.horas[i]);
            f.writeDouble(this.notas[i]);

        }
        f.close();
    }

    public static void main(String[] args) throws IOException {

        // Comprovem els arguments
        if (args.length!=2){
            System.out.println("Nombre d'arguments incorrecte.\n\nSintaxi: \n\t java Moduls [read | write] fitxer.dat");
            System.exit(0);
        }

        // Defining the class
        Modulos moduls=new Modulos();

        // Depending the args, we will proceed
        if (args[0].equals("read")) 
        moduls.readFiLe(args[1]);
        else if (args[0].equals("write")) 
        moduls.writeFile(args[1]);
        else 
        System.out.println("No entenc l'ordre "+args[0]+"\n");
    }
}
