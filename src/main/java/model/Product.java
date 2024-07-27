package model;

public class Product {
    private int id;
    private String nombre;
    private String marca;
    private String estado;
    private double precio;
    private String descripcion;
    private String imageUrl;
    private String palabraClave;
    private String categoria;
    private String uploadedBy;
    private String tipoAlquiler;
    private String pais;
    private String ciudad;

    public Product() {}

    public Product(int id, String nombre, String marca, String estado, double precio, String descripcion, String imageUrl, String palabraClave, String categoria, String uploadedBy, String tipoAlquiler, String pais, String ciudad) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.estado = estado;
        this.precio = precio;
        this.descripcion = descripcion;
        this.imageUrl = imageUrl;
        this.palabraClave = palabraClave;
        this.categoria = categoria;
        this.uploadedBy = uploadedBy;
        this.tipoAlquiler = tipoAlquiler;
        this.pais = pais;
        this.ciudad = ciudad;
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPalabraClave() {
        return palabraClave;
    }

    public void setPalabraClave(String palabraClave) {
        this.palabraClave = palabraClave;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public String getTipoAlquiler() {
        return tipoAlquiler;
    }

    public void setTipoAlquiler(String tipoAlquiler) {
        this.tipoAlquiler = tipoAlquiler;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}