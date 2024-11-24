package ejercicios.FTP;

import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;

public class ejercicio1 {
    public static void main(String[] args) {
        String[] servers = {
                "ftp.rediris.es",
                "ftp.uv.es",
                "ftp.freebsd.org",
                "ftp.dit.upm.es",
                "ftp.unavarra.es"
        };

        for (String server : servers) {
            System.out.println("Conectando a: " + server);
            try {
                FTPClient ftpClient = new FTPClient();
                ftpClient.connect(server);
                ftpClient.login("anonymous", "");
                String[] rootDirs = ftpClient.listNames();

                if (rootDirs != null) {
                    System.out.println("Directorios en la raíz de " + server + ":");
                    for (String dir : rootDirs) {
                        System.out.println(" - " + dir);
                        ftpClient.changeWorkingDirectory(dir);
                        String[] subFiles = ftpClient.listNames();
                        if (subFiles != null) {
                            for (String file : subFiles) {
                                System.out.println("   * " + file);
                            }
                        }
                        ftpClient.changeToParentDirectory();
                    }
                } else {
                    System.out.println("No se encontraron directorios en la raíz de " + server);
                }
                ftpClient.logout();
                ftpClient.disconnect();
            } catch (IOException e) {
                System.out.println("Error conectando a " + server + ": " + e.getMessage());
            }
        }
    }
}
