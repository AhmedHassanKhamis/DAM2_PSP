package ejercicios.FTP;

import org.apache.commons.net.ftp.FTPClient;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ejercicio2 {
    public static void main(String[] args) {
        String server = "localhost";
        int port = 21;
        String user = "Usuario";
        String password = "clave";

        FTPClient ftpClient = new FTPClient();

        try {
            ftpClient.connect(server, port);
            boolean login = ftpClient.login(user, password);

            if (login) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File fileToUpload = fileChooser.getSelectedFile();
                    FileInputStream inputStream = new FileInputStream(fileToUpload);

                    boolean uploaded = ftpClient.storeFile(fileToUpload.getName(), inputStream);
                    inputStream.close();

                    if (uploaded) {
                        System.out.println("Archivo subido con éxito: " + fileToUpload.getName());
                    } else {
                        System.out.println("Error al subir el archivo.");
                    }

                    System.out.println("Contenido del directorio raíz:");
                    String[] files = ftpClient.listNames();
                    if (files != null) {
                        for (String file : files) {
                            System.out.println(" - " + file);
                        }
                    }
                }
            } else {
                System.out.println("No se pudo iniciar sesión en el servidor FTP.");
            }

            ftpClient.logout();
            ftpClient.disconnect();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
