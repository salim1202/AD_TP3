package tp.DAO;

import java.sql.Connection;

/**
 * Created by lock- on 04/04/2016.
 */
public class DAOFactory {
    protected static final Connection conn = ApiConnection.getInstance();

    /**
     * Retourne un objet Classe interagissant avec la BDD
     * @return DAO
     */
    public static DAO getCityDAO(){
        return new CityDAO(conn);
    }
}