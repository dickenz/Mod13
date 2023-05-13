import java.net.HttpURLConnection;
import java.net.URL;

public class DeleteUser {
    public static void main(String[] args) throws Exception {
        URL url = new URL("https://jsonplaceholder.typicode.com/users/1");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("DELETE");

        int responseCode = conn.getResponseCode();
        System.out.println("Response Code : " + responseCode);

        conn.disconnect();
    }
}
