package com.sophophile;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.printf("Hello and welcome!");

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

            var client = HttpClient.newHttpClient();
            var response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("Reponse>>" + response.body());
        }
    }
}