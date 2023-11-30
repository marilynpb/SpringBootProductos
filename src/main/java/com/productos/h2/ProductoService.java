package com.productos.h2;

import com.productos.h2.model.Producto;
import com.productos.h2.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.List;

@Component
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public Producto createproducto(Producto producto){
        return productoRepository.save(producto);
    }

    public Producto updateProducto(Long id, Producto producto) throws ProductoNotFoundException {
        Optional<Producto> optionalProducto = productoRepository.findById(id);

        if (optionalProducto.isPresent()) {
            Producto productoExistente = optionalProducto.get();
            productoExistente.setNombre(producto.getNombre());
            productoExistente.setDescripcion(producto.getDescripcion());
            productoExistente.setCantidad(producto.getCantidad());
            productoExistente.setPrecio(producto.getPrecio());

            return productoRepository.save(productoExistente);
        } else {
            throw new ProductoNotFoundException("No se encontró el producto con ID: " + id);
        }
    }



    public Producto getProductoById(Long id){
        Optional<Producto> optionalProducto = productoRepository.findById(id);
        return optionalProducto.get();
    }

    public List<Producto> getAllProductos(){
        return productoRepository.findAll();
    }

    public void deteleProducto(Long id){
        productoRepository.deleteById(id);
    }

    public List<Producto> getAllProductosOrderedByPrecio() {
        return productoRepository.findAllByOrderByPrecio();
    }
}
