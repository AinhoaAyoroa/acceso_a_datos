package com.ad.ieseljust;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

class FileController {

    public FileController() {

    }

    private boolean validaInt(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    private boolean validaDouble(String s) {
        try {
            Double.parseDouble(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    public Boolean Exists(String file) {
        /**
         * **************************************
         * TO-DO: Mètode a implementar: Retorna si el fitxer existeix o no
         *
         * ***************************************
         */

        File f = new File(file);
        return f.isFile() && f.canRead();
    }

    public Blockchain importFromText(String file) {

        Blockchain blockchain = new Blockchain();
        FileReader fr = null;
        try {
            /**
             * *********************************************************
             * TO-DO: Mètode a implementar: Llegirà el fitxer indicat, en format
             * text, i importarà la llista de blocs a la Blockchain
             * **********************************************************
             */

            File f = new File(file);
            fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            int num_Lin = 0;

            while ((linea = br.readLine()) != null) {
                linea = linea.toLowerCase(); // per evitar errors

                num_Lin++;

                if (linea.startsWith("#")) {
                    continue;
                }

                String elementos[] = linea.split("[ ]+");

                switch (elementos[0]) {
                    case "bloque" -> {
                        if (elementos.length != 3) {
                            System.out.println(Colores.Red + "Error en la linea " + num_Lin + ". El comando 'bloque' ha de tenir 3 arguments");
                            break;
                        }

                        if (!validaInt(elementos[1])) {
                            System.out.println(Colores.Red + "Error en la linea " + num_Lin + ". Falla algun formato numérico");
                            break;
                        }

                        Block b = new Block(Integer.parseInt(elementos[1]), elementos[2]);
                        blockchain.add(b);
                    }
                    case "transaccion" -> {
                        if (elementos.length != 5) {
                            System.out.println(Colores.Red + "Error en la linea " + num_Lin + ". La transaccion debe tener 5 argumentos");
                            break;
                        }

                        if (!validaInt(elementos[1]) || !validaDouble(elementos[4])) {
                            System.out.println(Colores.Red + "Error en la linea " + num_Lin + ". Falla algun formato numérico");
                            break;
                        }
                        boolean existe = false;
                        for (Block bl : blockchain.ListaBloques) {
                            if (bl.getNumbloque() == Integer.parseInt(elementos[1])) {
                                existe = true;
                                Transaction tr = new Transaction(Integer.parseInt(elementos[1]), elementos[2], elementos[3], Double.parseDouble(elementos[4]));
                                bl.addTransaction(tr);
                                break;
                            }
                        }
                        if (!existe) {
                            System.out.println(Colores.Red + "Error en la linea " + num_Lin + ". Bloque no existe en la Blockchain.");
                        }
                    }

                    default ->
                        System.out.println(Colores.Red + "Error en el fichero en la linea " + num_Lin + Colores.Reset);
                }

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return blockchain;
    }

    public Blockchain importFromObj(String file) {

        FileInputStream fi = null;
        Blockchain blockchain = null;
        try {
            /**
             * ****************************************************************
             * TO-DO: Mètode a implementar: Llegirà el fitxer indicat, en format
             * d'objectes seriats, i importa la llista de blocs a la Blockchain.
             *
             ******************************************************************
             */

            fi = new FileInputStream(file);
            try (ObjectInputStream ois = new ObjectInputStream(fi)) {
                blockchain = (Blockchain) (ois.readObject());
            }
            fi.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
            
        } finally {
            try {
                fi.close();
            } catch (IOException ex) {
                Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return blockchain;
    }

    public Boolean exportText(Blockchain blockchain, String file) {
        boolean out = false;
        FileWriter file_writer = null;
        try {
            /**
             * ************************************************
             * TO-DO: Mètode a implementar: exporta la Blockchain donada a un
             * fitxer en format text, per poder ser importat posteriorment.
             *
             ************************************************
             */

            File f = new File(file);
            file_writer = new FileWriter(f);
            PrintWriter pw = new PrintWriter(file_writer);

            if (blockchain.esBuida()) {
                out = false;
            }

            for (Block b : blockchain.ListaBloques) {
                pw.println(b.toTXT().toLowerCase());
            }

            pw.flush();
            file_writer.close();
            out = true;

        } catch (IOException ex) {
            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                file_writer.close();
            } catch (IOException ex) {
                Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return out;

    }

    public Boolean exportObj(Blockchain blockchain, String file) {

        FileOutputStream output = null;
        boolean out = false;
        try {
            /**
             * **********************************************************
             * TO-DO: Mètode a implementar: exporta la Blockchain donada a un
             * fitxer binari d'objectes, per poder ser importat posteriorment. *
             * ********************************************************
             */

            File f = new File(file);
            output = new FileOutputStream(f);
            try (ObjectOutputStream oos = new ObjectOutputStream(output)) {
                oos.writeObject(blockchain); // es guarda tot
            } // es guarda tot
            output.close();

            out = true;

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                output.close();
            } catch (IOException ex) {
                Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return out;
    }

    public Boolean exportXML(Blockchain blockchain, String file) {
        /**
         * **********************************************************
         * TO-DO: Mètode a implementar: exporta la Blockchain donada a un fitxer
         * XML. **********************************************************
         */

        /* XML file example:
        
            <?xml version="1.0" encoding="UTF-8" standalone="no"?>
            <blockchain>
              <bloques>
                <bloque numbloque="1" propietario="JASB">
                  <transaccion destino="b" importe="15.2" origen="a"/>
                  <transaccion destino="c" importe="-9.33" origen="a"/>
                </bloque>
                <bloque numbloque="2" propietario="ROOT">
                  <transaccion destino="n10" importe="5888.22" origen="n11"/>
                </bloque>
                <bloque numbloque="3" propietario="DAM"/>
              </bloques>
            </blockchain>
        
         */
        boolean out = false;
        try {

            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            document.setXmlVersion("1.0");

            Element root = document.createElement("blockchain");

            document.appendChild(root);
            Element bloques = document.createElement("bloques");

            for (Block b : blockchain.ListaBloques) {
                b.toXML(document, bloques);
            }
            root.appendChild(bloques);

            Source source = new DOMSource(document);
            Result result = new StreamResult(file);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            out = true;

        } catch (ParserConfigurationException | TransformerException ex) {
            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return out;
    }

    public Boolean exportJSON(Blockchain blockchain, String filename) {

        FileWriter file = null;
        boolean out = false;
        try {
            /**
             * **********************************************
             * TO-DO: Mètode a implementar: exporta la Blockchain donada a un
             * fitxer JSON.
             *
             **********************************************
             */

            /* JSON file example:
                                {
                    "blockchain": {
                        "bloques": [
                            {
                                "bloque": {
                                    "propietario": "JASB",
                                    "transacciones": [
                                        {
                                            "transaccion": {
                                                "origen": "a",
                                                "destino": "b",
                                                "importe": 15.2
                                            }
                                        },
                                        {
                                            "transaccion": {
                                                "origen": "a",
                                                "destino": "c",
                                                "importe": -9.33
                                            }
                                        }
                                    ],
                                    "numbloque": "1"
                                }
                            },
                            {
                                "bloque": {
                                    "propietario": "ROOT",
                                    "transacciones": [
                                        {
                                            "transaccion": {
                                                "origen": "n1",
                                                "destino": "n10",
                                                "importe": 5888.22
                                            }
                                        }
                                    ],
                                    "numbloque": "2"
                                }
                            },
                            {
                                "bloque": {
                                    "propietario": "DAM",
                                    "transacciones": [],
                                    "numbloque": "3"
                                }
                            }
                        ]
                    }
                }
             */
            JSONArray listaBloques = new JSONArray();

            for (Block b : blockchain.ListaBloques) {
                listaBloques.put(b.toJSON());
            }
            JSONObject objJSON = new JSONObject();
            objJSON.put("bloques", listaBloques);

            JSONObject objRootJSON = new JSONObject();
            objRootJSON.put("blockchain", objJSON);

            file = new FileWriter(filename);
            file.write(objRootJSON.toString(4));
            file.flush();
            out = true;
        } catch (IOException ex) {
            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                file.close();
            } catch (IOException ex) {
                Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return out;
    }

}
