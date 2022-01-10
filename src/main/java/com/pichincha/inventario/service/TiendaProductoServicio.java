/**
 * 
 */
package com.pichincha.inventario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pichincha.inventario.entity.Producto;
import com.pichincha.inventario.entity.Tienda;
import com.pichincha.inventario.entity.TiendaProducto;
import com.pichincha.inventario.exception.InventarioException;
import com.pichincha.inventario.repository.TiendaProductoRepository;

/**
 * @author Christian Muyon
 *
 */
@Service
public class TiendaProductoServicio {

	@Autowired
	private TiendaProductoRepository tiendaProductoRepository;

	/**
	 * Guarda objeto TiendaProducto de acuerdo a busqueda por tienda y producto
	 * 
	 * @param tienda   Objeto Tienda
	 * @param producto Objeto Producto
	 * 
	 * @throws InventarioException
	 * 
	 * @return TiendaProducto
	 */
	public TiendaProducto obtenerPorTiendaYProducto(Tienda tienda, Producto producto) throws InventarioException {
		return tiendaProductoRepository.findByTiendaAndProducto(tienda, producto).stream().findFirst()
				.orElseThrow(() -> new InventarioException("La tienda con codigo: " + tienda.getCodigo()
						+ " no tiene asignado el producto con id: " + producto.getId()));
	}

}
