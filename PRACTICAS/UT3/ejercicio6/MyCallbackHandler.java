import java.io.*;
import javax.security.auth.callback.*;

public class MyCallbackHandler implements CallbackHandler {
	private String usuario;
	private String clave;
	
    //metodo handle
	public void handle(Callback[] callbacks) throws IOException,
			UnsupportedCallbackException {
		for (int i = 0; i < callbacks.length; i++) {
			Callback callback = callbacks[i];
			if (callback instanceof NameCallback) {
				NameCallback nameCB = (NameCallback) callback;
				System.out.print(nameCB.getPrompt());  
				BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
				usuario = br.readLine();				
				nameCB.setName(usuario); 
				
			} else if (callback instanceof PasswordCallback) {
				PasswordCallback passwordCB = (PasswordCallback) callback;
				System.out.print(passwordCB.getPrompt());  
				BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
				clave = br.readLine();	
				passwordCB.setPassword(clave.toCharArray());
			}
		}  		  
	}//handle
}//..MyCallbackHandler 
