<%-- 
    Document   : index
    Created on : 31 mai 2018, 09:46:09
    Author     : esic
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
      
       <form action="Controleur" method="POST">     <% //Quand on clique sur le bouton Envoyer (de type submit), ça va effectuer le code de controleur.java %>
    
<label>Login : <input type="text" name="chLogin"/></label>
<br />
<label>Mot de passe : <input type="text" name="chMdp"/></label>
<br />
<input type="submit" name="btnOK" value="Envoyer"/>  


<%  
    
    Boolean testErr = (Boolean) session.getAttribute("cleTestErr"); //on récupère cleTestErr  TOUJOURS MEME SYNTAXE POUR GETATTRIBUTE
    
    if (testErr != null){     //si testErr est true ou false
    
    if (testErr){ 
    out.println("Vous devez renseigner les deux champs.");
    }
    }


%>


</form>
        
 
    </body>
</html>
