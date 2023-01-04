import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class Dalee2API {
    public static void main(String[] args) {
        try {
            // Set the API endpoint URL
            URL url = new URL("https://api.openai.com/v1/images/generations");

            // Set the API key for the request
            String apiKey = "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";

            // Set the prompt for the request
            String prompt = "Generate a picture of a cat playing with a ball";

            // Set the model to use for the request
            String model = "image-alpha-001";

            // Set the HTTP method and headers for the request
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Bearer " + apiKey);

            // Set the request body
            String body = "{\"model\": \"" + model + "\", \"prompt\": \"" + prompt + "\"}";

            // Send the request
            con.setDoOutput(true);
            con.getOutputStream().write(body.getBytes());

            // Get the response
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse the response into a JSON object
            JSONObject jsonResponse = new JSONObject(response.toString());

            // Get the URL of the generated image from the JSON object
            String imageUrl = jsonResponse.getJSONArray("data").getJSONObject(0).getString("url");

            // Print the URL to the console
            System.out.println("IL LINK DELL'IMMAGINE CREATA" + imageUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
