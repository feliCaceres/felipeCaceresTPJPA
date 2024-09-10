package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println("en marcha");

        try {
            entityManager.getTransaction().begin();

            Factura factura1 = new Factura();
            factura1.setFecha("10/10/2023");
            factura1.setNumero(23);

            Cliente cliente= new Cliente("Rosa","Gutierrez",2000011);
            Domicilio domicilio = new Domicilio("Peru",231);
            cliente.setDomicilio(domicilio);
            domicilio.setCliente(cliente);

            factura1.setCliente(cliente);

            Categoria perecederos = new Categoria("Perecederos");
            Categoria lacteos = new Categoria("Lacteos");
            Categoria limpieza = new Categoria("Limpieza");

            Articulo art1= new Articulo(200,"Yogures",32);
            Articulo art2 = new Articulo(32,"Detergente Magistral",35);

            art1.getCategorias().add(perecederos);
            art2.getCategorias().add(limpieza);
            art1.getCategorias().add(lacteos);

            lacteos.getArticulos().add(art1);
            limpieza.getArticulos().add(art2);
            perecederos.getArticulos().add(art1);

            DetalleFactura det1= new DetalleFactura();
            det1.setArticulo(art1);
            det1.setCantidad(2);
            det1.setSubtotal(35);

            art1.getDetallefactura().add(det1);
            factura1.getDetallefactura().add(det1);
            det1.setFactura(factura1);

            DetalleFactura det2= new DetalleFactura();

            det2.setArticulo(art2);
            det2.setCantidad(6);
            det2.setSubtotal(65);

            art2.getDetallefactura().add(det2);
            factura1.getDetallefactura().add(det2);
            det2.setFactura(factura1);

            factura1.setTotal(232);
            entityManager.persist(factura1);

            entityManager.persist(cliente);

            Domicilio dom = entityManager.find(Domicilio.class, 1L);
            Cliente cli = entityManager.find(Cliente.class, 1L);

            System.out.println("DNI CLIENTE" + dom.getCliente().getDni());
            System.out.println("CALLE CLIENTE" + cli.getDomicilio().getNombreCalle());


            entityManager.flush();
            entityManager.getTransaction().commit();

        }catch (Exception e) {
            entityManager.getTransaction().rollback();
        }

        // Cerrar el EntityManager y el EntityManagerFactory
        entityManager.close();
        entityManagerFactory.close();
    }
}
