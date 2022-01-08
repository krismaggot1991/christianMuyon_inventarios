/**
 * 
 */
package com.pichincha.inventario.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pichincha.inventario.entity.Producto;
import com.pichincha.inventario.repository.ProductoRepository;
import com.pichincha.inventario.to.ProductoCodigoNombreTo;

/**
 * @author Christian Muyon
 *
 */
@Service
public class ProductoServicio {

	@Autowired
	private ProductoRepository productoRepository;

	/**
	 * Guarda producto en tabla PRODUCTO
	 * 
	 * @param producto Entidad Producto
	 * 
	 * @return Producto
	 */
	public Producto guardarProducto(Producto producto) {
		return productoRepository.save(producto);
	}

	/**
	 * Obtiene todos los productos disponnibles unicamente con los atributos codigo
	 * y nombre.
	 * 
	 * @return List<ProductoCodigoNombreTo> Lista de objetos ProductoCodigoNombreTo
	 */
	public List<ProductoCodigoNombreTo> obtenerProductosCodigoNombre() {
		Iterable<Producto> iterableProducto = productoRepository.findAll();
		List<ProductoCodigoNombreTo> listaProductoCodigoNombreTo = new ArrayList<>();
		iterableProducto.forEach(itemProducto -> listaProductoCodigoNombreTo.add(itemProducto.obtenerCodigoNombre()));
		return listaProductoCodigoNombreTo;
	}

}
