/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "Producto")

public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")

    private long idProducto;
    private String detalle;
    private int existencias;
    private double precio;
    private String descripcion;
    private String rutaImagen;
    private boolean activo;

    // private long idCategoria;
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    categoria categoria;

    public Producto() {
    }

    public Producto(String producto, boolean activo) {
        this.descripcion = producto;
        this.activo = activo;
    }
}
