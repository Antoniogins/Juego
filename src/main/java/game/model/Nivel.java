package game.model;

import common.IGameObject;
import game.factories.GameFileManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

public class Nivel {
    private ConcurrentLinkedQueue<IGameObject> tableroItems=new ConcurrentLinkedQueue<>();

    public static final int GAME_FLY_LEVEL=1;
    public static final int GAME_BEE_LEVEL=2;
    public static final int GAME_SPIDER_LEVEL=3;
    public static final int GAME_BASE_LEVEL=99921;


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
                    }
                    else if(jsonObject.get(AbstractGameObject.TypeLabel).equals("Bee")){
                        tableroItems.add(new Bee(jsonObject));
                    }
                    else if(jsonObject.get(AbstractGameObject.TypeLabel).equals("Spider")){
                        tableroItems.add(new Spider(jsonObject));
                    }
                    else if(jsonObject.get(AbstractGameObject.TypeLabel).equals("Fly")){
                        tableroItems.add(new Fly(jsonObject));
                    }
                    else if(jsonObject.get(AbstractGameObject.TypeLabel).equals("Blossom")){
                        tableroItems.add(new Blossom(jsonObject));
                    }
                    else if(jsonObject.get(AbstractGameObject.TypeLabel).equals("Wall")){
                        tableroItems.add(new Wall(jsonObject));
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
                    }
                    else if(jsonObject.get(AbstractGameObject.TypeLabel).equals("Bee")){
                        tableroItems.add(new Bee(jsonObject));
                    }
                    else if(jsonObject.get(AbstractGameObject.TypeLabel).equals("Spider")){
                        tableroItems.add(new Spider(jsonObject));
                    }
                    else if(jsonObject.get(AbstractGameObject.TypeLabel).equals("Fly")){
                        tableroItems.add(new Fly(jsonObject));
                    }
                    else if(jsonObject.get(AbstractGameObject.TypeLabel).equals("Blossom")){
                        tableroItems.add(new Blossom(jsonObject));
                    }
                    else if(jsonObject.get(AbstractGameObject.TypeLabel).equals("Wall")){
                        tableroItems.add(new Wall(jsonObject));
                    }
                }
            }
        }
    }



    public RidingHood getRidingHood(){
        for(IGameObject i:tableroItems){
            if(i instanceof RidingHood){
                return (RidingHood) i;
            }
        }
        return null;
    }
    public void setRidingHood(RidingHood ridingHood){
        tableroItems.removeIf(c->c instanceof RidingHood);

//        for(IGameObject iGameObject:tableroItems){
//            if(iGameObject!=null && iGameObject.getClass().getSimpleName().equals("RidingHood")){
//                tableroItems.remove(iGameObject);
//            }
//        }
        tableroItems.add(ridingHood);
    }

    public ArrayList<Blossom> getBlossomsArrayList(){
        ArrayList<Blossom> blossoms=new ArrayList<>();
        for(IGameObject igo:this.tableroItems){
            if(igo instanceof Blossom){
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

    public ArrayList<Wall> getWallsArrayList(){
        ArrayList<Wall> arrayList=new ArrayList<>();
        Iterator iterator=this.tableroItems.iterator();
        while (iterator.hasNext()){
            IGameObject iGameObject=(IGameObject) iterator.next();
            if(iGameObject instanceof Wall){
                arrayList.add((Wall) iGameObject);
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
    public void removeElement(IGameObject iGameObject){
        if(iGameObject!=null){
            tableroItems.remove(iGameObject);
        }
    }
    public ConcurrentLinkedQueue<IGameObject> getTableroItems() {
        return this.tableroItems;
    }
    public void setTableroItems(ConcurrentLinkedQueue<IGameObject> tableroItems) {
        this.tableroItems = tableroItems;
    }
    public String toString(){
        String n="";
        for(IGameObject igo:tableroItems){
            n+=igo.toJSONObject().toString()+"\n";
        }
        return n;
    }


    /**
     * Genera objetos hostiles (Spider,Bee y Fly) sobre un tablero dado
     *
     * @param level_type
     * @param dificultad
     * @param nivel
     */
    public static void createHostileObjects(int level_type,int dificultad, Nivel nivel){
        switch (level_type) {
            case GAME_FLY_LEVEL:
                for(int m=1; m<=dificultad;m++){
                    nivel.addElement(new Fly(Position.generarPosicionRandom(), (int) (Math.random()*24), 1));
                }
                break;
            case GAME_BEE_LEVEL:
                for(int m=1; m<=dificultad;m++){
                    nivel.addElement(new Bee(Position.generarPosicionRandom(), (int) (Math.random()*24), 1));
                }
                break;
            case GAME_SPIDER_LEVEL:
                for(int m=1; m<=dificultad;m++){
                    nivel.addElement(new Spider(Position.generarPosicionRandom(), (int) (Math.random()*24), 1));
                }
                break;
        }
    }


    /**
     * Comprueba si la posicion dada esta ocupada por un blossom dentro del nivel dado
     */
    public static boolean checkBlossomOverWall(Nivel nivel, Position position){
        //TODO no funciona xDDD
        for(IGameObject bls:nivel.getTableroItems()){
            if(bls.getPosition().isEqual(position)){
                return false;
            }
        }
        return true;
    }


    /**
     * Este metodo crea la base del nivel (objetos fijos: blossom y wall) sobre un nivel dado
     *
     * @param dificultad
     * @param nivel
     */
    public static void createLevelBase(int dificultad, Nivel nivel){
        for(int i=0;i<dificultad+4;i++){
            nivel.addElement(new Blossom(Position.generarPosicionRandom(), (int) (Math.random() * 12), 1));
        }
        for(int m=0;m<dificultad+1;m++){
            boolean continu=true;

            do{
                Position request=Position.generarPosicionRandom();
                //Comprobamos si la posicion para la pared esta encima de un blossom (es un problema)
                if(checkBlossomOverWall(nivel,request)){
                    nivel.addElement(new Wall(request, (int) (Math.random() * 12), 1));
                    continu=false;
                }
            }while (continu);



        }



    }


    /**
     * Este metodo hace que el nivel que le pasamos se convierta en un nivel base (unicamente contiene blossoms y walls)
     * @param nivel
     */
    public static void setToBaseLevel(Nivel nivel){
        for(IGameObject igo:nivel.getTableroItems()){
            if(igo!=null){
                if(igo instanceof Bee || igo instanceof Fly || igo instanceof Spider || igo instanceof RidingHood){
                    nivel.removeElement(igo);
                }
            }
        }
    }




}
