import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class OpenTasks {

    public static void main(String[] args) throws IOException {
        int userId = 8;
        String tasksUrl = "https://jsonplaceholder.typicode.com/users/" + userId + "/todos";

        URL url = new URL(tasksUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        InputStream inputStream = conn.getInputStream();
        Scanner scanner = new Scanner(inputStream);
        String response = scanner.useDelimiter("\\A").next();
        scanner.close();
        inputStream.close();
        conn.disconnect();

        JsonArray tasksArray = new Gson().fromJson(response, JsonArray.class);
        for (int i = 0; i < tasksArray.size(); i++) {
            JsonObject taskObject = tasksArray.get(i).getAsJsonObject();
            boolean completed = taskObject.get("completed").getAsBoolean();
            if (!completed) {
                int taskId = taskObject.get("id").getAsInt();
                String title = taskObject.get("title").getAsString();
                System.out.println("task id: " + taskId);
                System.out.println("title: " + title);
                System.out.println();
            }

        }
    }

    }





