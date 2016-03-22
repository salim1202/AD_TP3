package tp.model;

import javax.jws.WebService;
import java.util.List;

/**
 * Created by lock- on 11/03/2016.
 */
@WebService
public interface CityManagerService {
    boolean addCity(City city);
    boolean removeCity(City city);
    void removeAll();
    void setCities(List<City> cities);
    List<City> searchFor(String cityName) throws CityNotFound;
    List<City> searchCitiesNear(Position position) throws  CityNotFound;
    City searchExactPosition(Position position) throws  CityNotFound;


    List<City> getCities();




}
