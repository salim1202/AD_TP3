
package tp;

import javax.jws.WebService;

@WebService(endpointInterface = "tp.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

    public String sayHi(String text) {
        return "Hello " + text;
    }
}

