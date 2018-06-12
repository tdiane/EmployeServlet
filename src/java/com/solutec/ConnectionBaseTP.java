/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solutec;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author esic
 */
public class ConnectionBaseTP {

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    private String url = "jdbc:derby://localhost:1527/BaseTP";
    private String user = "jee";
    private String mdp = "jee";

    private String req = "SELECT * FROM IDENTIFIFANTS";

    //Connexion à la bdd BaseTP
    public ConnectionBaseTP() {

        try {
            conn = DriverManager.getConnection(url, user, mdp);

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
    }

    //Renvoyer le résultat de la requête req dans rs
    //req = "SELECT * FROM IDENTIFIFANTS";
    public ResultSet getResultSet(String req) {
        
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(req);
            
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        
        return rs;
    }

    public Identifiants getIdentifiants(ResultSet rs){    //on rentre les valeurs de login et mdp de la base dans la classe Identifiants (classe Bean)
        
        Identifiants ident = new Identifiants();
        
        try {
        
        while (rs.next()) {

            ident.setLogin(rs.getString("login"));
            ident.setMdp(rs.getString("mdp"));

        }
        
        
               } catch (SQLException e) {

            System.out.println(e.getMessage());
        } 
        
        return ident;
    }

}
