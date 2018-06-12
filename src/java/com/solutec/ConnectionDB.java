/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solutec;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author esic
 */
public class ConnectionDB {

    private Connection conn;
    private Statement stmt;
    private ResultSet rs1;
    private ResultSet rs2;     //req3

    private PreparedStatement pstmt;

    private String url = "jdbc:derby://localhost:1527/solutec";
    private String user = "test";
    private String mdp = "test";

    private String listeEmployes = "";
    
    String req1 = "SELECT * FROM EMPLOYES";
    String req2 = "DELETE FROM EMPLOYES WHERE ID=?";
    String req3 = "SELECT * FROM EMPLOYES WHERE ID=?";
    String req4 = "UPDATE EMPLOYES SET NOM=?, PRENOM=? WHERE ID=?";

 /* initialisation de conn dans le constructeur de la classe ConnectionDB(définition valable pour tout le code)
    et non dans la méthode du dessous car sinon valable uniquement dans la méthode */
    
    public ConnectionDB() {

        try {
            conn = DriverManager.getConnection(url, user, mdp);

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
    }

    //méthode statique de type ResultSet et de nom getResultSet

    public ResultSet getResultSet(String req1) {

        //req1 = "SELECT * FROM EMPLOYES"
        try {
        stmt = conn.createStatement();
        rs1 = stmt.executeQuery(req1);
        
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        
           return rs1;
       
    }

    //méthode de type ArrayList et de nom getEmployes qui enregistre sous forme de tableau les résultats de rs (requête 1)
    
    public ArrayList getEmployes(ResultSet rs1) {

        ArrayList<EmployesBean> listeEmployes = null;             //listeEmployes correpsond au nom de l'ArrayList

        try {

            listeEmployes = new ArrayList<EmployesBean>();

            while (rs1.next()) {

                EmployesBean eb = new EmployesBean();

                eb.setID(rs1.getInt("ID"));
                eb.setNom(rs1.getString("NOM"));
                eb.setPrenom(rs1.getString("PRENOM"));
                eb.setAdresse(rs1.getString("ADRESSE"));
                eb.setVille(rs1.getString("VILLE"));
                eb.setEmail(rs1.getString("EMAIL"));
                eb.setTelport(rs1.getString("TELPORT"));
                eb.setTeldom(rs1.getString("TELDOM"));
                eb.setTelpro(rs1.getString("TELPRO"));
                eb.setCodepostal(rs1.getString("CODEPOSTAL"));

                listeEmployes.add(eb);

            }

        } catch (SQLException e) {
            System.out.println("ERREUR :" + e.getMessage());
        }

        return listeEmployes;

    }

  
    
    // ***************** SUPPRIMER UN EMPLOYE ********(Dynamique)***************************
     
    public void SupprimerEmployes(String ID) {         //on met entre () les paramètres qu'il y a dans la requête (ici, ID)

        //req2 = "DELETE * from EMPLOYES WHERE ID=?"
        
        try {
            pstmt = conn.prepareStatement(req2);        //pstmt car dynamique (?)
            pstmt.setString(1, ID);
            pstmt.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("ERREUR :" + sqle.getMessage());
        }
    }
    
    
    //******************* AFFICHER DETAILS EMPLOYE SELECTIONNE VIA ID ****************************
    
        public ResultSet getResultSetID(String ID, String req3)  {  
        
        //req3 = "SELECT * FROM EMPLOYES WHERE ID=?";
        try{ 
        pstmt = conn.prepareStatement(req3);
        pstmt.setString(1, ID);
        rs2 = pstmt.executeQuery();     //Query et non Update car pas une simple modification
        
                } catch (SQLException sqle) {
            System.out.println("ERREUR :" + sqle.getMessage());
        }
        
        return rs2;                                                   //dans rs, on a la réponse à la req5 
    }
    
    public EmployesBean getInfoEmployeID(ResultSet rs2)  {    //traduction de rs2 en bdd, avec toutes les données par rapport à rs2
      
        EmployesBean eb = new EmployesBean(); 
        
        try {
        
              
        while (rs2.next()) {
     
                eb.setNom(rs2.getString("NOM"));
                eb.setPrenom(rs2.getString("PRENOM"));
                eb.setAdresse(rs2.getString("ADRESSE"));
                eb.setVille(rs2.getString("VILLE"));
                eb.setEmail(rs2.getString("EMAIL"));
                eb.setTelport(rs2.getString("TELPORT"));
                eb.setTeldom(rs2.getString("TELDOM"));
                eb.setTelpro(rs2.getString("TELPRO"));
                eb.setCodepostal(rs2.getString("CODEPOSTAL"));

            }   
        
                } catch (SQLException sqle) {
            System.out.println("ERREUR :" + sqle.getMessage());
        }
                
        return eb;
        
    }
    
   // ****************** MODIFIER EMPLOYE ******************
    
    public void modifierEmployeID(String ID, String nom, String prenom) {   //entre () les paramètres de req4
        //req4 = "UPDATE EMPLOYES SET NOM=?, PRENOM=? WHERE ID=?";
        try {
            
        pstmt = conn.prepareStatement(req4);
        pstmt.setString(1, nom);
        pstmt.setString(2, prenom);
        pstmt.setString(3, ID);
        pstmt.executeUpdate();
        
                        } catch (SQLException sqle) {
            System.out.println("ERREUR :" + sqle.getMessage());
        }
    }
    
    
 
}
