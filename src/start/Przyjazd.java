package start;

//import strefy.BoxParking;
import strefy.BoxParking;
import strefy.Place;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.ArrayList;

/**
 * Created by Kamil on 21.05.2017.
 */

@WebService
public class Przyjazd {

    @EJB
    BoxParking box;

    @WebMethod(operationName = "getList")
    @WebResult(name = "list")
    public ArrayList<Place> getPlaces(@WebParam(name = "type") int type) {
        if(type==0) return box.getPlaceLista();
        else return box.getList(type);
    }

    @WebMethod(operationName = "getNumber")
    @WebResult(name = "number")
    public ArrayList<Integer> count (){
        return box.count();
    }

}
