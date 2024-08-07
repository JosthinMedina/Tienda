package com.tienda.controller;

import com.tienda.domain.Producto;
import com.tienda.service.CategoriaService;
import com.tienda.service.ProductoService;
import com.tienda.service.impl.FirebaseStorageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Slf4j
@RequestMapping("/Producto")
public class ProductoController {
    
    @Autowired
    private ProductoService ProductoService;
    
    @Autowired
    private CategoriaService CategoriaService;
    

    @GetMapping("/listado")
    public String inicio(Model model) {
        var Producto = ProductoService.getProductos(false);
        model.addAttribute("Producto", Producto);
        model.addAttribute("totalProducto", Producto.size());
        return "/Producto/listado";
    }
    @GetMapping("/nuevo")
    public String ProductoNuevo(Producto Producto) {
        return "/Producto/modifica";
    }

    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;
    
    @PostMapping("/guardar")
    public String ProductoGuardar(Producto Producto,
            @RequestParam("imagenFile") MultipartFile imagenFile) {        
        if (!imagenFile.isEmpty()) {
            ProductoService.save(Producto);
            Producto.setRutaImagen(
                    firebaseStorageService.cargaImagen(
                            imagenFile, 
                            "Producto", 
                            Producto.getIdProducto()));
        }
        ProductoService.save(Producto);
        return "redirect:/Producto/listado";
    }

    @GetMapping("/eliminar/{idProducto}")
    public String ProductoEliminar(Producto Producto) {
        ProductoService.delete(Producto);
        return "redirect:/Producto/listado";
    }

    @GetMapping("/modificar/{idProducto}")
    public String ProductoModificar(Producto Producto, Model model) {
        Producto = ProductoService.getProducto(Producto);
        var categorias = CategoriaService.getcategorias(false);
        model.addAttribute("categorias", categorias);
        model.addAttribute("Producto", Producto);
        return "/Producto/modifica";
    }
}
