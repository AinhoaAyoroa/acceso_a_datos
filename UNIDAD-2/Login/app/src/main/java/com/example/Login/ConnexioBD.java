package com.example.Login;

import com.example.Login.Utils.Xifrar;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author noa
 */
public class ConnexioBD {

    Connection laConnexio = null;

    public void connect() {
        String url = "jdbc:mysql://localhost:3306/prueba";
        String user = "root";
        String password = "root";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            laConnexio = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e);
        }
    }

    public void disConnect() {
        try {
            if (laConnexio != null) {
                laConnexio.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al desconectar la base de datos: " + e);
        }
    }

    /**
     *
     * @param user
     * @return true: encontrado
     */
    public boolean validaUser(String user) {
        boolean encontrado = false;
        try {
            String query = "SELECT COUNT(*) FROM usuarios WHERE nombre = ?";
            try (PreparedStatement statement = laConnexio.prepareStatement(query)) {
                statement.setString(1, user);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        int count = rs.getInt(1);
                        if (count > 0) {
                            encontrado = true;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al validar usuario: " + e);
        }
        return encontrado;
    }

    /**
     *
     * @param user
     * @param pass
     * @return 0: usuario y contraseña correctos
     */
    public int validaPass(String user, String pass) {
        int res = -1;
        try {
            if (!validaUser(user)) {
                return 1;
            }
            String query = "SELECT contraseña FROM usuarios WHERE nombre = ?";
            try (PreparedStatement statement = laConnexio.prepareStatement(query)) {
                statement.setString(1, user);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        String contraseñaGuardada = rs.getString("contraseña");
                        String contraseñaCifrada = Xifrar.sha1(pass);
                        if (contraseñaGuardada.equals(contraseñaCifrada)) {
                            res = 0;
                        } else {
                            res = 2;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al validar contraseña: " + e);
        }
        return res;
    }

    /**
     *
     * @param user
     * @param pass
     * @return 0: usuario intertado correctamente
     */
    public int insertUser(String user, String pass) {

        int res = -1;
        try {
            if (validaUser(user)) {
                return -1;
            }

            String query = "INSERT INTO usuarios (nombre, contraseña) VALUES (?, ?)";
            try (PreparedStatement statement = laConnexio.prepareStatement(query)) {
                statement.setString(1, user);
                String contraseñaCifrada = Xifrar.sha1(pass);
                statement.setString(2, contraseñaCifrada);
                int filasAfectadas = statement.executeUpdate();
                if (filasAfectadas > 0) {
                    res = 0;
                }
                statement.close();
            } catch (Exception e) {
                System.out.println("Error al ejecutar el query o actualizar filas: " + e);
            }
        } catch (Exception e) {
            System.out.println("Error al insertar el usuario: " + e);
        }
        return res;
    }
}
