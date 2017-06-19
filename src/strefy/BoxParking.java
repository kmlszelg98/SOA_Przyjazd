package strefy;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.ArrayList;

/**
 * Created by Kamil on 11.05.2017.
 */

@Singleton
@Startup
public class BoxParking {

    private ArrayList<Place> part1;
    private ArrayList<Place> part2;
    private ArrayList<Place> part3;
    private ArrayList<Place> placeLista;


    @PostConstruct
    public void setup(){

        part1= new ArrayList<>();
        part2 = new ArrayList<>();
        part3 = new ArrayList<>();
        placeLista = new ArrayList<>();

        for (int i=1;i<4;i++){
            for(int j=1;j<6;j++){
                placeLista.add(new Place(j,i,"true",null));
            }
        }

        for (Place m: placeLista) {
            add(m);
            System.out.println(m);
        }

        System.out.println(part1.size());
        System.out.println(part2.size());
        System.out.println(part3.size());

    }

    public ArrayList<Place> getPlaceLista() {
        return placeLista;
    }

    @Lock(LockType.READ)
    public ArrayList<Place> getList(int id){

        switch (id){
            case 1:{

                return part1;
            }
            case 2:{

                return part2;
            }
            case 3:{

                return part3;
            }
        }
        return null;
    }

    @Lock(LockType.READ)
    public ArrayList<Integer> count(){
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(part1.size());
        arrayList.add(part2.size());
        arrayList.add(part3.size());
        return arrayList;
    }


    @Lock(LockType.WRITE)
    public void add(Place m){
        switch (m.getStrefa()){
            case 1:{
                part1.add(m);
                break;
            }
            case 2:{
                part2.add(m);
                break;
            }
            case 3:{
                part3.add(m);
                break;
            }
        }
    }

    public Place find(int id, int strefa){
        for (Place m: placeLista) {
            if(m.getStrefa()==strefa && m.getId()==id) return m;
        }
        return null;
    }




}
