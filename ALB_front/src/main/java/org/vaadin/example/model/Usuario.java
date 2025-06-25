package org.vaadin.example.model;

public class Usuario {
    private String id;
    private String nombre;
    private String apellidos;
    private String nif;
    private Direccion direccion;
    private String email;
    private MetodoPago metodoPago;

    // Constructor vacío
    public Usuario() {}

    // Constructor completo
    public Usuario(String id, String nombre, String apellidos, String nif, Direccion direccion, String email, MetodoPago metodoPago) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nif = nif;
        this.direccion = direccion;
        this.email = email;
        this.metodoPago = metodoPago;
    }

    // Getters y setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getNif() { return nif; }
    public void setNif(String nif) { this.nif = nif; }

    public Direccion getDireccion() { return direccion; }
    public void setDireccion(Direccion direccion) { this.direccion = direccion; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public MetodoPago getMetodoPago() { return metodoPago; }
    public void setMetodoPago(MetodoPago metodoPago) { this.metodoPago = metodoPago; }
}
