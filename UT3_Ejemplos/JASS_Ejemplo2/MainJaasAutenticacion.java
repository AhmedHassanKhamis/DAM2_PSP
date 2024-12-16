import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.*;

public class MainJaasAutenticacion {
  public static void main(String[] args) {       
     CallbackHandler handler = new MyCallbackHandler();
     try {
	  LoginContext loginContext = new 
                       LoginContext("EjemploLogin" , handler);
	  loginContext.login();
	  System.out.println("Usuario autenticado.....");
     } catch (LoginException e) {
      System.err.println("ERROR=> No se puede autenticar el usuario.");                                                             
     }
  }
}//..MainJaasAutenticacion
