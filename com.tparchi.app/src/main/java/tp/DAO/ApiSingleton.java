package tp.DAO;

/**
 * Created by lock- on 04/04/2016.
 */
public class ApiSingleton {
    private volatile static ApiSingleton single;
    private String name = "";

    private ApiSingleton(){
        this.name = "Mon singleton";
        System.out.println("\n\t\tCRÉATION DE L'INSTANCE ! ! !");
    }

    public static ApiSingleton getInstance(){
        if(single == null){
            synchronized(ApiSingleton.class){
                if(single == null)
                    single = new ApiSingleton();
            }
        }
        return single;
    }

    public String getName(){
        return this.name;
    }
}