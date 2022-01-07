/**
 * 
 */
package com.pichincha.inventario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pichincha.inventario.entity.Producto;
import com.pichincha.inventario.repository.ProductoRepository;

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

}
