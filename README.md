Ejercicio práctico Java BackEnd Developer
================================

Proyecto spring boot, gestiona inventario de prodcutos. 

Para ejecutar el proyecto.
==========================

Clic derecho sobre InventarioApplication.java, ejecutar como java application.

![Inicio Apicacion](/capturas/aplicacion.png)


Servicios REST expuestos
==========================

1) Obtener codigo y nombre de todos los productos disponibles

URL: http://localhost:8080/inventario/api/producto/obtenerProductosCodigoNombre
<br />Metodo: GET

![Rest obtener productos](/capturas/obtenerProductos.png)

2) Actualizar stock de producto

URL: http://localhost:8080/inventario/api/producto/actualizarStock/idProducto/stock
<br />Metodo: PUT

![Rest actualizar stock producto](/capturas/actualizarStock.png)