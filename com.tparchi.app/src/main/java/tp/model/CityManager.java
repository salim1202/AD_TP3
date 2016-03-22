package tp.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.jws.WebService;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class represent a city manager, it can
 * <ul>
 * <li>Ajouter une ville </li>
 * <li>Supprimer une ville</li>
 * <li>Retourne la liste de film</li>
 * <li>Recherche une ville avec un nom donne</li>
 * <li>Recherhce une ville à une position</li>
 * <li>Retourne la liste de villes à une position donnée a 10 km près</li>
 * </ul>
 */
@WebService(endpointInterface = "tp.model.CityManagerService", serviceName = "CityManagerService" )
public class CityManager implements CityManagerService {

    private List<City> cities;

    /**
     * Initialisation du city manager
     */
    public CityManager() {
        this.cities = new LinkedList<City>();
    }

    /***
     * Renvoie toutes les villes
     *
     * @return la liste de toutes les villes
     */
    public List<City> getCities() {
        return cities;
    }

    /**
     * remplace les villes actuelles par une liste de villes
     *
     * @param cities liste des villes
     */
    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    /**
     * Ajouter une ville dans le city manager
     *
     * @param city ville à ajouter
     * @return vrai si l'ajout a fonctionné
     * faux si l'ajout n'a pas fonctionné
     */
    public boolean addCity(City city) {
        System.out.println("----->"+city);

        boolean exist = false;
        for (City ville : cities) {
            if (ville.getPosition().equals(city.getPosition())) {
                exist = true;
            }
        }
        if (!exist)
            cities.add(city);

        return !exist;
    }

    /**
     * Supprime une ville si elle existe
     *
     * @param city ville a supprimer
     * @return vrai si la suppression a fonctionné
     * faux sinon
     * @throws CityNotFound si la ville n'est pas trouvée
     */
    public boolean removeCity(City city) {
        return  cities.remove(city);
    }

    /**
     * Supprime toutes les villes
     */
    public void removeAll() {
        cities.clear();
    }

    /**
     * Recherche une ou des villes selon le nom de la ville
     *
     * @param cityName nom de la ville recherchée
     * @return retourne une liste de ville
     * @throws CityNotFound si il n'y aucune ville
     */
    public List<City> searchFor(String cityName) throws CityNotFound {

        List<City> citiesFound = new LinkedList<City>();

        for (City c : cities)
            if (c.getName().equals(cityName))
                citiesFound.add(c);

        if (citiesFound.size() > 0) {
            return citiesFound;
        } else {
            throw new CityNotFound();
        }
    }

    /**
     * Recherche les villes à moins de 10 km de la position donnée
     *
     * @param position position de la ville
     * @return une liste de ville
     * @throws CityNotFound si il n'y aucune ville de trouvé
     */
    public List<City> searchCitiesNear(Position position) throws CityNotFound {

        List<City> citiesNear = new LinkedList<City>();

        for (City city : cities) {
            Position posCity = city.getPosition();
            double distance = distFrom(
                    posCity.getLatitude(),
                    posCity.getLongitude(),
                    position.getLatitude(),
                    position.getLongitude()
            );

            if (distance < 10000)
                citiesNear.add(city);
        }

        if (citiesNear.size() > 0)
            return citiesNear;

        throw new CityNotFound();
    }

    /**
     * Calcul la distance entre 2 positions
     *
     * @param lat1 latitude  de la première ville
     * @param lng1 longitude de la première ville
     * @param lat2 latitude  de la deuxième ville
     * @param lng2 longitude de la deuxième ville
     * @return la distance entre 2 positions
     */
    private double distFrom(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = (earthRadius * c);

        return dist;
    }

    /**
     * Recherche une ville a une position exacte
     * @param position de la ville recherchée
     * @return la ville a la position si elle existe
     * @throws CityNotFound si la ville n'est pas trouve
     */
    public City searchExactPosition(Position position) throws CityNotFound {

        for (City city : cities)
            if (position.equals(city.getPosition()))
                return city;

        throw new CityNotFound();
    }
}
