package ejercicios.FTP;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ejercicio3 {
    public static void main(String[] args) {
        String server = "localhost";
        int port = 21;

        String user = JOptionPane.showInputDialog("Ingrese el nombre de usuario:");
        String password = JOptionPane.showInputDialog("Ingrese la contraseña:");

        FTPClient ftpClient = new FTPClient();

        try {
            ftpClient.connect(server, port);
            boolean login = ftpClient.login(user, password);

            if (login) {
                // Configurar el modo pasivo y el tipo de archivo
                ftpClient.enterLocalPassiveMode();
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

                // Listar archivos disponibles
                String[] files = ftpClient.listNames();
                if (files != null) {
                    JFrame frame = new JFrame("Archivos disponibles");
                    JList<String> fileList = new JList<>(files);
                    JScrollPane scrollPane = new JScrollPane(fileList);
                    JButton downloadButton = new JButton("Descargar");
                    JButton exitButton = new JButton("Salir");

                    frame.add(scrollPane, "Center");
                    JPanel buttonPanel = new JPanel();
                    buttonPanel.add(downloadButton);
                    buttonPanel.add(exitButton);
                    frame.add(buttonPanel, "South");

                    frame.setSize(400, 300);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);

                    // Acción del botón Descargar
                    downloadButton.addActionListener(e -> {
                        String selectedFile = fileList.getSelectedValue();
                        if (selectedFile != null) {
                            JFileChooser fileChooser = new JFileChooser();
                            fileChooser.setSelectedFile(new File(selectedFile));
                            int result = fileChooser.showSaveDialog(frame);

                            if (result == JFileChooser.APPROVE_OPTION) {
                                File downloadFile = fileChooser.getSelectedFile();
                                try (FileOutputStream fos = new FileOutputStream(downloadFile)) {
                                    boolean success = ftpClient.retrieveFile(selectedFile, fos);
                                    if (success) {
                                        JOptionPane.showMessageDialog(frame, "Archivo descargado con éxito.");
                                    } else {
                                        JOptionPane.showMessageDialog(frame, "Error al descargar el archivo.");
                                    }
                                } catch (IOException ex) {
                                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
                                }
                            }
                        }
                    });

                    // Acción del botón Salir
                    exitButton.addActionListener(e -> {
                        try {
                            ftpClient.logout();
                            ftpClient.disconnect();
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(frame, "Error al cerrar la conexión: " + ex.getMessage());
                        }
                        System.exit(0);
                    });
                } else {
                    System.out.println("No se encontraron archivos en el servidor.");
                }
            } else {
                System.out.println("No se pudo iniciar sesión en el servidor FTP.");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                System.out.println("Error al cerrar la conexión FTP: " + ex.getMessage());
            }
        }
    }
}
