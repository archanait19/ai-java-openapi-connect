package com.sophophile;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        var apiKey = System.getenv("OpenAIAPI_KEY");
        if (apiKey == null) {
            System.out.println("No OpenAI API Key set!");
        }
        else {
            System.out.println("OpenAI API Key: " + apiKey);
            var body = """
                    {
                        "model": "gpt-4o",
                        "messages": [
                            {
                            "role": "user",
                            "content": "Tell me an interesting fact about Java"
                            }
                        ]
                    }
                    """;

            var httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.openai.com/v1/chat/completions"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + apiKey)
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            HttpResponse<String> response;
            try (var client = HttpClient.newHttpClient()) {
                response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            }
            System.out.println("Reponse>>" + response.body());
        }
    }
}