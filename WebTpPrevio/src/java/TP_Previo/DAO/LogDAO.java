/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP_Previo.DAO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import org.json.simple.JSONObject;


public class LogDAO {
    
    public static void GrabarLog(String usuario, Date fechaHora, String actividad, String resultado) 
    {
        
        JSONObject obj = new JSONObject();
        obj.put("usuario", usuario);         
        obj.put("fechaHora", fechaHora);         
        obj.put("actividad", actividad);         
        obj.put("resultado", resultado);
        JSONWrite.main(obj.toString());
    }   

}

