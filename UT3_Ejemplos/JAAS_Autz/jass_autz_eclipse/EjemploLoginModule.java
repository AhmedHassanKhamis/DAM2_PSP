package jass_autz_eclipse;
import java.util.Map;
import javax.security.auth.*;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

public class EjemploLoginModule implements LoginModule{
	private Subject subject;
	private CallbackHandler callbackHandler;
	private String usuario ;
	private String clave;
      //Se define el principal para es usuario 
	private EjemploPrincipal usuarioPrincipal ;

	public boolean abort() throws LoginException {return true;}

	//Se llama a este método si la autenticación global de 
      //LoginContext ha sido satisfactoria
	public boolean commit() throws LoginException {
	  //Se crea el principal asociado al usuario autenticado
	  usuarioPrincipal= new EjemploPrincipal(usuario);	  
	  //Se añade el principal (identidad autenticada) al sujeto
	  if (!subject.getPrincipals().contains(usuarioPrincipal))
		 subject.getPrincipals().add(usuarioPrincipal);
	   return true;
	}
      //
	public void initialize(Subject subject, CallbackHandler handler, 
                        Map state, Map options) {
	  this.subject = subject;
	  this.callbackHandler = handler;
	}
      //metodo login
	public boolean login() throws LoginException {
	  boolean autenticado = true;
	  if(callbackHandler == null){
		throw new LoginException("Se necesita CallbackHandler");   
	  }

	  Callback[] callbacks = new Callback[2];
 	  callbacks[0] = new NameCallback("Nombre de usuario: ");
	  callbacks[1] = new PasswordCallback("Clave: ", false);

	  try {
	    //se invoca al CallbackHandler
	    callbackHandler.handle(callbacks);		
	    usuario = ((NameCallback)callbacks[0]).getName();
	    char [] passw=((PasswordCallback)callbacks[1]).getPassword();
	    clave = new String(passw);

	    //La autenticación se realiza aquí
	    //maria - 1234 * juan - 5678
	    boolean autenticado1 = ("maria".equalsIgnoreCase(usuario)& 
                                   "1234".equals(clave));
          boolean autenticado2= ("juan".equalsIgnoreCase(usuario) & 
                                   "5678".equals(clave)); 
          autenticado = autenticado1||autenticado2;	

	  } catch (Exception e) { e.printStackTrace();}

        return autenticado;
	}//login

	//Finalizar la sesión del usuario.
      //Este método elimina el principal que se añadió en commit 
      public boolean logout() throws LoginException {
	  subject.getPrincipals().remove(usuarioPrincipal);
	  usuarioPrincipal=null;
	  usuario=null ;
	  clave=null;
	  return true;
	}

}//..EjemploLoginModule

