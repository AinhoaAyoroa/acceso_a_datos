package com.ieseljust.ad.myDBMS;

import java.sql.*;
import java.util.Scanner;

import java.util.ArrayList;

class DatabaseManager{
    
    String server;
    String port;
    String user;
    String pass;
    String dbname;

    DatabaseManager(){
        // TO-DO: Default initialization
    }

    DatabaseManager(String server, String port, String user, String pass, String dbname){
        // TO-DO:   -init from args
    }

    public Connection connectDatabase(){
        // TO-DO:  Connect to a specific database

        return null;
    }

    public void showTables(){
        // TO-DO: Show the tables in this database
    }



    public void insertIntoTable(String table){
        // TO-DO: add a new record

        // Steps
        // 1. Connect to the DB (if not)
        // 2. Get columns and data types of each column
        // 3. Ask the user for values
        // 4. Compose insert query 
        
        // Notice that
        // - Data types of each type
        // - Notice about default values
        // - manage errors
        // - Show generated id (if exists)

    }



    public void showDescTable(String table){
        // TO-DO: Show info about tables, keys and foreign keys
        
    }

    public void startShell(){

        // TO-DO: Inicia la shell del mode base de dades

        }


}