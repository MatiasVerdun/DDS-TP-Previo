/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP_Previo.Controllers;

import TP_Previo.DAO.SeleccionZonaDAO;
import TP_Previo.DTO.Estado;
import TP_Previo.DTO.SeleccionZona;
import TP_Previo.Services.MercadoPagoService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RegistrarZonaControllerServlet extends HttpServlet {

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
        if (request.getParameter("confirmarSeleccion")!=null)
        {
            //--- Indica que debe grabar la Selección del Usuario
            
            //--- Obtiene los datos Seleecionados
            String idPais=request.getParameter("Pais");  
            String idEstado=request.getParameter("Provincia");  
            //
            //--- Accede al Objeto de Seleccion de Zona, lo actualiza
            //--- lo guarda en la Session y envia a grabar la Seleccion
            SeleccionZona objSeleccionZona = (SeleccionZona)request.getSession().getAttribute("zonaBean");
            objSeleccionZona.setId_pais(idPais);
            objSeleccionZona.setId_estado(idEstado);
            objSeleccionZona.setFechaHora(new Date());
            request.getSession().setAttribute("zonaBean",objSeleccionZona);  
            //
            //--- Guarda la Seleccion de la Zona en la Base de Datos
            SeleccionZonaDAO selZonaDAO = new SeleccionZonaDAO();
            selZonaDAO.GuardarSeleccionZona(objSeleccionZona);
            //
            RequestDispatcher rd=request.getRequestDispatcher("SeleccionZonaOK.jsp");  
            rd.forward(request, response);  
        }            
        else
        {
            //--- Indica que debe cargar los Estados del Pais
            
            //--- Obtiene el Pais Seleccionado
            String idPais=request.getParameter("Pais");  
            //
            //--- Accede al Objeto de Seleccion de Zona, lo actualiza
            //--- lo guarda en la Session
            SeleccionZona objSeleccionZona = (SeleccionZona)request.getSession().getAttribute("zonaBean");
            objSeleccionZona.setId_pais(idPais);
            request.getSession().setAttribute("zonaBean",objSeleccionZona);  
            //
            MercadoPagoService mpService = new MercadoPagoService();
            ArrayList<Estado> estados = mpService.obtenerEstados(idPais);
            request.setAttribute("estadosBean",estados);  
            //
            RequestDispatcher rd=request.getRequestDispatcher("ElegirZona.jsp");  
            rd.forward(request, response);  
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
