import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.*;

public class MainJaasAutenticacion {
  public static void main(String[] args) {

    //datos proporcionados desde la linea de comandos
    String user = System.getProperty("usuario");
    String pass = System.getProperty("clave");

    //Paso al CallbackHandler el nombre de usuario y la clave para que 
    //el LoginModule acceda a ellos
    CallbackHandler handler = new MyCallbackHandler(user,pass);
    try {
  	  LoginContext loginContext = new 
                              LoginContext("EjemploLogin", handler);
        //llamada al m�todo login para realizar la autenticaci�n
	  loginContext.login();
 	  System.out.println("Usuario autenticado.....");
     } catch (LoginException e) {
        //si la autenticaci�n no tiene �xito
        System.err.println("ERROR=>No se puede autenticar el usuario");                                                             
     } 
  }
}//..MainJaasAutenticacion

