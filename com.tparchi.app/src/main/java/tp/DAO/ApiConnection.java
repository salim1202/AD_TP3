package tp.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by lock- on 04/04/2016.
 */
public class ApiConnection {

    //URL de connexion
    private String url = "jdbc:mysql://localhost:3306/m1gil";
    //Nom du user
    private String user = "root";
    //Mot de passe de l'utilisateur
    private String pass = "";
    //Objet Connection
    private static Connection connect;

    /**
     * Retourne l'instance de connection
     *
     * @return
     */
    public static Connection getInstance() {
        if (connect == null) {
            new ApiConnection();
            System.out.println("Instanciation de la co");
        } else {
            System.out.println("Connexion inexistante");
        }
        return connect;
    }

    private ApiConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
