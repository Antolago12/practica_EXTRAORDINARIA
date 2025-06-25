package es.ufv.dis.back.examenFinal.ALB.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import es.ufv.dis.back.examenFinal.ALB.model.Usuario;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

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

    // Lee usuarios desde src/main/resources/usuarios.json
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

    // Guarda usuarios en src/main/resources/usuarios.json (solo funciona en modo desarrollo)
    public void guardarUsuarios(List<Usuario> usuarios) {
        try (FileWriter writer = new FileWriter("src/main/resources/" + FILE_NAME)) {
            gson.toJson(usuarios, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Genera info.pdf con todos los usuarios
    public void generarPdfUsuarios() throws Exception {
        List<Usuario> usuarios = cargarUsuarios();

        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        PdfWriter.getInstance(document, new FileOutputStream("info.pdf"));
        document.open();

        document.add(new Paragraph("Listado de Usuarios\n\n"));

        // Tabla: Nombre, Apellidos, NIF, Email, Ciudad
        PdfPTable table = new PdfPTable(5);
        table.addCell("Nombre");
        table.addCell("Apellidos");
        table.addCell("NIF");
        table.addCell("Email");
        table.addCell("Ciudad");

        for (Usuario u : usuarios) {
            table.addCell(u.getNombre());
            table.addCell(u.getApellidos());
            table.addCell(u.getNif());
            table.addCell(u.getEmail());
            table.addCell(u.getDireccion().getCiudad());
        }

        document.add(table);
        document.close();
    }
}
