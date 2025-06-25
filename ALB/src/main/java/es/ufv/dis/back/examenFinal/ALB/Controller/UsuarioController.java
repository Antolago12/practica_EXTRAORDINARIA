package es.ufv.dis.back.examenFinal.ALB.Controller;

import es.ufv.dis.back.examenFinal.ALB.model.Usuario;
import es.ufv.dis.back.examenFinal.ALB.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Obtener todos los usuarios
    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.loadAllUsuarios();
    }

    // Buscar usuario por nombre (case-insensitive)
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Usuario> getUsuarioByNombre(@PathVariable String nombre) {
        Usuario usuario = usuarioService.findUsuarioByName(nombre);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // (Opcional) Si quieres buscar por ID y tu POJO Usuario tiene getId()
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable String id) {
        List<Usuario> usuarios = usuarioService.loadAllUsuarios();
        return usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Otras rutas (añadir, modificar, PDF, etc) requieren que implementes guardarUsuarios, generarPdfUsuarios, etc
    // Si necesitas estos métodos, tendrás que implementar también persistencia en UsuarioService
}
