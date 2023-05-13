import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Users {
    public static void main(String[] args) throws Exception {
        URL url = new URL("https://jsonplaceholder.typicode.com/users");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int responseCode = conn.getResponseCode();

        if (responseCode == 200) {
            Scanner scanner = new Scanner(url.openStream());
            String jsonResult = scanner.useDelimiter("\\Z").next();
            System.out.println(jsonResult);
            scanner.close();
        }
    }
}