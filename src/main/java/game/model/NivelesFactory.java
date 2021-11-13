package game.model;

import common.IGameObject;
import game.factories.FileUtilities;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class NivelesFactory {

    /**
     * @deprecated
     */


    /**
     * Devuelve un JSONArray que contiene Tableros en formato JSONArray
     *
     * @param pathName ruta y nombre del archivo
     * @return
     */

    public static ArrayList<Nivel> obtenerTableros(String pathName) {
        ArrayList<Nivel> tipex=new ArrayList<>();
        JSONArray jsonArray;
        Nivel nivel;
        try{
            jsonArray= FileUtilities.readJSONsFromFile(pathName);

            Iterator it=jsonArray.iterator();
            while (it.hasNext()){
                JSONObject level=(JSONObject) it.next();
                nivel=new Nivel();

                if(level.get(AbstractGameObject.TypeLabel).equals("Nivel")){
                    jsonArray=(JSONArray) level.get("content");
                    Iterator ite=jsonArray.iterator();

                    while (ite.hasNext()){
                        JSONObject igoJSON=(JSONObject) ite.next();
                        if(igoJSON.get(AbstractGameObject.TypeLabel).equals("Fly")){
                            nivel.addElement(new Fly(igoJSON));
                        }if(igoJSON.get(AbstractGameObject.TypeLabel).equals("Bee")){
                            nivel.addElement(new Bee(igoJSON));
                        }if(igoJSON.get(AbstractGameObject.TypeLabel).equals("Spider")){
                            nivel.addElement(new Spider(igoJSON));
                        }if(igoJSON.get(AbstractGameObject.TypeLabel).equals("Blossom")){
                            nivel.addElement(new Blossom(igoJSON));
                        }if(igoJSON.get(AbstractGameObject.TypeLabel).equals("RidingHood")){
                            nivel.addElement(new RidingHood(igoJSON));
                        }





                    }



                }

                tipex.add(nivel);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return tipex;
    }


    public static void guardarArrayDeTableros(ArrayList<Nivel> nivels, String pathname) {
        ArrayList<JSONObject> jsonObjects=new ArrayList<>();
        for(Nivel iGameObject: nivels){
            if(iGameObject!=null){
                jsonObjects.add(iGameObject.toJSONObject());
            }
        }
        try{
            FileUtilities.writeJsonsToFile(jsonObjects,pathname);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
