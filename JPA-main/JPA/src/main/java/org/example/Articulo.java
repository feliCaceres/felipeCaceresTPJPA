package org.example;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Articulo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int cantidad;
    private String denominacion;
    private int precio;

    @OneToMany(mappedBy = "articulo")
    private List<DetalleFactura> detallefactura = new ArrayList<DetalleFactura>();

    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinTable(
            name = "articulo_categoria",
            joinColumns = @JoinColumn(name = "articulo_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private List<Categoria> categorias = new ArrayList<Categoria>();

    public List<DetalleFactura> getDetallefactura() {
        return detallefactura;
    }

    public void setDetallefactura(List<DetalleFactura> detallefactura) {
        this.detallefactura = detallefactura;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Articulo() {
    }

    public Articulo(int cantidad, String denominacion, int precio) {
        this.cantidad = cantidad;
        this.denominacion = denominacion;
        this.precio = precio;
    }
}
