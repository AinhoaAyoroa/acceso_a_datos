/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Login;

import java.awt.HeadlessException;
import javax.swing.JOptionPane;

/**
 *
 * @author joange
 */
public class Validacio extends javax.swing.JFrame {

    private String usuario;
    private String password;

    private final ConnexioBD conn;

    /**
     * Creates new form Validacio
     */
    public Validacio() {
        initComponents();

        conn = new ConnexioBD();
        conn.connect();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblUser = new javax.swing.JLabel();
        jtxtUser = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jpwdPassword = new javax.swing.JPasswordField();
        jbAccept = new javax.swing.JButton();
        jbCancel = new javax.swing.JButton();
        jbNew = new javax.swing.JButton();
        jbExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lblUser.setText("User");

        jtxtUser.setToolTipText("type username");

        jLabel1.setText("Password");

        jbAccept.setText("Aceptar");
        jbAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAcceptActionPerformed(evt);
            }
        });

        jbCancel.setText("Cancelar");
        jbCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelActionPerformed(evt);
            }
        });

        jbNew.setText("Alta nuevo usuario");
        jbNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbNewActionPerformed(evt);
            }
        });

        jbExit.setText("Salir");
        jbExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbExit, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jbNew, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jbAccept, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jbCancel))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblUser)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addGap(26, 26, 26)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jtxtUser)
                                .addComponent(jpwdPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUser)
                    .addComponent(jtxtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jpwdPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbAccept)
                    .addComponent(jbCancel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbNew)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbExit))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelActionPerformed
        // TODO add your handling code here:
        cancelar();
    }//GEN-LAST:event_jbCancelActionPerformed

    private void jbAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAcceptActionPerformed
        // TODO add your handling code here:
        aceptar();
    }//GEN-LAST:event_jbAcceptActionPerformed

    private void jbNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNewActionPerformed
        // TODO add your handling code here:
        nuevo();
    }//GEN-LAST:event_jbNewActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        exit();
    }//GEN-LAST:event_formWindowClosing

    private void jbExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbExitActionPerformed
        // TODO add your handling code here:
        exit();
    }//GEN-LAST:event_jbExitActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbAccept;
    private javax.swing.JButton jbCancel;
    private javax.swing.JButton jbExit;
    private javax.swing.JButton jbNew;
    private javax.swing.JPasswordField jpwdPassword;
    private javax.swing.JTextField jtxtUser;
    private javax.swing.JLabel lblUser;
    // End of variables declaration//GEN-END:variables

    private void aceptar() {
        if ((jtxtUser.getText().equals("")) || (new String(jpwdPassword.getPassword()).equals(""))) {
            JOptionPane.showMessageDialog(null,
                    "Debes introducir usuario y contraseña",
                    "Login", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String user = jtxtUser.getText();
        String pass = new String(jpwdPassword.getPassword());

        try {
            int result = conn.validaPass(user, pass);
            switch (result) {
                case 0 ->
                    JOptionPane.showMessageDialog(null,
                            "Login correcto. ¡Bienvenido " + user + "!",
                            "Login", JOptionPane.INFORMATION_MESSAGE);
                case 1 ->
                    JOptionPane.showMessageDialog(null,
                            "El usuario no existe.",
                            "Login", JOptionPane.ERROR_MESSAGE);
                case 2 ->
                    JOptionPane.showMessageDialog(null,
                            "Contraseña incorrecta.",
                            "Login", JOptionPane.ERROR_MESSAGE);
                default -> {
                }
            }
        } catch (HeadlessException e) {
            System.out.println("\n" + e);
        }
    }

    private void clean() {
        jtxtUser.setText("");
        jpwdPassword.setText("");
    }

    private void nuevo() {
        if ((jtxtUser.getText().equals("")) || (new String(jpwdPassword.getPassword()).equals(""))) {
            JOptionPane.showMessageDialog(null,
                    "Debes introducir usuario y contraseña.",
                    "Login", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            String user = jtxtUser.getText();
            String pass = new String(jpwdPassword.getPassword());

            int busquedaUser = conn.insertUser(user, pass);

            if (busquedaUser != -1) {
                JOptionPane.showMessageDialog(null,
                        "Usuario creado exitosamente",
                        "Registro", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                    "El usuario ya existe.",
                    "Registro", JOptionPane.ERROR_MESSAGE);
            }

        } catch (HeadlessException h) {
            System.out.println("FATAL ERROR \n" + h);
        } catch (Exception e) {
            System.out.println("Error al crear el usuario: " + e);
        }

    }

    private void exit() {
        conn.disConnect();
        this.setVisible(false);
        System.exit(0);
    }

    private void cancelar() {
        jtxtUser.setText("");
        jpwdPassword.setText("");

    }

    public String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }
}