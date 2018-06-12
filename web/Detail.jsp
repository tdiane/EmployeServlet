<%-- 
    Document   : Detail
    Created on : 1 juin 2018, 13:31:51
    Author     : esic
--%>

<%@page import="com.solutec.EmployesBean"%>
<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>

        <meta content="text/html; charset=ISO-8859-1" http-equiv="content-type"><title></title>

    </head>

    <body>

        <span style="font-weight: bold;"></span><big><big><span style="font-weight: bold;">Détails</span><br style="font-weight: bold;">
        </big></big><br>
    <span style="font-weight: bold; color: red;"></span><br>

    <%
        EmployesBean eb = (EmployesBean) session.getAttribute("cleInfoEmployeID");

    %>

    <form action="Controleur">           <% //on redirige toujours vers le fichier qui contient tous les codes %>


        <label for="nom">Nom : </label><input value="<% out.println(eb.getNom());%>" id="nom" name="nom" onblur="leaveField(this);"> <label for="prenom">Prénom
            : </label><input value="<% out.println(eb.getPrenom());%>" id="prenom" name="prenom" onblur="leaveField(this); leavePrenom();">
        <br>
        <br>

        <span style="font-weight: bold;">Numéros de téléphone</span><br>

        <label for="teldom">Domicile : </label><input value="<% out.println(eb.getTeldom());%>" name="teldom" id="teldom" onblur="leaveField(this); checkTel(this);"><br>
        <label for="telpor">Portable : </label><input value="<% out.println(eb.getTelport());%>" name="telpor" id="telpor" onblur="leaveField(this); checkTel(this);"><br>
        <label for="telpro">Professionnel : </label><input value="<% out.println(eb.getTelpro());%>" name="telpro" id="telpro" onblur="leaveField(this); checkTel(this);"><br>
        <br>
        <label for="adresse">Adresse : </label><input value="<% out.println(eb.getAdresse());%>" name="adresse" id="adresse" onblur="leaveField(this);"><br>
        <label for="cp">Code postal : </label><input value="<% out.println(eb.getCodepostal());%>" name="cp" id="cp" onblur="leaveField(this); checkCP();"><br>
        <label for="ville">Ville : </label><input value="<% out.println(eb.getTeldom());%>" name="ville" id="ville" onblur="leaveField(this);"><br>
        <label for="mail">E-mail : </label><input id="mail" name="mail" value="<% out.println(eb.getEmail());%>" onblur="leaveField(this); checkMail();"><br>
        <br>


        <input type="submit" name="bouton" value="Voir Liste"><br>
        <input type="submit" name="bouton" value="Modifier"><br>

    </form>

</body>

</html>
