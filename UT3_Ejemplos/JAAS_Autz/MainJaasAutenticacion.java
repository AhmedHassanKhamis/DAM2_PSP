import java.security.PrivilegedAction;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.*;

public class MainJaasAutenticacion {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		CallbackHandler handler = new MyCallbackHandler();
		LoginContext loginContext = null;
		try {
		   loginContext = new LoginContext
                                ("EjemploLogin", handler);
		   loginContext.login();
		   System.out.println("Usuario autenticado.....");
		} catch (LoginException e) {
    		   System.err.println("ERROR=> No se puede autenticar el usuario.");
		   System.exit(-1);
		}
            //Una vez autenticado se obtiene el Subject
		Subject subject = loginContext.getSubject();

            //Se crea un objeto PrivilegedAction 
		PrivilegedAction action = new EjemploAccion();
		try {
		    //El sujeto realiza la acción definida en la clase 
                //EjemploAccion como usuario autenticado bajo las 
                //restricciones de seguridad definidas,
                //se usa el método doAsPrivileged()
		    Subject.doAsPrivileged(subject, action, null);
		} catch (SecurityException se) {
		     System.out.println("ACCESO DENEGADO =>  "+ 
                                se.getMessage());	
		}
		try {
                //Desconectamos al usuario
		    loginContext.logout();
		} catch (LoginException le) {
		    System.out.println("Logout: " + le.getMessage());
		    System.exit(-1);
		}
	}
}// ..MainJaasAutenticacion

