package game.model;

import common.IGameObject;
import game.factories.GameFileManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Nivel {
    private ConcurrentLinkedQueue<IGameObject> tableroItems=new ConcurrentLinkedQueue<>();


    /**
     * Los niveles son un decorador para manejar los ConcurrentLinkedQueue<IGameObject> de manera mas sencilla
     *
     */
    public Nivel(){}
    public Nivel(RidingHood ridingHood){
        addElement(ridingHood);
        ridingHood.setNivel(this);
    }
    public Nivel(Nivel nivel){
        tableroItems=new ConcurrentLinkedQueue<>();
        for(IGameObject ago:nivel.getTableroItems()){
            if(ago.getClass().getSimpleName().equals("Fly")){
                tableroItems.add(new Fly((Fly) ago));
            } else if(ago.getClass().getSimpleName().equals("Bee")){
                tableroItems.add(new Bee((Bee)ago));
            } else if(ago.getClass().getSimpleName().equals("Spider")){
                tableroItems.add(new Spider((Spider) ago));
            }else if(ago.getClass().getSimpleName().equals("Blossom")){
                tableroItems.add(new Blossom((Blossom) ago));
            }else if(ago.getClass().getSimpleName().equals("RidingHood")){
                tableroItems.add(new RidingHood((RidingHood) ago));
            }
        }

    }


    public Nivel(JSONArray jsonArray){
        if(jsonArray!=null&&jsonArray.length()!=0){
            try{
                Iterator it=jsonArray.iterator();
                JSONObject jsonObject;
                while (it.hasNext()){
                    jsonObject=(JSONObject) it.next();

                    if(jsonObject.get(AbstractGameObject.TypeLabel).equals("RidingHood")){
                        tableroItems.add(new RidingHood(jsonObject));
                    }else if(jsonObject.get(AbstractGameObject.TypeLabel).equals("Bee")){
                        tableroItems.add(new Bee(jsonObject));
                    }else if(jsonObject.get(AbstractGameObject.TypeLabel).equals("Spider")){
                        tableroItems.add(new Spider(jsonObject));
                    }else if(jsonObject.get(AbstractGameObject.TypeLabel).equals("Fly")){
                        tableroItems.add(new Fly(jsonObject));
                    }else if(jsonObject.get(AbstractGameObject.TypeLabel).equals("Blossom")){
                        tableroItems.add(new Blossom(jsonObject));
                    }

                }



            }catch (Exception e){
                System.out.println("Error al leer el array del nivel");
            }


        }
    }

    public Nivel(JSONObject jsonObject){
        if(jsonObject!=null){
            if(jsonObject.get(AbstractGameObject.TypeLabel).equals(GameFileManager.nivel)){
                JSONArray jsonArray=(JSONArray) jsonObject.get("content");
                Iterator iterator= jsonArray.iterator();;
                while (iterator.hasNext()){
                    jsonObject=(JSONObject) iterator.next();

                    if(jsonObject.get(AbstractGameObject.TypeLabel).equals("RidingHood")){
                        tableroItems.add(new RidingHood(jsonObject));
                    }else if(jsonObject.get(AbstractGameObject.TypeLabel).equals("Bee")){
                        tableroItems.add(new Bee(jsonObject));
                    }else if(jsonObject.get(AbstractGameObject.TypeLabel).equals("Spider")){
                        tableroItems.add(new Spider(jsonObject));
                    }else if(jsonObject.get(AbstractGameObject.TypeLabel).equals("Fly")){
                        tableroItems.add(new Fly(jsonObject));
                    }else if(jsonObject.get(AbstractGameObject.TypeLabel).equals("Blossom")){
                        tableroItems.add(new Blossom(jsonObject));
                    }
                }
            }
        }
    }



    public RidingHood getRidingHood(){
        //TODO implementar WOW
        for(IGameObject i:tableroItems){
            if(i.getClass().getSimpleName().equals("RidingHood")){
                return (RidingHood) i;
            }
        }
        return null;
    }
    public void setRidingHood(RidingHood ridingHood){
        for(IGameObject iGameObject:tableroItems){
            if(iGameObject!=null && iGameObject.getClass().getSimpleName().equals("RidingHood")){
                tableroItems.remove(iGameObject);
            }
        }
        tableroItems.add(ridingHood);
    }

    public ArrayList<Blossom> getBlossomsArrayList(){
        ArrayList<Blossom> blossoms=new ArrayList<>();
        for(IGameObject igo:this.tableroItems){
            if(igo.getClass().getSimpleName().equals("Blossom")){
                blossoms.add((Blossom) igo);
            }
        }


        return blossoms;
    }

    public ArrayList<Spider> getSpidersArrayList(){
        ArrayList<Spider> spider=new ArrayList<>();
        for(IGameObject i:this.tableroItems){
            if(i.getClass().getSimpleName().equals("Blossom")){
                spider.add((Spider) i);
            }
        }
        return spider;
    }

    public ArrayList<Bee> getBeeArrayList(){
        ArrayList<Bee> bee=new ArrayList<>();
        for(IGameObject i:this.tableroItems){
            if(i.getClass().getSimpleName().equals("Blossom")){
                bee.add((Bee) i);
            }
        }
        return bee;
    }

    public ArrayList<Fly> getFleesArrayList(){
        ArrayList<Fly> fly=new ArrayList<>();
        for(IGameObject i:this.tableroItems){
            if(i.getClass().getSimpleName().equals("Blossom")){
                fly.add((Fly) i);
            }
        }
        return fly;
    }

    public ArrayList<IGameObject> getActiveObjectsWithoutRidingHood(){
        ArrayList<IGameObject> arrayList=new ArrayList<>();
        Iterator iterator=this.tableroItems.iterator();
        while (iterator.hasNext()){
            IGameObject iGameObject=(IGameObject) iterator.next();
            if(iGameObject.getClass().getSimpleName().equals("Fly")){
                arrayList.add(iGameObject);
            } else if(iGameObject.getClass().getSimpleName().equals("Bee")){
                arrayList.add(iGameObject);
            } else if(iGameObject.getClass().getSimpleName().equals("Spider")){
                arrayList.add(iGameObject);
            }
        }
        return arrayList;
    }


    /**
     * Devuelve un objeto JSON que contiene: AbstractGameObject.TypeLabel:"Nivel" y "content":JSONArray (con todos los elementos del trablero)
     * @return
     */
    public JSONObject toJSONObject(){
        JSONArray jsonArray=new JSONArray();
        for(IGameObject iGameObject:this.tableroItems){
            if(iGameObject!=null){
                jsonArray.put(iGameObject.toJSONObject());
            }
        }
        JSONObject returnable=new JSONObject();
        returnable.put(AbstractGameObject.TypeLabel,"Nivel");
        returnable.put("content",jsonArray);
        return returnable;
    }



    public void addElement(IGameObject iGameObject){
        if(iGameObject!=null){
            tableroItems.add(iGameObject);
        }
    }

    public ConcurrentLinkedQueue<IGameObject> getTableroItems() {
        return this.tableroItems;
    }
    public void setTableroItems(ConcurrentLinkedQueue<IGameObject> tableroItems) {
        this.tableroItems = tableroItems;
    }
    public void removeElement(IGameObject iGameObject){
        if(iGameObject!=null){
            tableroItems.remove(iGameObject);
        }
    }
    public String toString(){
        String n="";
        Iterator it=tableroItems.iterator();
        while (it.hasNext()){
            n+=((IGameObject)it.next()).toJSONObject().toString()+"\n";
        }
        return n;
    }


    /**
     * Genera niveles aleatorios en base a una dificultad dada
     *
     * @param
     */
    public static Nivel generarNivelRandom(int contadorNiveles,int dificultad,RidingHood ridingHood){
        Nivel nivel=new Nivel();
        nivel.addElement(ridingHood);
        switch (contadorNiveles) {
            case 0:
                createFourRandomBlossom(dificultad,nivel);
                break;
            case 1:
                for(int m=1; m<=dificultad;m++){
                    nivel.getTableroItems().add(new Fly(new Position((int) (Math.random() * 12), (int) (Math.random() * 12)), (int) (Math.random()*24), 1));
                }
                createFourRandomBlossom(dificultad,nivel);

                break;
            case 2:
                for(int m=1; m<=dificultad;m++){
                    nivel.getTableroItems().add(new Bee(new Position((int) (Math.random() * 12), (int) (Math.random() * 12)), (int) (Math.random()*24), 1));
                }
                createFourRandomBlossom(dificultad,nivel);
                break;
            case 3:
                for(int m=1; m<=dificultad;m++){
                    nivel.getTableroItems().add(new Spider(new Position((int) (Math.random() * 12), (int) (Math.random() * 12)), (int) (Math.random()*24), 1));
                }
                createFourRandomBlossom(dificultad,nivel);
                break;
            default:

                break;

        }

        return nivel;
    }

    /**
     * Este metodo crear 4 Blossom de posicion aleatoria y los almacena en inGameObjects
     */
    private static void createFourRandomBlossom(int dificultad,Nivel nivel){
        for(int i=0;i<dificultad+4;i++){
            nivel.getTableroItems().add(new Blossom(new Position((int) (Math.random() * 40), (int) (Math.random() * 12)), (int) (Math.random() * 12), 1));
        }

    }




}
