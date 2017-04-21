<%@page import="TP_Previo.DTO.*"%>  
<%@page import="java.util.ArrayList"%>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
.button{
	background-color: #00c8f8;
    color: white;
    height:38px; 
    width:190px;
    font-size:120%
}
.select{
   height:35px;
   width:180px;
   border-color:#00c8f8;
   font-size:110%
}
.welcome
{
    color:#00c8f8
}
.error
{
    color: red;
    font-size:110%
}
</style>
</head>
<body>
<form action="RegistrarZonaControllerServlet" method="post">
<h1 class="welcome">
<%  
Usuario usuario=(Usuario)request.getSession().getAttribute("usuarioBean");  
out.print("Bienvenido, "+usuario.getUserName());  
%>  
</h1>
<h2>por favor seleccione la Zona.</h2>
<br>
	<fieldset style="width:12%;text-align:center;background-color: rgb(245, 245, 245); border-color:#00c8f8;text-align: center; margin:auto">
<br>
<br>
  <select required class="select" name="Pais" onchange="if(this.value != '') this.form.submit()" >
    <option value="">País</option>
<%  
//--- Obtiene el objeto de Seleccion de Zona almacenado en la Session
SeleccionZona objSeleccionZona = (SeleccionZona)request.getSession().getAttribute("zonaBean");
ArrayList<Pais> paises =(ArrayList<Pais>)request.getSession().getAttribute("paisesBean");  
for (int counter = 0; counter < paises.size(); counter++) { 		      
      out.print("<option value='"); 		
      out.print(paises.get(counter).getId()); 		
      out.print("' ");
      if (paises.get(counter).getId().equals(objSeleccionZona.getId_pais()))   {
          out.print("selected");
      }
      out.print(" >"); 		
      out.print(paises.get(counter).getName()); 		
      out.print("</option>"); 		
} 
%>   </select>
<br>
<br>
<br>
<%  
ArrayList<Estado> estados =(ArrayList<Estado>)request.getAttribute("estadosBean");  
if (estados.size()>0) {
%>  
  <select required class="select" name="Provincia">
  <option value="" disabled>Provincia</option>
    <%  
    for (int counter = 0; counter < estados.size(); counter++) { 		      
        out.print("<option value='"); 		
        out.print(estados.get(counter).getId()); 		
        out.print("' ");
        if (estados.get(counter).getId().equals(objSeleccionZona.getId_estado()))   {
            out.print("selected");
        }
        out.print(" >"); 		
        out.print(estados.get(counter).getName()); 		
        out.print("</option>"); 		
    } 
    %>  
  </select>
  <br><br><br>
  <input class="button" type="submit" name="confirmarSeleccion" value="Confirmar Selección">
<br>
<br>
<%  
} 
%>
  </fieldset>
</form>
</body>
</html>