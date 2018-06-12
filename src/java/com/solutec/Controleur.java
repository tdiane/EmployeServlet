/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solutec;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author esic
 */
public class Controleur extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)  //processRequest va jouer le role de tour de controle, comme la classe controleur de JavaExerciceCinq
            throws ServletException, IOException {
        
            //rendre les portées sessions actives :
            HttpSession session = request.getSession();

            //il faut se connecter à la bddTP
            ConnectionBaseTP connect = new ConnectionBaseTP();

            String req = "SELECT * FROM IDENTIFIANTS";

            ResultSet rs = connect.getResultSet(req);               //méthode getResultSet de la classe ConnectionBaseTP
            Identifiants ident = connect.getIdentifiants(rs);       //méthode getIdentifiants de la classe ConnectionBaseTP      
            

            //récupérer les infos écrites dans les champs Login et Mdp et les stocker dans loginSaisi et MdpSaisi
            String loginSaisi = request.getParameter("chLogin");
            String MdpSaisi = request.getParameter("chMdp");
            
            
            //colonne Sel qui sélectionne l'id 
            String id = request.getParameter("idClient");          //id de l'employé sélectionné   (id: variable de ConnectionDB) ; idClient pour Bienvenue.jsp
            
            if (id != null) {
                session.setAttribute("cleId", id);      //on rentre la valeur de l'id sélectionné dans un HashMap appelé cleId et session (garde la valeur jusqu'à 3pages)
            }

            
            //Code pour Bienvenue.jsp       
            ConnectionDB cdb = new ConnectionDB();  //on se connecte à la bdd Employés 
            EmployesBean eb = new EmployesBean();
            

            //Etape 1 : Afficher les infos des employés si admin
            String req1 = "SELECT * FROM EMPLOYES";
            ResultSet rs1 = cdb.getResultSet(req1);

            //Etape 2 : Supprimer un employé sélectionné avec le RadioButton
            String req2 = "DELETE FROM EMPLOYES WHERE ID=?";

            ArrayList<EmployesBean> listeEmployes = new ArrayList<EmployesBean>();

            listeEmployes = cdb.getEmployes(rs1);

            session.setAttribute("cleListeEmployes", listeEmployes);    //on rentre dans cleListeEmployes la valeur de lsiteEmployes (tous les employes)

            
            //Etape 3 : afficher les détails de l'employé sélectionné avec le RadioButton
            String req3 = "SELECT * FROM EMPLOYES WHERE ID=?";
            ResultSet rs2 = cdb.getResultSetID(id, req3);
            eb = cdb.getInfoEmployeID(rs2);

            session.setAttribute("cleInfoEmployeID", eb);        //on rentre la valeur de eb dans la clé
            
            

            //différentes possibilités selon ce qu'on entre dans les champs Login et Mot de passe
            
            boolean testErr = false;   //initialisation de la variable testErr

            if (loginSaisi != null && MdpSaisi != null) {         //filet de sécurité

                if (loginSaisi.equals("") || MdpSaisi.equals("")) {

                    testErr = true;   //si l'un des deux champs est vide, alors testErr = true
                    session.setAttribute("cleTestErr", testErr);  //pour faire le lien avec la page accueil.jsp ; dans cleTestErr, on rentre la valeur de testErr (ici true)

                    request.getRequestDispatcher("accueil.jsp").forward(request, response);  //redirection vers accueil.jsp

                } else {
                    if (loginSaisi.equals(ident.getLogin()) && MdpSaisi.equals(ident.getMdp())) {

                        request.getRequestDispatcher("Bienvenue.jsp").forward(request, response);

                    } else {
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                }
            }

            
            //boutons 
            
            String btn = request.getParameter("bouton");    //on récupère dans btn (sous forme de string) la valeur de bouton

            if (btn != null) {

                if (btn.equals("Supprimer")) {

                    cdb.SupprimerEmployes(id);

                    //rediriger vers Bienvenue (liste Employés sous forme de tableaus) avec les nouveaux employés 
                    rs1 = cdb.getResultSet(req1);
                    listeEmployes = cdb.getEmployes(rs1);

                    session.setAttribute("cleListeEmployes", listeEmployes);        //on ré-initialise la liste des Employés

                    request.getRequestDispatcher("Bienvenue.jsp").forward(request, response);
                }

                if (btn.equals("Detail")) {

                    cdb.getInfoEmployeID(rs2);

                    request.getRequestDispatcher("Detail.jsp").forward(request, response);

                }

                if (btn.equals("Voir Liste")) {
                    request.getRequestDispatcher("Bienvenue.jsp").forward(request, response);
                }

                if (btn.equals("Modifier")) {
                    String Nom = request.getParameter("nom");
                    String Prenom = request.getParameter("prenom");
                    String Id = (String) session.getAttribute("cleId");
                    cdb.modifierEmployeID(Id, Nom, Prenom);

                    //rediriger vers Bienvenue (liste Employés sous forme de tableaus) avec les nouveaux employés 
                    rs1 = cdb.getResultSet(req1);
                    listeEmployes = cdb.getEmployes(rs1);

                    session.setAttribute("cleListeEmployes", listeEmployes);        //on ré-initialise la liste des Employés

                    request.getRequestDispatcher("Bienvenue.jsp").forward(request, response);

                }

            }


       
        
        
        
        
        
        
        
        
                }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>


}