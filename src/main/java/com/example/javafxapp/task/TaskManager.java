package com.example.javafxapp.task;

import com.example.javafxapp.domain.Author;
import javafx.concurrent.Task;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TaskManager extends Task<List<Author>> {

    @Override
    protected List<Author> call() throws Exception {
        List<Author> authors = new ArrayList<>();
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/authors"))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JSONArray jsonArray = new JSONArray(response.body());

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    String birthdateString = jsonObject.getString("birthdate");
                    OffsetDateTime birthdate = OffsetDateTime.parse(birthdateString);
                    LocalDate birthdateLocalDate = birthdate.toLocalDate();

                    Author author = new Author(
                    jsonObject.getInt("id"),
                    jsonObject.getString("name"),
                    jsonObject.getString("surname"),
                    birthdateLocalDate,
                    jsonObject.getBoolean("active"));

                    authors.add(author);

                    System.out.println("Respuesta JSON: " + response.body());
                }


            } else {
                updateMessage("Error " + response.statusCode() + " " + response.body());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return authors;
    }
}