import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class UserName {
    public static void main(String[] args) throws Exception {
            String username = "Bret";
            URL url = new URL("https://jsonplaceholder.typicode.com/users?username=" + username);
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
