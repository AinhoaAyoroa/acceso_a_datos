/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.ad.ieseljust;

import java.util.Scanner;

/**
 *
 * @author jasb
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // FileController serà el gestor d'emmagatzenament segons el format de file dessitjat (bytes, txt, xml, json)
        FileController fm;

        Scanner keyboard = new Scanner(System.in);

        // Inicialitzem la Blockchain (inicialment buida, sense blocks)
        Blockchain blockchain = new Blockchain();
        String command;

        do {

            System.out.print(Colores.Blue + "# Blockchain DAM: " + Colores.Reset);
            String linea = keyboard.nextLine();

            // Separem l'ordre introduida pel teclat, per cada espai en blanc"
            String[] components = linea.split(" ");

            command = components[0];

            switch (command) {
                case "block":
                    // Creació de un nou block
                    try {
                    
                    Block blq = new Block(Integer.valueOf(components[1]), components[2].toString());
                    // afegir el block a la llista
                    blockchain.add(blq);

                } catch (Exception e) {
                    // Si s'ha produït algun error als paràmetres, s'indica un error de sintaxi
                    System.out.println("\u001B[31m Error de sintaxi. La sintaxi correcta és: block \"numero de bloque\" \"propietario\" \u001B[0m");
                }
                break;

                case "transaction":
                    // Creació de la nova transaccio
                    try {
                    Transaction tr = new Transaction(Integer.valueOf(components[1]), components[2], components[3], Double.valueOf(components[4]));
                    
                    // afegir la transaccio a la llista
                    if (!blockchain.esBuida()) {
                        boolean westa = false;
                        for (Block b : blockchain.ListaBloques) {
                            if (b.getNumbloque() == Integer.valueOf(components[1])) {
                                b.addTransaction(tr);
                                westa = true;
                                break;
                            }
                        }
                        if (!westa) {
                            System.out.println(Colores.Red + "El bloque indicado no existe." + Colores.Reset);
                        }
                    } else {
                        System.out.println(Colores.Red + "El bloque indicado no existe." + Colores.Reset);
                    }

                } catch (Exception e) {
                    // Si s'ha produït algun error als paràmetres, s'indica un error de sintaxi
                    System.out.println("\u001B[31m Error de sintaxi. La sintaxi correcta és: tr \"numero de bloque\" \"Origen\" \"Destino\" \"Importe\"\u001B[0m" + e.getMessage());
                }
                break;

                case "import":
                    // Instanciem el Gestor d'Emmagatzemamanet
                    fm = new FileController();

                    // que llegirà del fitxer indicat 
                    // com a primer paràmetre (emmagatzemat en components[1])
                    // i rep una nova escena
                    if (!fm.Exists(components[1])) {
                        System.out.println("El fitxer no existeix");
                    } else {
                        System.out.println("Important fitxer...");

                        String extensio = components[1].substring(components[1].length() - 4, components[1].length());

                        switch (extensio) {
                            case ".txt": {
                                Blockchain novaBlockchain = fm.importFromText(components[1]);
                                // Si torna algun resultat, l'escena canvia per la nova
                                if (!novaBlockchain.esBuida()) {
                                    blockchain = novaBlockchain;
                                }
                                System.out.println(Colores.Bright_Cyan + "Ok. Importació Correcta" + Colores.Reset);
                            }
                            break;
                            case ".obj": {
                                Blockchain novaBlockchain = fm.importFromObj(components[1]);
                                // Si torna algun resultat, l'escena canvia per la nova
                                if (!novaBlockchain.esBuida()) {
                                    blockchain = novaBlockchain;
                                }
                                System.out.println(Colores.Bright_Cyan + "Ok. Importació Correcta" + Colores.Reset);
                            }

                            break;

                            default:
                                System.out.println("Format no reconegut..");
                                break;

                        }
                        System.out.println(blockchain.toString());

                    }

                    break;

                case "export":
                    try {
                    String extensio = components[1].substring(components[1].length() - 4, components[1].length());

                    // Comprovem si el fitxer existeix
                    fm = new FileController();
                    if (fm.Exists(components[1])) {
                        // Si el fitxer ja existeix, eixim de l'exportació mostrant un error
                        System.out.println("\u001B[31m El fitxer ja existeix\u001B[0m");
                        break;

                    }

                    switch (extensio) {
                        case ".txt":
                            if (fm.exportText(blockchain, components[1])) {
                                System.out.println(Colores.Bright_Cyan + "Process Ok. Exportació Correcta a TXT" + Colores.Reset);
                            } else {
                                System.out.println(Colores.Bright_Red + "Error. Exportació Incorrecta" + Colores.Reset);
                            }
                            break;

                        case ".obj":
                            if (fm.exportObj(blockchain, components[1])) {
                                System.out.println(Colores.Bright_Cyan + "Process Ok. Exportació Correcta a OBJ" + Colores.Reset);
                            } else {
                                System.out.println(Colores.Bright_Red + "Error. Exportació Incorrecta" + Colores.Reset);
                            }
                            break;

                        case ".xml":
                            if (fm.exportXML(blockchain, components[1])) {
                                System.out.println(Colores.Bright_Cyan + "Process Ok. Exportació Correcta a XML" + Colores.Reset);
                            } else {
                                System.out.println(Colores.Bright_Red + "Error. Exportació Incorrecta" + Colores.Reset);
                            }
                            break;

                        case "json":
                            if (fm.exportJSON(blockchain, components[1])) {
                                System.out.println(Colores.Bright_Cyan + "Process Ok. Exportació Correcta a JSON" + Colores.Reset);
                            } else {
                                System.out.println(Colores.Bright_Red + "Error. Exportació Incorrecta" + Colores.Reset);
                            }
                            break;

                        default:
                            System.out.println("\u001B[31m Format no suportat\u001B[0m");
                            break;

                    }

                } catch (Exception e) {
                    System.out.println("\u001B[31m Error d'exportació\u001B[0m");
                }

                break;

                case "list":
                    if (blockchain.esBuida())
                        System.out.println("\nAvís: La Blockchain no conte cap block \n");
                    else
                        // Si l'ordre indicada és llista, imprimirem tota la info de la Blockchain: els blocs que conté (i els seus propietaris) i cadascuna de les seues transaccions (amb IBAN d'orige, de desti i l'imports associat)
                        System.out.println(blockchain.toString());
                    break;

                case "quit":
                    System.out.println(Colores.Magenta + "Acabant el programa." + Colores.Reset);
                    break;
                default:
                    // Si hem arribat aci, l'ordre no es coneix
                    System.out.println(Colores.Bright_Red + "Ordre no reconeguda" + Colores.Reset);
            }

        } while (!command.equals("quit"));

        System.exit(0);
    }

}
