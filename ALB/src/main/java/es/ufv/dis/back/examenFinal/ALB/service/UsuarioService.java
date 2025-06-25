package es.ufv.dis.back.examenFinal.ALB.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import es.ufv.dis.back.examenFinal.ALB.model.Usuario;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    // Lee todos los usuarios desde usuarios.json en resources
    public List<Usuario> loadAllUsuarios() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("usuarios.json")) {
            if (is == null) {
                throw new FileNotFoundException("No se encontró usuarios.json en el classpath");
            }
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Usuario>>() {}.getType();
            List<Usuario> usuarios = gson.fromJson(new InputStreamReader(is), listType);
            return usuarios != null ? usuarios : new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Busca un usuario por su nombre (case-insensitive)
    public Usuario findUsuarioByName(String name) {
        List<Usuario> usuarios = loadAllUsuarios();
        Optional<Usuario> found = usuarios.stream()
                .filter(u -> u.getNombre().equalsIgnoreCase(name))
                .findFirst();
        return found.orElse(null);
    }

    // Método para registrar la petición (pendiente de implementar si quieres)
    public void trackPetition(String userName) {
        // Implementa aquí la lógica para guardar el nombre del usuario y el contador en el JSON de peticiones
    }
}
