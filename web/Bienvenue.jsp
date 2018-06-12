<%-- 
    Document   : Bienvenue
    Created on : 31 mai 2018, 10:05:48
    Author     : esic
--%>

<%@page import="com.solutec.EmployesBean"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

    <body>

        <%
            //Infos des Employes sous forme de tableau
            
            ArrayList<EmployesBean> listeEmployes = (ArrayList<EmployesBean>) session.getAttribute("cleListeEmployes"); //cleListeEmployes contient toutes les infos des employés
            //les set et getAttribute permettent de transférer des données d'une classe à une autre (stockagee d'infos)

        %>

        <form action="Controleur">

            <table border =1>

                <tr>
                    <td><b>Details</b></td>
                    <td><b>Nom</b></td>
                    <td><b>Prenom</b></td>
                    <td><b>Tel Dom</b></td>
                    <td><b>Tel Port</b></td>
                    <td><b>Tel Pro</b></td>
                    <td><b>Adresse</b></td>
                    <td><b>Code Postal</b></td>
                    <td><b>Ville</b></td>
                    <td><b>Email</b></td>

                </tr>

                <%                  
                    for (EmployesBean e : listeEmployes) {
                %>

                <tr> 
                    <td>  <INPUT TYPE="radio" NAME="idClient" VALUE="<% out.println(e.getID());%>" CHECKED ></td>  <% //valeur idClient définie dans controleur %>

                    <td>  <% out.println(e.getNom());%> </td>
                    <td>  <% out.println(e.getPrenom());%>    </td>
                    <td>  <% out.println(e.getTeldom());%>    </td>
                    <td>  <% out.println(e.getTelport());%>  </td>
                    <td>  <% out.println(e.getTelpro());%>    </td>
                    <td>  <% out.println(e.getAdresse());%>  </td>
                    <td>  <% out.println(e.getCodepostal());%> </td>
                    <td>  <% out.println(e.getVille());%>     </td>
                    <td>  <% out.println(e.getEmail());%>     </td>

                </tr>    
                <% }%>


            </table>

            <input type='submit' name="bouton" value="Detail"  />
            <input type='submit' name="bouton" value="Quitter"/>
            <input type='submit' name="bouton" value="Supprimer"/>



        </form>



    </body>
</html>
