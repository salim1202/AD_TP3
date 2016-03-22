package tp.model;

public class CityNotFound extends Exception {
    public CityNotFound() { super(); }
    public CityNotFound(String message) { super(message); }
    public CityNotFound(String message, Throwable cause) { super(message, cause); }
    public CityNotFound(Throwable cause) { super(cause); }
}
