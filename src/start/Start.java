package start;

import test.SimulatorService;
import strefy.BoxParking;
import strefy.Place;
//import simulation.SimulatorService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;


/**
 * Created by Kamil on 26.05.2017.
 */
@WebServlet(urlPatterns = "/start")
public class Start extends HttpServlet {

    @EJB
    BoxParking box;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int miejsce = Integer.parseInt(request.getParameter("miejsce"));
        int strefa = Integer.parseInt(request.getParameter("strefa"));
        String action = request.getParameter("action");
        if(action.equals("przyjazd")){
            Place miejsce1 = box.find(miejsce,strefa);
            miejsce1.setWolne("false");
            LocalDateTime now = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(now);
            miejsce1.setTime(timestamp);

            Load.base.add(miejsce1);
            Message message = new Message("Samochod przyjechal "+miejsce1.toString(),strefa);
            Sender sender = new Sender();
            sender.send(message);
        } else {
            Place miejsce1 = box.find(miejsce,strefa);
            miejsce1.setWolne("true");
            Load.base.remove(miejsce1);
            Message message = new Message("Samochod odjechal "+miejsce1.toString(),strefa);
            SimulatorService simulatorService = new SimulatorService();
            simulatorService.getSimulatorPort().decrement(miejsce1.getStrefa());

            Sender sender = new Sender();
            sender.send(message);
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
