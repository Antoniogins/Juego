/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.factories;
import common.IGameObject;
import game.model.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.json.JSONArray;
import org.json.JSONObject;

public class FileUtilities {
    
    /**
     * Crea un directorio si no existe.
     * @param pathname nombre del directorio.
     */
    public static void crearDirectorio(String pathname) throws SecurityException {
        File directorio = new File(pathname);
        if (!directorio.exists()) {
            System.out.println("crearDirectorio: directorio ha creado en " + pathname);
            directorio.mkdir();
        }
        else{
            System.out.println("crearDirectorio: directorio ya existe en " + pathname);
        }
    }



    /**
     * Escribe un string en un fichero de texto.
     * @param s 
     * @param fichero fiechero destino
     * @throws java.io.FileNotFoundException
     */
    public static void writeToFile(String s, String fichero) throws FileNotFoundException {
        if (s == null || fichero == null){
            return;
        }
        PrintWriter streamWriter;
        try {
            streamWriter = new PrintWriter(new FileOutputStream (fichero));
            streamWriter.println(s);
            streamWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("escribirEnFichero: FileNotFoundException");
            throw e;
        }
    }


    /**
     * Escribe un array de jsons en un fichero de texto, 
     * cada uno en una línea diferente.
     * @param jsonObjects array de JSONObjects
     * @param fileName fichero destino.
     */
    public static void writeJsonsToFile(ArrayList<JSONObject> jsonObjects, String fileName) {
        PrintWriter streamWriter;
        // Test arguments.
        if (jsonObjects == null || fileName == null){
            return;
        }
        // Write each json object in a new line.
        try {
            streamWriter = new PrintWriter(new FileOutputStream (fileName));
            for (JSONObject item : jsonObjects) {
                streamWriter.println(item.toString());
            }
            streamWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("escribirEnFichero: FileNotFoundException");
        }
    }


    public static void writeJSONArrayToFile(JSONArray jsonArray,String filename) throws FileNotFoundException {
        writeToFile(jsonArray.toString(),filename);

    }

    public static ArrayList<JSONArray> readJSONArrayFromFile(String fileName){
        String line;
        BufferedReader fileReader;
        ArrayList<JSONArray> jArray = new ArrayList<>();
        try{
            fileReader = new BufferedReader(new FileReader(fileName));

            while((line = fileReader.readLine()) != null){
                jArray.add(new JSONArray(line));
            }
            fileReader.close();
        } catch(IOException ioe){
            jArray = null;
        }
        return jArray;
    }

    /**
     * Lee objetos json de un fichero de TEXTO en donde han sido
     * previamente guardados usando writeJsonsToFile.
     * @param fileName ruta del fichero
     * @return contenido del fichero
     */
    public static JSONArray readJSONsFromFile(String fileName) {
        String line;
        BufferedReader fileReader;
        JSONArray jArray = new JSONArray(); 
        try{
            fileReader = new BufferedReader(new FileReader(fileName));

            while((line = fileReader.readLine()) != null){
                jArray.put(new JSONObject(line));
            }
            fileReader.close();            
        } catch(IOException ioe){
            jArray = null;
        } 
        return jArray;
    }  


    //TODO eliminar despues de omplementar JSONARRAY
    public static void writeConcurrentListToFile(ConcurrentLinkedQueue<IGameObject> iGameObjects,String pathname){
        ArrayList<JSONObject> jsonObjects=new ArrayList<>();
        for(IGameObject igo:iGameObjects){
            jsonObjects.add(igo.toJSONObject());
        }
        writeJsonsToFile(jsonObjects,pathname);
    }

    //TODO eliminar despues de implementar JSONARRAY
    public static ConcurrentLinkedQueue<IGameObject> readConcurrentListFromFile(String pathname){
        JSONArray jsonArray= readJSONsFromFile(pathname);
        ConcurrentLinkedQueue<IGameObject> iGameObjects=new ConcurrentLinkedQueue<>();
        Iterator it= jsonArray.iterator();
        JSONObject tempo;
        while (it.hasNext()){
            tempo=(JSONObject) it.next();
            if(tempo.get(AbstractGameObject.TypeLabel).equals("RidingHood")){
                iGameObjects.add(new RidingHood(tempo));
            }else if(tempo.get(AbstractGameObject.TypeLabel).equals("Blossom")){
                iGameObjects.add(new Blossom(tempo));
            }else if(tempo.get(AbstractGameObject.TypeLabel).equals("Bee")){
                iGameObjects.add(new Bee(tempo));
            }else if(tempo.get(AbstractGameObject.TypeLabel).equals("Fly")){
                iGameObjects.add(new Fly(tempo));
            }else if(tempo.get(AbstractGameObject.TypeLabel).equals("Spider")){
                iGameObjects.add(new Spider(tempo));
            }
        }

        return iGameObjects;

    }

}
