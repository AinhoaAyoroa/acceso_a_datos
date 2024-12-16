/*
 * This source file was generated by the Gradle 'init' task
 */
package com.ad.ieseljust.StarWars;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class App {

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, TransformerConfigurationException, FileNotFoundException, TransformerException {

        App app = new App();

        Document DOM = app.createXML();

        Element root = DOM.createElement("StarWars");

        DOM.appendChild(root);

        JSONObject myJson = app.lligJSON("StarWars.json");
        JSONArray personatges = myJson.getJSONArray("personatges");

        for (int i = 0; i < personatges.length(); i++) {

            JSONObject personatge = personatges.getJSONObject(i);
            JSONArray vehicles = personatge.getJSONArray("vehicles");

            if (vehicles.isEmpty()) {
                Element character = DOM.createElement("character");
                Element name = DOM.createElement("name");
                Element mass = DOM.createElement("mass");
                Element url = DOM.createElement("url");

                JSONArray films = personatge.getJSONArray("films");
                character.setAttribute("films", String.valueOf(films.length()));
                character.setAttribute("vehicles", "0");

                name.setTextContent(personatge.getString("name"));
                mass.setTextContent(personatge.getString("mass"));
                url.setTextContent(personatge.getString("url"));

                character.appendChild(name);
                character.appendChild(mass);
                character.appendChild(url);

                root.appendChild(character);
            }
        }
        
        app.exportXML("StarWars.xml", DOM);

    }

    public void exportXML(String filename, Document doc) throws TransformerConfigurationException, FileNotFoundException, TransformerException {

        Transformer trans = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new FileOutputStream(filename));
        
        trans.transform(source, result);
    }

    public Document createXML() throws IOException, SAXException, ParserConfigurationException, FileNotFoundException {

        // Create an instance of DocumentBuilderFactory
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        // Using the DocumentBuilderFactory instance we create a DocumentBuilder
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        //And we use DocumentBuilder's "parse" method to get the document
        Document doc = dBuilder.newDocument();

        return doc;
    }

    private JSONObject lligJSON(String filename) {
        try {
            // Amb FileReader llegirem caràcter a 
            // caràcter el fitxer i l'afegim al string myJson
            FileReader file = new FileReader(filename);
            String myJson = "";

            int i;
            while ((i = file.read()) != -1) {
                myJson = myJson + ((char) i);
            }

            //System.out.println(myJson);
            file.close();

            // I fem ús del constructor de JSONObject
            // al que li passem un string amb el JSON:
            return (new JSONObject(myJson));

        } catch (Exception e) {
            System.out.println("Error llegint el fitxer");
            return null;
        }

    }

}
