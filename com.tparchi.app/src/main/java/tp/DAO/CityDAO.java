package tp.DAO;

import tp.model.City;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by lock- on 04/04/2016.
 */
public class CityDAO extends DAO<City> {

    public CityDAO(Connection conn){
        super(conn);
    }

    @Override
    public boolean create(City obj) {
        return false;
    }

    @Override
    public boolean delete(City obj) {
        return false;
    }

    @Override
    public boolean update(City obj) {
        return false;
    }

    @Override
    public City find(int id) {
        City city= new City();

        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM cities WHERE id= " + id);
            if(result.first())
                city = new City(
                        result.getString("name"),
                        result.getInt("latitude"),
                        result.getInt("longitude"),
                        result.getString("country")
                );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }

    public ArrayList<City> getAll(){
        ArrayList<City> cities = new ArrayList<City>();
        try {
            Statement statement = this.connect.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM cities");
            ResultSetMetaData results = resultSet.getMetaData();

            while(resultSet.next()){
                cities.add(new City(
                        resultSet.getString("name"),
                        resultSet.getInt("latitude"),
                        resultSet.getInt("longitude"),
                        resultSet.getString("country")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }
}
