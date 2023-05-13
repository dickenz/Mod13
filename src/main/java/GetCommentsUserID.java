import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class GetCommentsUserID {

    public static void main(String[] args) throws IOException {
        int userId = 5;
        String postUrl = "https://jsonplaceholder.typicode.com/users/" + userId + "/posts";
        String commentsUrl;
        int maxPostId = -1;

        URL url = new URL(postUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        InputStream inputStream = conn.getInputStream();
        Scanner scanner = new Scanner(inputStream);
        String response = scanner.useDelimiter("\\A").next();
        scanner.close();
        inputStream.close();
        conn.disconnect();

        String[] posts = response.split("\\{");
        for (String post : posts) {
            if (post.contains("\"id\":")) {
                int id = Integer.parseInt(post.split("\"id\":")[1].split(",")[0].trim());
                if (id > maxPostId) {
                    maxPostId = id;
                }
            }
        }

        if (maxPostId != -1) {
            commentsUrl = "https://jsonplaceholder.typicode.com/posts/" + maxPostId + "/comments";
            url = new URL(commentsUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            inputStream = conn.getInputStream();
            scanner = new Scanner(inputStream);
            response = scanner.useDelimiter("\\A").next();
            scanner.close();
            inputStream.close();
            conn.disconnect();

            String fileName = "user-" + userId + "-post-" + maxPostId + "-comments.json";
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(response);
            fileWriter.close();
            System.out.println("Comments saved to file " + fileName);
        } else {
            System.out.println("User has no posts");
        }
    }

}
