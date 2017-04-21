/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP_Previo.DAO;

import TP_Previo.DTO.Usuario;
import TP_Previo.MySQL.MySqlHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UsuarioDAO {
    
    public boolean ValidarExistencia(Usuario usuario){
        Boolean existe=false;
        try {
            //--- Se conecta a la base de datos
            MySqlHelper mySQL = new MySqlHelper();
            Connection conn = mySQL.getConnection();
            
            //--- Prepara la sentencia para validar el Usuario
            PreparedStatement consultaUsuario = conn.prepareStatement("SELECT * FROM usuarios where id_usuario = ? AND contrasena=? ");
            consultaUsuario.setString(1, usuario.getUserName());
            consultaUsuario.setString(2, usuario.getPassword());

            //--- Ejecuta la consulta
            ResultSet rs = consultaUsuario.executeQuery();
            //--- Verifica si pudo obtener al Usuuario
            if(rs.next())   {
                existe=true;
            }
        } catch (SQLException ex) {
            System.out.println("Error al validar el usuario");
        }
        return existe;
    }   
}
