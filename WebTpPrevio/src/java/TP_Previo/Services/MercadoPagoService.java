/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP_Previo.Services;

import TP_Previo.DTO.Ciudad;
import TP_Previo.DTO.Estado;
import TP_Previo.DTO.Pais;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;



public class MercadoPagoService {
    /*
    Método para obtener el listado de Paises
    */
    public ArrayList<Pais> obtenerPaises() {    
       
        //--- URL para obtener el listado de paises
        String urlCountries = "https://api.mercadolibre.com/countries";
        ArrayList<Pais> listPaises = new ArrayList<Pais>();
        
        try {
            URL obj = new URL(urlCountries);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            //--- Setea las condiciones del requerimiento
            //--- (GET y respuesat en JSON)
            conn.setRequestMethod( "GET");
            conn.setRequestProperty("Accept", "application/json");

            //--- Obtiene el codigo de Respuesta
            //--- 200 = OK
            int responseCode = conn.getResponseCode();
            if (responseCode!=200)
                throw new RuntimeException("ERROR al obtener Paises. (HTTP error code: "+ responseCode +")");

            StringBuilder response;
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()))) {
                String inputLine;
                response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }

            //--- Obtiene toda la respuesta
            String jsonCountries = response.toString();
            
            //--- Crea el objeto para parsear JSON
            JSONParser parser = new JSONParser();
            try{
                //--- Parsea el JSON obtenido en la respuesta
                JSONArray arrayCountries = (JSONArray)parser.parse(jsonCountries);
                
                //--- Recorre los items y genera la lista de Objetos
                int i = 0;
                while(i<arrayCountries.size()){
                    JSONObject objPais = (JSONObject) arrayCountries.get(i);
                    //
                    //--- Crea un nuevo objeto Pais y le carga los datos
                    Pais pais = new Pais();
                    pais.setId(objPais.get("id").toString());
                    pais.setName(objPais.get("name").toString());
                    //
                    //--- Agrega el Pais a la Lista
                    listPaises.add(pais);
                    i++;
                }
            }catch(ParseException pe){
                System.out.println("Error en parseo de Paises.");
            }                
        } catch (MalformedURLException ex) {
            System.out.println("Error al obtener Paises.");
        } catch (IOException ex) {
            System.out.println("Error al obtener Paises.");
        }
    
        return listPaises;
    }

    /*
    Método para obtener el listado de Estados
    */
    public ArrayList<Estado> obtenerEstados(String idPais) {    
       
        //--- URL para obtener el detalle de un Pais
        String urlCountry = "https://api.mercadolibre.com/countries/"+idPais;
        ArrayList<Estado> listEstados = new ArrayList<Estado>();
        
        try {
            URL obj = new URL(urlCountry);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            //--- Setea las condiciones del requerimiento
            //--- (GET y respuesat en JSON)
            conn.setRequestMethod( "GET");
            conn.setRequestProperty("Accept", "application/json");

            //--- Obtiene el codigo de Respuesta
            //--- 200 = OK
            int responseCode = conn.getResponseCode();
            if (responseCode!=200)
                throw new RuntimeException("ERROR al obtener Paises. (HTTP error code: "+ responseCode +")");

            StringBuilder response;
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()))) {
                String inputLine;
                response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }

            //--- Obtiene toda la respuesta
            String jsonCountry = response.toString();
            
            //--- Crea el objeto para parsear JSON
            JSONParser parser = new JSONParser();
            try{
                //--- Parsea el JSON obtenido en la respuesta
                JSONObject objPais = (JSONObject)parser.parse(jsonCountry);
                JSONArray arrayStates = (JSONArray)objPais.get("states");
                
                int i = 0;
                while(i<arrayStates.size()){
                    JSONObject objEstado = (JSONObject) arrayStates.get(i);
                    //
                    //--- Crea un nuevo objeto Estado y le carga los datos
                    Estado estado = new Estado();
                    estado.setId(objEstado.get("id").toString());
                    estado.setName(objEstado.get("name").toString());
                    //
                    //--- Agrega el Estado a la Lista
                    listEstados.add(estado);
                    i++;
                }
            }catch(ParseException pe){
                System.out.println("Error en parseo de Estados.");
            }                
        } catch (MalformedURLException ex) {
            System.out.println("Error al obtener Estados.");
        } catch (IOException ex) {
            System.out.println("Error al obtener Estados.");
        }
    
        return listEstados;
    }

    /*
    Método para obtener el listado de Ciudades
    */
    public ArrayList<Ciudad> obtenerCiudades(String idEstado) {    
       
        //--- URL para obtener el detalle del Estado
        String urlState = "https://api.mercadolibre.com/states/"+idEstado;
        ArrayList<Ciudad> listCiudades = new ArrayList<Ciudad>();
        
        try {
            URL obj = new URL(urlState);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            //--- Setea las condiciones del requerimiento
            //--- (GET y respuesat en JSON)
            conn.setRequestMethod( "GET");
            conn.setRequestProperty("Accept", "application/json");

            //--- Obtiene el codigo de Respuesta
            //--- 200 = OK
            int responseCode = conn.getResponseCode();
            if (responseCode!=200)
                throw new RuntimeException("ERROR al obtener Paises. (HTTP error code: "+ responseCode +")");

            StringBuilder response;
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()))) {
                String inputLine;
                response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }

            //--- Obtiene toda la respuesta
            String jsonState = response.toString();
            
            //--- Crea el objeto para parsear JSON
            JSONParser parser = new JSONParser();
            try{
                //--- Parsea el JSON obtenido en la respuesta
                JSONObject objEstado = (JSONObject)parser.parse(jsonState);
                JSONArray arrayCities = (JSONArray)objEstado.get("cities");
                
                int i = 0;
                while(i<arrayCities.size()){
                    JSONObject objCiudad = (JSONObject) arrayCities.get(i);
                    //
                    //--- Crea un nuevo objeto Ciudad y le carga los datos
                    Ciudad ciudad = new Ciudad();
                    ciudad.setId(objCiudad.get("id").toString());
                    ciudad.setName(objCiudad.get("name").toString());
                    //
                    //--- Agrega la Ciudad a la Lista
                    listCiudades.add(ciudad);
                    i++;
                }                
            }catch(ParseException pe){
                System.out.println("Error en parseo de Ciudades.");
            }                
        } catch (MalformedURLException ex) {
            System.out.println("Error al obtener Ciudades.");
        } catch (IOException ex) {
            System.out.println("Error al obtener Ciudades.");
        }
    
        return listCiudades;
    }
}
