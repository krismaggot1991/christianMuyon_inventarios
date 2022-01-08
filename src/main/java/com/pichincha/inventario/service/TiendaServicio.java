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
import com.pichincha.inventario.entity.Tienda;
import com.pichincha.inventario.entity.TiendaProducto;
import com.pichincha.inventario.exception.ServiceException;
import com.pichincha.inventario.repository.TiendaProductoRepository;
import com.pichincha.inventario.repository.TiendaRepository;
import com.pichincha.inventario.to.ProductoIdNombreTo;
import com.pichincha.inventario.to.TiendaProductoDetalleTo;
import com.pichincha.inventario.to.TiendaProductoTo;

/**
 * @author Christian Muyon
 *
 */
@Service
public class TiendaServicio {

	@Autowired
	private ProductoServicio productoServicio;

	@Autowired
	private TiendaProductoRepository tiendaProductoRepository;

	@Autowired
	private TiendaRepository tiendaRepository;

	public TiendaProductoDetalleTo asignarGuardarProductosATienda(TiendaProductoTo tiendaProductoTo)
			throws ServiceException {
		Optional<Tienda> tiendaOptional = tiendaRepository.findById(tiendaProductoTo.getCodigoTienda());
		if (tiendaOptional.isPresent()) {
			List<TiendaProducto> listaTiendaProducto = tiendaProductoRepository.findByTienda(tiendaOptional.get());
			listaTiendaProducto.forEach(itemTiendaProducto -> tiendaProductoRepository.delete(itemTiendaProducto));
			for (Long idProducto : tiendaProductoTo.getListaIdProductos()) {
				asignarGuardarProductoATienda(tiendaOptional.get(), idProducto);
			}
			return obtenerDetalleTiendaPorCodigo(tiendaOptional.get().getCodigo());
		} else {
			throw new ServiceException("No se encontro tienda con codigo: " + tiendaProductoTo.getCodigoTienda());
		}
	}

	/**
	 * Asigna y guarda en base de datos producto a determinada Tienda
	 * 
	 * @param tienda     Objeto Tienda
	 * @param idProducto Id de producto
	 * 
	 * @throws ServiceException
	 * 
	 * @return TiendaProducto
	 */
	public TiendaProducto asignarGuardarProductoATienda(Tienda tienda, Long idProducto) throws ServiceException {
		Optional<Producto> producto = productoServicio.obtenerProductoPorId(idProducto);
		if (producto.isPresent()) {
			TiendaProducto tiendaProducto = new TiendaProducto();
			tiendaProducto.setProducto(producto.get());
			tiendaProducto.setTienda(tienda);
			return tiendaProductoRepository.save(tiendaProducto);
		} else {
			throw new ServiceException("No se encontro producto con id: " + idProducto);
		}
	}

	private TiendaProductoDetalleTo obtenerDetalleTiendaPorCodigo(Long codigoTienda) throws ServiceException {
		Optional<Tienda> tiendaOptional = tiendaRepository.findById(codigoTienda);
		if (tiendaOptional.isPresent()) {
			List<TiendaProducto> listaTiendaProducto = tiendaProductoRepository.findByTienda(tiendaOptional.get());
			List<ProductoIdNombreTo> productos = new ArrayList<>();
			listaTiendaProducto
					.forEach(itemTiendaProducto -> productos.add(itemTiendaProducto.getProducto().obtenerIdNombre()));
			return new TiendaProductoDetalleTo(tiendaOptional.get().obtenerTiendaTo(), productos);
		} else {
			throw new ServiceException("No se encontro tienda con codigo: " + codigoTienda);
		}
	}

}
