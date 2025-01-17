import java.util.Map;
import javax.security.auth.*;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

public class EjemploLoginModule implements LoginModule{
	private Subject subject;
	private CallbackHandler callbackHandler;

	public boolean commit() throws LoginException {return true;}
 	public boolean logout() throws LoginException {return true;}
	public boolean abort() throws LoginException {return true;}
public void initialize(Subject subject, CallbackHandler handler,
                                Map state, Map options) {
		this.subject = subject;
		this.callbackHandler = handler;
	}  
      //m�todo login � se realiza la autenticaci�n
	public boolean login() throws LoginException {
	   boolean autenticado = true;
         if(callbackHandler == null){
	      throw new LoginException("Se necesita CallbackHandler");   
         }
         //Se crea el array de Callbacks
	   Callback[] callbacks = new Callback[2];
         //Constructor de NameCallback y PasswordCallback con prompt
	   callbacks[0] = new NameCallback("Nombre de usuario: ");
	   callbacks[1] = new PasswordCallback("Clave: ", false);

	   try {
            //se invoca al m�todo handle del CallbackHandler
            //para solicitar el usuario y la contrase�a
		callbackHandler.handle(callbacks);
		String usuario = ((NameCallback)callbacks[0]).getName();
		char [] passw = 
                      ((PasswordCallback)callbacks[1]).getPassword();
		String clave = new String(passw);

            //La autenticaci�n se realiza aqu�
		//el nombre de usuario: maria, su clave: 1234
		autenticado = ("pedro".equalsIgnoreCase(usuario) & 
                             "abcd".equals(clave)) ;
		} catch (Exception e) {e.printStackTrace();}
	   return autenticado;//devuelve true o false
	}//fin login	
}//..EjemploLoginModule

