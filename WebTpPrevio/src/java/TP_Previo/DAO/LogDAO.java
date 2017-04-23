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
        String strLogFileName = "C:\\Log.json";
                
        JSONObject obj = new JSONObject();
        obj.put("usuario", usuario);
        obj.put("fechaHora", fechaHora);
        obj.put("actividad", actividad);
        obj.put("resultado", resultado);
        
      /*  try (FileWriter file = new FileWriter(strLogFileName,true)) {
            file.write(obj.toJSONString());
            file.write("\r\n");     //--- Hace que salte de linea
            file.flush();           //--- Fuerza a que grabe a Disco
        } catch (IOException e) {
            e.printStackTrace();
        } */
        try{
            File archivo = new File(strLogFileName);
             try (FileWriter escribir = new FileWriter(archivo,true)) {
                escribir.write(obj.toJSONString());
                escribir.write("\r\n");     //--- Hace que salte de linea
                escribir.flush();           //--- Fuerza a que grabe a Disco
             }
          }
            catch (IOException e){
                System.out.println("Error al escribir el archivo json");
            }
    }

    
    
}
