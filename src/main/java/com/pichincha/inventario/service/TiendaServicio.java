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
import com.pichincha.inventario.exception.InventarioException;
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

	/**
	 * Asigna y guarda en base de datos n productos a una determinada Tienda,
	 * devuelve como resultado detalle de productos asignados a tienda.
	 * 
	 * @param tiendaProductoTo Objeto TiendaProductoTo
	 * 
	 * @throws InventarioException
	 * 
	 * @return TiendaProductoTo
	 */
	public TiendaProductoDetalleTo asignarGuardarProductosATienda(TiendaProductoTo tiendaProductoTo)
			throws InventarioException {
		Optional<Tienda> tiendaOptional = tiendaRepository.findById(tiendaProductoTo.getCodigoTienda());
		if (tiendaOptional.isPresent()) {
			List<TiendaProducto> listaTiendaProducto = tiendaProductoRepository.findByTienda(tiendaOptional.get());
			listaTiendaProducto.forEach(itemTiendaProducto -> tiendaProductoRepository.delete(itemTiendaProducto));
			for (Long idProducto : tiendaProductoTo.getListaIdProductos()) {
				asignarGuardarProductoATienda(tiendaOptional.get(), idProducto);
			}
			return obtenerDetalleTiendaPorCodigo(tiendaOptional.get());
		} else {
			throw new InventarioException("No se encontro tienda con codigo: " + tiendaProductoTo.getCodigoTienda());
		}
	}

	/**
	 * Asigna y guarda en base de datos un producto a una determinada Tienda
	 * 
	 * @param tienda     Objeto Tienda
	 * @param idProducto Id de producto
	 * 
	 * @throws InventarioException
	 * 
	 * @return TiendaProducto
	 */
	public TiendaProducto asignarGuardarProductoATienda(Tienda tienda, Long idProducto) throws InventarioException {
		Producto producto = productoServicio.obtenerProductoPorId(idProducto);
		TiendaProducto tiendaProducto = new TiendaProducto();
		tiendaProducto.setProducto(producto);
		tiendaProducto.setTienda(tienda);
		return tiendaProductoRepository.save(tiendaProducto);
	}

	/**
	 * Obtiene tienda por codigo de tienda
	 * 
	 * @param codigoTienda Codigo de tienda
	 * 
	 * @throws InventarioException
	 * 
	 * @return Tienda
	 */
	public Tienda obtenerTiendaPorCodigo(Long codigoTienda) throws InventarioException {
		Optional<Tienda> optionalTienda = tiendaRepository.findById(codigoTienda);
		if (optionalTienda.isPresent()) {
			return optionalTienda.get();
		} else {
			throw new InventarioException("No existe tienda con codigo: " + codigoTienda);
		}
	}

	/**
	 * Obtiene detalle de productos asignados a una determinada tienda
	 * 
	 * @param tienda Objeto Tienda
	 * 
	 * @return TiendaProductoDetalleTo
	 */
	private TiendaProductoDetalleTo obtenerDetalleTiendaPorCodigo(Tienda tienda) {
		List<TiendaProducto> listaTiendaProducto = tiendaProductoRepository.findByTienda(tienda);
		List<ProductoIdNombreTo> productos = new ArrayList<>();
		listaTiendaProducto
				.forEach(itemTiendaProducto -> productos.add(itemTiendaProducto.getProducto().obtenerIdNombre()));
		return new TiendaProductoDetalleTo(tienda.obtenerNombreCodigoTienda(), productos);
	}

}
