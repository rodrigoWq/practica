import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
 
/**
 *
 * @author codigo200
 */
public class RestApi {
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
 
            URL url = new URL("http://api.fixer.io/latest?base=USD");
            //Opcional
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 2531));
 
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection(proxy);
            conexion.setRequestMethod("GET");
 
            conexion.setRequestProperty("Accept", "application/json");
 
            if (conexion.getResponseCode() != 200) {
                throw new RuntimeException("Intento Fallido : codigo de error HTTP: " + conexion.getResponseCode());
            }
 
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conexion.getInputStream())));
 
            String output;
 
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
 
            conexion.disconnect();
 
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }
 
}