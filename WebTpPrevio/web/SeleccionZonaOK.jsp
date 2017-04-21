
<%@page import="TP_Previo.DTO.*"%>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
.error
{
    color: red;
    font-size:110%
}
</style>    
</head>
<body>
<form action="" method="post">
<br>
    <fieldset style="width:50%;text-align:center;background-color: rgb(245, 245, 245); border-color:#00c8f8;text-align: center; margin:auto">
<br>
    <h1>
    <%  
    Usuario usuario=(Usuario)request.getSession().getAttribute("usuarioBean");  
    out.print("Muchas gracias "+usuario.getUserName());  
    %>, <br/>
    hemos guardado su selección.
    </h1>
<br>
  </fieldset>
</form>
</body>
</html>