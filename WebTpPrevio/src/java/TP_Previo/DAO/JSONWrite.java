

package TP_Previo.DAO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JSONWrite {
    public static void main (String json ){
         try{
            File archivo = new File("C:\\Users\\Victoria\\Documents\\NetBeansProjects\\DDS-TP-Previo\\WebTpPrevio\\Log.json");
             try (FileWriter escribir = new FileWriter(archivo,true)) {
                 escribir.write(json);
             }
          }
            catch (IOException e){
                System.out.println("Error al escribir el archivo json");
            }
        } 
    }

