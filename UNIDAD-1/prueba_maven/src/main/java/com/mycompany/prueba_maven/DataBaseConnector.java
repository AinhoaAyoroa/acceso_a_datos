package com.mycompany.prueba_maven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnector {

    // URL de conexión con el nombre de la base de datos 'prueba'
    private static final String URL = "jdbc:mysql://localhost:3306/ejercicio1";
    private static final String USER = "root"; // usuario de MySQL
    private static final String PASSWORD = "root"; // contraseña de MySQL

    public Connection connect() {
        Connection connection = null;
        try {
            // Establecer conexión
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión a la base de datos exitosa.");
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
        return connection;
    }

    public Connection Consulta() {

        String user = props.getProperty("user");
        String pass = props.getProperty("password");

        Connection conexion = DriveManager.getConnection(connectionUrl, user, pass);

        ResultSet rs = conexion.createStatement().executeQuery("SELECT * FROM Genero");

        while (rs.next) {
            System.out.println("");
        }

        return null;
    }

}
