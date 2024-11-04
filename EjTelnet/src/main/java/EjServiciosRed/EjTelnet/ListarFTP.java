package EjServiciosRed.EjTelnet;

import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class ListarFTP {

   static public void main(String[] args) {

      // Datos para la conexión
      String server = "127.0.0.1";
      String username = "ahmed";
      String password = "ahmed";

      // Cliente de conexion a FTP
      FTPClient ftp = new FTPClient();

      int respuesta, i;
      String[] lista;

      try{
            System.out.println("CONECTANDO AL SERVIDOR FTP");
            // Conectando e identificándose con el servidor
            ftp.connect(server,2121);
            ftp.login(username, password);
            /*
            ftp.enterLocalPassiveMode(); 
            Entrar al modo pasivo es recomendable para conexiones en la forma cliente-servidor.
            Para nuestro ejemplo con Máquinas Virtuales no deja acceder. 
            */
            // Obteniendo respuesta del servidor
            respuesta = ftp.getReplyCode();
            System.out.println("RESPUESTA " + respuesta);

            // Si la respuesta del servidor indica podemos pasar procedemos
            if(FTPReply.isPositiveCompletion(respuesta) == true ) {
                  System.out.println("LISTANDO ARCHIVOS");
//                  lista = ftp.listNames();
//                  
//                  for(i=0; i<lista.length; i++) {
//
//                        System.out.println(lista[i]);
//                  }
                  System.out.println(ftp.getStatus());
            // Si no avisamos
            } else {
                        System.out.println("ERROR DE CONEXIÓN");
            }

            // en ambos casos terminaos sesion
            ftp.logout();
            // Y nos desconectamos
            ftp.disconnect();

            // Esta excepción se lanza en caso de algún error durante el proceso
            }catch(IOException e) {
                  System.out.println("Error de conexión");
            }
      }
}