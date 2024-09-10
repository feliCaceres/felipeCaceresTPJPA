package org.example;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Setter

public class Factura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fecha;
    private int numero;
    private int total;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="fk_cliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleFactura> detallefactura = new ArrayList<DetalleFactura>();


    public List<DetalleFactura> getDetallefactura() {
        return detallefactura;
    }

    public void setDetallefactura(List<DetalleFactura> detallefactura) {
        this.detallefactura = detallefactura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Factura() {
    }

    public Factura(String fecha, int numero, int total) {
        this.fecha = fecha;
        this.numero = numero;
        this.total = total;
    }

    public Factura(String fecha, int numero, int total, Cliente cliente) {
        this.fecha = fecha;
        this.numero = numero;
        this.total = total;
        this.cliente = cliente;
    }

}
