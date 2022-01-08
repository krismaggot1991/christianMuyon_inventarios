Ejercicio práctico Java BackEnd Developer
================================

Proyecto spring boot, gestiona inventario de productos. 

Para ejecutar el proyecto.
==========================

Clic derecho sobre InventarioApplication.java, ejecutar como java application.

![Inicio Apicacion](/capturas/aplicacion.png)

Base de datos.
==========================

La aplicacion hace uso de una base de datos embebida **H2**
<br />
**URL consola DB:** http://localhost:8080/inventario/h2-console/
<br />
**Usuario:** sa
<br />
**Password:** 

![Consola base de datos](/capturas/consolaDB.png)

Al arrancar la aplicacion se carga automaticamente datos para la tabla **PRODUCTO**.

![Consola producto](/capturas/productoDB.png)

Al arrancar la aplicacion se carga automaticamente datos para la tabla **TIENDA**.

![Consola tienda](/capturas/tiendaDB.png)

Servicios REST expuestos
==========================

1) Obtener codigo y nombre de todos los productos disponibles

**URL:** http://localhost:8080/inventario/api/producto/obtenerProductosCodigoNombre
<br />**Metodo:** GET

![Rest obtener productos](/capturas/obtenerProductos.png)

2) Actualizar stock de producto

URL: http://localhost:8080/inventario/api/producto/actualizarStock/idProducto/stock
<br />**Metodo:** PUT

![Rest actualizar stock producto](/capturas/actualizarStock.png)