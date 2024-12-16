package com.mycompany.prueba_maven;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        DataBaseConnector dbConnector = new DataBaseConnector();
        Connection connection = dbConnector.connect();

        if (connection != null) {
            System.out.println("La conexión está activa.");
        } else {
            System.out.println("No se pudo establecer conexión con la base de datos.");
        }
    }
}
