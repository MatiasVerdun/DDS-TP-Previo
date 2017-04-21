/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP_Previo.DAO;

import TP_Previo.DTO.SeleccionZona;
import TP_Previo.MySQL.MySqlHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;


public class SeleccionZonaDAO {
    public void GuardarSeleccionZona(SeleccionZona seleccionZona){
        try {
            //--- Se conecta a la base de datos
            MySqlHelper mySQL = new MySqlHelper();
            Connection conn = mySQL.getConnection();
            
            //--- Prepara la sentencia para validar el Usuario
            PreparedStatement guardarSeleccionZona = conn.prepareStatement("INSERT INTO `seleccionzonas` (`id_usuario`, `id_pais`, `id_estado`, `fechaHora`) VALUES (?, ?, ?, ?)");
            guardarSeleccionZona.setString(1, seleccionZona.getId_usuario());
            guardarSeleccionZona.setString(2, seleccionZona.getId_pais());
            guardarSeleccionZona.setString(3, seleccionZona.getId_estado());
            Date fechaHora = seleccionZona.getFechaHora();
            java.sql.Date sqlFechaHora = new java.sql.Date(fechaHora.getTime());
            guardarSeleccionZona.setDate(4,sqlFechaHora);

            //--- Ejecuta la sentencia para almacenar datos
            guardarSeleccionZona.execute();
        } catch (SQLException ex) {
            System.out.println("Error al registrar la Seleccion de Zona");
        }
    }               
}
