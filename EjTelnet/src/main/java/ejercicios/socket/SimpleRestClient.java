package ejercicios.socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SimpleRestClient {
	public static void main(String[] args) {
		try {
			// URL del servicio REST (reemplázala con la URL de tu servicio)
			String apiUrl = "https://api.weatherapi.com/v1/current.json?key=YOUR_API_KEY&q=Madrid";
			// Crear la conexión
			URL url = new URL(apiUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			// Configurar la solicitud
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
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