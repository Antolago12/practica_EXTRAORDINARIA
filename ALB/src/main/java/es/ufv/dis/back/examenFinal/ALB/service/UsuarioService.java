package es.ufv.dis.back.examenFinal.ALB.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import es.ufv.dis.back.examenFinal.ALB.model.Usuario;
import org.springframework.stereotype.Service;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private static final String FILE_NAME = "usuarios.json";
    private final Gson gson = new Gson();

    public List<Usuario> cargarUsuarios() {
        try (
                InputStream is = getClass().getClassLoader().getResourceAsStream(FILE_NAME);
                InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8)
        ) {
            Type listType = new TypeToken<ArrayList<Usuario>>() {}.getType();
            List<Usuario> usuarios = gson.fromJson(reader, listType);
            return usuarios != null ? usuarios : new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Si quieres modificar el archivo, deberías escribirlo fuera de resources.
    // La carpeta resources es de solo lectura una vez el proyecto está empaquetado.
    public void guardarUsuarios(List<Usuario> usuarios) {
        // Esta función solo funcionará si ejecutas en modo desarrollo y apuntas a la ruta real del archivo,
        // porque en un JAR las resources son solo lectura.
        try (FileWriter writer = new FileWriter("src/main/resources/" + FILE_NAME)) {
            gson.toJson(usuarios, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
