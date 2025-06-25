package es.ufv.dis.back.examenFinal.ALB.Controller;

import es.ufv.dis.back.examenFinal.ALB.model.Usuario;
import es.ufv.dis.back.examenFinal.ALB.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//--------------PDF------
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
//--------------PDF------

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.cargarUsuarios();
    }

    @GetMapping("/{id}")
    public Usuario getUsuarioById(@PathVariable String id) {
        return usuarioService.cargarUsuarios().stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public void addUsuario(@RequestBody Usuario usuario) {
        List<Usuario> usuarios = usuarioService.cargarUsuarios();
        usuarios.add(usuario);
        usuarioService.guardarUsuarios(usuarios);
    }

    @PutMapping("/{id}")
    public void updateUsuario(@PathVariable String id, @RequestBody Usuario usuario) {
        List<Usuario> usuarios = usuarioService.cargarUsuarios();
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId().equals(id)) {
                usuarios.set(i, usuario);
                break;
            }
        }
        usuarioService.guardarUsuarios(usuarios);
    }
    @GetMapping("/pdf")
    public ResponseEntity<String> generarPdf() {
        try {
            usuarioService.generarPdfUsuarios();
            return ResponseEntity.ok("PDF generado correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error generando PDF");
        }
    }
}
