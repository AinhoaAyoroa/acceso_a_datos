package com.ad.ieseljust;

import java.io.*;
import java.nio.file.*;
import javax.xml.parsers.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

class FileController {

    public FileController() {
    }

    private boolean validaInt(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    private boolean validaDouble(String s) {
        try {
            Double.parseDouble(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    public Boolean Exists(String file) {
        File f = new File(file);
        return f.exists() && !f.isDirectory();
    }

    public Blockchain importFromText(String file) {
        Blockchain blockchain = new Blockchain();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            Block currentBlock = null;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts[0].equals("bloque")) {
                    currentBlock = new Block(Integer.parseInt(parts[1]), parts[2]);
                    blockchain.add(currentBlock);
                } else if (parts[0].equals("transaccion") && currentBlock != null) {
                    String origen = parts[1];
                    String destino = parts[2];
                    double importe = Double.parseDouble(parts[3]);
                    Transaction transaccion = new Transaction(currentBlock.getNumbloque(), origen, destino, importe);
                    currentBlock.addTransaction(transaccion);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return blockchain;
    }

    public Blockchain importFromObj(String file) {
        Blockchain blockchain = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            blockchain = (Blockchain) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return blockchain;
    }

    public Boolean exportText(Blockchain blockchain, String file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Block block : blockchain.ListaBloques) {
                writer.write(block.toTXT());
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Exporta la blockchain como un objeto serializado
    public Boolean exportObj(Blockchain blockchain, String file) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(blockchain);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean exportXML(Blockchain blockchain, String file) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            Element rootElement = doc.createElement("blockchain");
            doc.appendChild(rootElement);

            for (Block block : blockchain.ListaBloques) {
                block.toXML(doc, rootElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(file));

            transformer.transform(source, result);
            return true;
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean exportJSON(Blockchain blockchain, String file) {
        JSONObject jsonBlockchain = new JSONObject();
        JSONArray bloquesArray = new JSONArray();

        for (Block block : blockchain.ListaBloques) {
            bloquesArray.put(block.toJSON());
        }

        jsonBlockchain.put("bloques", bloquesArray);

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(file))) {
            writer.write(jsonBlockchain.toString(4)); // Indentación de 4 espacios
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
