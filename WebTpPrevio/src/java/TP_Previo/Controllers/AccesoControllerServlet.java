/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP_Previo.Controllers;

import TP_Previo.DAO.LogDAO;
import TP_Previo.DAO.UsuarioDAO;
import TP_Previo.DTO.Estado;
import TP_Previo.DTO.Pais;
import TP_Previo.DTO.SeleccionZona;
import TP_Previo.DTO.Usuario;
import TP_Previo.Services.MercadoPagoService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "AccesoControllerServlet", urlPatterns = {"/AccesoControllerServlet"})
public class AccesoControllerServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();  
          //--- Determina la acción seleccionada
        if (request.getParameter("cambioClave")!=null)
        {
            //
            RequestDispatcher rd=request.getRequestDispatcher("CambioClave.jsp");  
            rd.forward(request, response);  
        }  
        else{ 
            //--- Obtiene los datos desde la Vista (Login.jsp)
            String strUserName=request.getParameter("Usuario");  
            String strPassword=request.getParameter("Clave");  

            //--- Crea el objeto usuario que desde Validar
            Usuario objUsuario=new Usuario();  
            objUsuario.setUserName(strUserName);  
            objUsuario.setPassword(strPassword); 

            //--- Controla si existe el usuario en la Tabla
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            boolean existe = usuarioDAO.ValidarExistencia(objUsuario);

            //--- Graba el LOG de accesos
            //--- (utiliza un if en linea para definir el resultado)
            LogDAO.GrabarLog(strUserName, new Date(), "Acceso", (existe ? "Permitido" : "Denegado"));          

            //--- Determina la acci�n en base a la existencia
            if(existe){
                //--- Guarda en la Session los datos del Usuario
                request.getSession().setAttribute("usuarioBean",objUsuario);  
                //
                //--- Inicializa el Objeto de Seleccion de Zona y
                //--- lo guarda en la Session
                SeleccionZona objSeleccionZona = new SeleccionZona();
                objSeleccionZona.setId_usuario(strUserName);
                request.getSession().setAttribute("zonaBean",objSeleccionZona);  
                //
                //--- Obtiene el listado de Paises
                MercadoPagoService mpService = new MercadoPagoService();
                ArrayList<Pais> paises = mpService.obtenerPaises();
                //--- Para no acceder continuamente almacena los Paises en la Session
                request.getSession().setAttribute("paisesBean",paises);
                //
                //--- Almacena una lista vacia de Estados
                ArrayList<Estado> estados = new ArrayList<Estado>();
                request.setAttribute("estadosBean",estados);  
                //
                RequestDispatcher rd=request.getRequestDispatcher("ElegirZona.jsp");  
                rd.forward(request, response);  
            }  
            else{  
                RequestDispatcher rd=request.getRequestDispatcher("Login-error.jsp");  
                rd.forward(request, response);  
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
