/**
 * 
 */
package com.pichincha.inventario.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pichincha.inventario.entity.Producto;
import com.pichincha.inventario.exception.ServiceException;
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

	/**
	 * Obtiene producto por id de producto
	 * 
	 * @return Optional<Producto>
	 */
	public Optional<Producto> obtenerProductoPorId(Long idProducto) {
		return productoRepository.findById(idProducto);
	}

	/**
	 * Actualiza el stock de determinado producto. El stock a actualizar debe ser
	 * mayor a 0
	 * 
	 * @param idProducto Id de producto
	 * @param stock      Numero de stock a actualizar
	 * 
	 * @throws ServiceException
	 * 
	 * @return Producto
	 */
	public Producto actualizarStockProducto(Long idProducto, int stock) throws ServiceException {
		Optional<Producto> productoOptional = this.obtenerProductoPorId(idProducto);
		if (productoOptional.isPresent()) {
			if (stock >= 1) {
				productoOptional.get().setStock(stock);
				return this.guardarProducto(productoOptional.get());
			} else {
				throw new ServiceException("El stock no puede ser menor o igual a cero.");
			}
		} else {
			throw new ServiceException("No se encontro producto con id: " + idProducto);
		}
	}

}
