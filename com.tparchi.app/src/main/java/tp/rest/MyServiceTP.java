package tp.rest;
import javax.xml.ws.Endpoint;

import tp.DAO.DAO;
import tp.DAO.DAOFactory;
import tp.model.City;
import tp.model.CityManager;

import java.util.ArrayList;

public class MyServiceTP{
    public MyServiceTP() {
        CityManager cityManager = new CityManager();
        cityManager.addCity(new City("Rouen",0,0,"France"));

        DAO<City> cityDAO = DAOFactory.getCityDAO();

        ArrayList<City> cities = cityDAO.getAll();
        System.out.println("->"+cities);


        Endpoint.publish("http://127.0.0.1:8084/citymanager", cityManager);
    }

    public static void main(String args[]) throws InterruptedException {
        new MyServiceTP();
        Thread.sleep(15*60*1000);
        System.exit(0);
    }
}
