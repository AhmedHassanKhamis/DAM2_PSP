package ejercicios.socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SimpleRestClient2 {
    public static void main(String[] args) {
        try {
            // URL del servicio REST público sin necesidad de una clave API
            String apiUrl = "https://jsonplaceholder.typicode.com/users"; // Endpoint para obtener usuarios

            // Crear la conexión
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configurar la solicitud
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            // Establecer tiempo de espera de conexión y lectura
            connection.setConnectTimeout(5000);  // 5 segundos
            connection.setReadTimeout(5000);     // 5 segundos

            // Verificar el código de respuesta
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // Código 200
                // Leer la respuesta
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Imprimir la respuesta
                System.out.println("Respuesta del servidor:");
                System.out.println(response.toString());
            } else {
                System.out.println("Error en la conexión: Código " + responseCode);
            }

            // Cerrar la conexión
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
