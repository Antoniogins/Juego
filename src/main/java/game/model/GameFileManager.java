package game.model;

import game.factories.FileUtilities;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class GameFileManager {

    /**
     * Con esta clase podemos manejar guardar/cargar archivos que contengan partidas o tableros
     *
     */

    public static final String nivel="Nivel";
    public static final String contadorDeNiveles="contadorDeNiveles";
    public static final String contadorDeDificultad="contadorDeDificultad";
    public static final String partida="Partida";


    public static void guardarPartida(Nivel nivels,int contadorDeNivelesss,int contadorDeDificultaddd,String gamePath){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put(AbstractGameObject.TypeLabel,partida);
        jsonObject.put(nivel,nivels.toJSONObject());
        jsonObject.put(contadorDeNiveles,contadorDeNivelesss);
        jsonObject.put(contadorDeDificultad,contadorDeDificultaddd);
        ArrayList<JSONObject> jsonObjects=new ArrayList<>();
        jsonObjects.add(jsonObject);
        FileUtilities.writeJsonsToFile(jsonObjects,gamePath);
    }

    /**
     *
     * @return array de Objetos con 3 elementos:
     *                                          [0] -> Nivel con sus datos
     *                                          [1] -> contador de niveles
     *                                          [2] -> contador de dificultad
     */
    public static Object[] cargarPartida(String gamePath){
        Object[] objects=new Object[3];
        JSONArray jsonArray= FileUtilities.readJSONsFromFile(gamePath);
        JSONObject jsonObject;
        Iterator iterator= jsonArray.iterator();
        if(iterator.hasNext()){
            if((    jsonObject=(JSONObject) iterator.next()   ).get(AbstractGameObject.TypeLabel).equals(partida)){
                objects[0]=new Nivel((JSONObject) jsonObject.get(nivel));
                objects[1]=jsonObject.get(contadorDeNiveles);
                objects[2]=jsonObject.get(contadorDeDificultad);
            }
        }


        return objects;
    }

    public static void guardarTablero(Nivel nivel, String tableroPath){
        FileUtilities.writeConcurrentListToFile(nivel.getTableroItems(),tableroPath);
    }

    public static Nivel cargarTablero(String tableroPath){
        Nivel n=new Nivel();
        n.setTableroItems(FileUtilities.readConcurrentListFromFile(tableroPath));
        return n;
    }

}
