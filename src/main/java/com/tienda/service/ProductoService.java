
package com.tienda.service;

import com.tienda.domain.Producto;
import java.util.List;
import org.springframework.data.repository.query.Param;


public interface ProductoService {
    
    public List<Producto> getProductos(boolean activos);
    
    // Se obtiene un Producto, a partir del id de un Producto
    public Producto getProducto(Producto Producto);
    
    // Se inserta un nuevo Producto si el id del Producto esta vacío
    // Se actualiza un Producto si el id del Producto NO esta vacío
    public void save(Producto Producto);
    
    // Se elimina el Producto que tiene el id pasado por parámetro
    public void delete(Producto Producto);
    
    public List<Producto> findByPrecioBetweenOrderByDescripcion(double precioInf, double precioSup);    

    
    public List<Producto> metodoJPQL(@Param("precioInf")double precioInf,@Param("precioSup") double precioSup);

    public List<Producto> metodoNativo(@Param("precioInf")double precioInf,@Param("precioSup") double precioSup);

}

