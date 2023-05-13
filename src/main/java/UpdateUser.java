import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class UpdateUser {

    public static void main(String[] args) throws Exception {
        String jsonInputString = "{\"name\": \"Updated User\", \"username\": \"updateduser\", \"email\": \"updateduser@example.com\"}";

        URL url = new URL("https://jsonplaceholder.typicode.com/users/1");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        OutputStream os = conn.getOutputStream();
        os.write(jsonInputString.getBytes());
        os.flush();

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String output;
        System.out.println("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }
        conn.disconnect();
    }
}