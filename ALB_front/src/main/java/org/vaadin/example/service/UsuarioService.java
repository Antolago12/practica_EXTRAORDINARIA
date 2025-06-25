package org.vaadin.example.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.vaadin.example.model.Usuario;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class UsuarioService {
    private static final String API_URL = "http://localhost:8080/api/usuarios";
    private static final Gson gson = new Gson();

    public static List<Usuario> getUsuarios() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Type listType = new TypeToken<List<Usuario>>() {}.getType();
        return gson.fromJson(response.body(), listType);
    }
}
