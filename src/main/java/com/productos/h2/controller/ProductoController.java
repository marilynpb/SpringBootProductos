package com.productos.h2.controller;

import com.productos.h2.ProductoNotFoundException;
import com.productos.h2.ProductoService;
import com.productos.h2.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;


@RestController
@RequestMapping("api/productos")

public class ProductoController {
    @Autowired

    private ProductoService productoService;

    @PostMapping
    public Producto createProducto(@RequestBody Producto producto){
       return productoService.createproducto(producto);
    }

    @PutMapping("{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        try {
            Producto productoActualizado = productoService.updateProducto(id, producto);
            return ResponseEntity.ok(productoActualizado);
        } catch (ProductoNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<Producto> getAllProductos(){
        return productoService.getAllProductos();
    }

    @GetMapping("{id}")
    public  Producto searchProductoById(@PathVariable("id") Long id){
        return productoService.getProductoById(id);
    }

    @DeleteMapping("{id}")
    public void  deteleProductoById(@PathVariable("id") Long id){
        productoService.deteleProducto(id);
    }

    @GetMapping("/ordenadosPorPrecio")
    public List<Producto> getAllProductosOrderedByPrecio() {
        return productoService.getAllProductosOrderedByPrecio();
    }
}
