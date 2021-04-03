package edu.unicauca.myapplication.Modelos;

public class Producto {

    private String id;
    private String nombre;
    private Number preciov;
    private Number precioc;
    private String descripcion;
    private String imagen;
    private String proveedor;
    private String categoria;
    private Number descuento;
    private Number cantidad;


    public  Producto(){ }

    public Number getPreciov() {
        return preciov;
    }

    public void setPreciov(Number preciov) {
        this.preciov = preciov;
    }

    public Number getPrecioc() {
        return precioc;
    }

    public void setPrecioc(Number precioc) {
        this.precioc = precioc;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Number getDescuento() {
        return descuento;
    }

    public void setDescuento(Number descuento) {
        this.descuento = descuento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCantidad(Number cantidad){ this.cantidad = cantidad; }

    public Number getCantidad(){ return cantidad; }

    public void setId(String id){ this.id = id; }

    public String getId(){ return id;}
}
