package com.ieseljust.ad.myDBMS;

import java.sql.*;
import java.util.Scanner;

class ConnectionManager{
    
    String server;
    String port;
    String user;
    String pass;
    
    ConnectionManager(){
        // TO-DO: Init default values
        
    }

    ConnectionManager(String server, String port, String user, String pass){
        // TO-DO:   Init with arguments
    }

    public Connection connectDBMS(){
        // TO-DO:  Create a connection, 
        //         Returns this or null.
        // Remember error management

        return null;

    }

    public void showInfo(){
        // TO-DO: show server info
        // Remember error management
    }

    public void showDatabases(){
         // TO-DO: Show databases in your server
    }

    public void startShell(){

        Scanner keyboard = new Scanner(System.in);
        String command;

        do {

            System.out.print(ConsoleColors.GREEN_BOLD_BRIGHT+"# ("+this.user+") on "+this.server+":"+this.port+"> "+ConsoleColors.RESET);
            command = keyboard.nextLine();

                        
            switch (command){
                case "help":
                    System.out.println("\n\n\t\t"+ConsoleColors.YELLOW_UNDERLINED+ "Us del Manager de AD"+ConsoleColors.RESET);
                    System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT+ "show databases o sh db\t\tMostra les bases de dades"+ConsoleColors.RESET);
                    System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT+ "info\t\t\t\tMostra informació de la connexió"+ConsoleColors.RESET);
                    System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT+ "import Nom_del_script\t\tCarrega un script en la BBDD"+ConsoleColors.RESET);
                    System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT+ "use Nom_de_la_BD\t\tActiva la BBDD indicada i canvia el mode del menú"+ConsoleColors.RESET);
                    System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT+ "help\t\t\t\tAjuda de l'aplicació\n\n"+ConsoleColors.RESET);
                    break;
                case "sh db":
                case "show databases":
                    this.showDatabases();
                    break;
                
                case "info":
                    this.showInfo();
                    break;

                case "quit":
                    break;
                default:
                    // Com que no podem utilitzar expressions
                    // regulars en un case (per capturar un "use *")
                    // busquem aquest cas en el default:

                    String[] subcommand=command.split(" ");
                    switch (subcommand[0]){
                        case "use":
                            // TO-DO:
                                // Creem un objecte de tipus databaseManager per connectar-nos a
                                // la base de dades i iniciar una shell de manipulació de BD..
                        case "import":
                            //TO-DO: call to function to import .sql to DMBS
                            break;
                        default:
                            System.out.println(ConsoleColors.RED+"Unknown option"+ConsoleColors.RESET);
                            break;


                    }

                    

            }
            
        } while(!command.equals("quit"));

        
        }


}