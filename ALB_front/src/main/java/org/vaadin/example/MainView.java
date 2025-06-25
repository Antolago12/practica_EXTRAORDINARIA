package org.vaadin.example;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.vaadin.example.model.Usuario;
import org.vaadin.example.service.UsuarioService;

import java.util.List;

@Route("")
public class MainView extends VerticalLayout {

    private Grid<Usuario> grid = new Grid<>(Usuario.class);

    public MainView() {
        setSizeFull();
        setPadding(true);
        setSpacing(true);

        grid.setColumns("nombre", "apellidos", "nif", "email"); // Añade aquí las columnas que quieras
        grid.getColumnByKey("nombre").setHeader("Nombre");
        grid.getColumnByKey("apellidos").setHeader("Apellidos");
        grid.getColumnByKey("nif").setHeader("NIF");
        grid.getColumnByKey("email").setHeader("Email");

        try {
            List<Usuario> usuarios = UsuarioService.getUsuarios();
            grid.setItems(usuarios);
        } catch (Exception e) {
            e.printStackTrace();
        }

        add(grid);

        // Botón Añadir usuario (a completar)
        Button btnNuevo = new Button("Añadir usuario");
        add(btnNuevo);

        // Botón Generar PDF (a completar)
        Button btnPdf = new Button("Generar PDF");
        add(btnPdf);
    }
}
