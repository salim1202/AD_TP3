package tp.rest;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import tp.model.City;
import tp.model.CityManagerService;

public class MyClient {

	private static final QName SERVICE_NAME= new QName("http://model.tp/", "CityManagerService");
	private static final QName PORT_NAME= new QName("http://model.tp/", "CityManagerPort");

	public static void main(String args[]) throws MalformedURLException {
		URL wsdlURL = new URL("http://127.0.0.1:8084/citymanager?wsdl");
		Service service = Service.create(wsdlURL, SERVICE_NAME);
		CityManagerService cityManager = service.getPort(PORT_NAME, CityManagerService.class);

		System.out.println(cityManager.getCities());
		City c = new City("Zanarkand", 16,64, "Hyrule");
		cityManager.addCity(c);
		cityManager.removeCity(c);
		System.out.println(cityManager.getCities());
		cityManager.addCity(new City("Rouen", 49.443889, 1.103333,"FR"));
		cityManager.addCity(new City("Mogadiscio", 49.443889, 1.103333,"SO"));
		cityManager.addCity(new City("Rouen", 49.443889, 1.103333,"FR"));
		cityManager.addCity(new City("Bihorel", 49.455278, 1.116944,"FR"));
		cityManager.addCity(new City("Londres", 51.504872, -0.07857,"AN"));
		cityManager.addCity(new City("Paris", 48.856578, 2.351828,"FR"));
		cityManager.addCity(new City("Paris", 43.2, -80.38333,"FR"));

		cityManager.getCities();
		cityManager.addCity(new City("Villers-Bocage", 49.083333, -0.65,"FR"));
		cityManager.addCity(new City("Villers-Bocage", 50.021858, 2.326126,"FR"));

		cityManager.getCities();
		cityManager.removeCity(new City("Villers-Bocage", 50.021858, 2.326126,"FR"));

		try{
			System.out.println(cityManager.searchFor("Villers-Bocage"));
		}catch (Exception e){
			e.getMessage();
		}

		cityManager.getCities();



	}
}
