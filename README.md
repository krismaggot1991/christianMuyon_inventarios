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

<br />
**Querys principales:** 
<br />
select * from tienda_producto;<br />
select * from producto;<br />
select * from tienda;<br />
select * from cliente;<br />
select * from pedido;<br />
select * from pedido_detalle;

Servicios REST expuestos (Productos)
==========================

**1) Obtener codigo y nombre de todos los productos disponibles**

**URL:** http://localhost:8080/inventario/api/producto/obtenerProductosCodigoNombre
<br />**Metodo:** GET

![Rest obtener productos](/capturas/obtenerProductos.png)

**2) Actualizar stock de producto**

**URL:** http://localhost:8080/inventario/api/producto/actualizarStock/idProducto/{stock}
<br />**Metodo:** PUT

![Rest actualizar stock producto](/capturas/actualizarStock.png)

Servicios REST expuestos (Tienda)
==========================

**1) Asignar productos a tienda**

**URL:** http://localhost:8080/inventario/api/tienda/asignarProductosATienda
<br />**Metodo:** POST
<br />**Body (example):**
<br />
```
{
    "codigoTienda": "1",
    "listaIdProductos": [
        "1",
        "3",
        "5"
    ]
}
````

![Rest asignar productos tienda](/capturas/asignarProductosTienda.png)

Servicios REST expuestos (Clientes)
==========================

**1) Guardar cliente**

**URL:** http://localhost:8080/inventario/api/cliente/guardarCliente
<br />**Metodo:** POST
<br />**Body (example):**
<br />
```
{
    "identificacion": "1803750312",
    "nombre": "Christian Muyon",
    "foto": "Foto"
}
````

![Rest guardar cliente](/capturas/guardarCliente.png)

**2) Obtener todos los clientes**

**URL:** http://localhost:8080/inventario/api/cliente/obtenerTodosClientes
<br />**Metodo:** GET

![Rest obtener clientes](/capturas/obtenerTodosClientes.png)


**3) Obtener cliente por identificacion**

**URL:** http://localhost:8080/inventario/api/cliente/obtenerPorIdentificacion/{identificacionCliente}
<br />**Metodo:** POST

![Rest obtener cliente identificacion](/capturas/obtenerClienteIdentificacion.png)

**4) Eliminar cliente por identificacion**

**URL:** http://localhost:8080/inventario/api/cliente/eliminarClientePorIdentificacion/{identificacionCliente}
<br />**Metodo:** DELETE

![Rest eliminar cliente identificacion](/capturas/eliminarCliente.png)

**5) Actualizar cliente**

**URL:** http://localhost:8080/inventario/api/cliente/actualizarCliente
<br />**Metodo:** PUT
<br />**Body (example):**
<br />
```
{
    "identificacion": "1803750312",
    "nombre": "Christian Alfredo Muyon Rivera",
    "foto": "Foto actualizada"
}
````

![Rest actuallizar cliente](/capturas/actualizarCliente.png)

Servicios REST expuestos (Pedidos)
==========================

**1) Realizar pedido**

**URL:** http://localhost:8080/inventario/api/pedido/realizarPedido
<br />**Metodo:** POST
<br />**Body (example):**
<br />
```
{
    "codigoCliente": "1",
    "detalle": [
        {
            "codigoTienda": "1",
            "idProducto": "2",
            "cantidad": "8"
        },
        {
            "codigoTienda": "4",
            "idProducto": "5",
            "cantidad": "2"
        }
    ]
}
````

![Rest realizar pedido](/capturas/realizarPedido.png)

![Rest pedido base](/capturas/pedidoBase.png)

Servicios REST expuestos (Reportes)
==========================

**1) Obtener reporte numero transacciones**

**URL:** http://localhost:8080/inventario/api/reporte/obtenerReporteNumeroTransacciones
<br />**Metodo:** GET

![Rest reporte transacciones](/capturas/reporteTransacciones.png)

**2) Obtener reporte de monto vendido por tienda**

**URL:** http://localhost:8080/inventario/api/reporte/obtenerReporteMontoVendidoTienda
<br />**Metodo:** GET

![Rest reporte vendido](/capturas/reporteVendido.png)

**3) Generar reporte CSV de transacciones de cliente (Para descarga de archivo realizar invocacion del servicio en un navegador web)**

**URL:** http://localhost:8080/inventario/api/reporte/obtenerReporteTransaccionesCliente/{codigo_cliente}/{fecha_inicio_anio_mes_dia}/{fecha_fin_anio_mes_dia}
<br />**Metodo:** GET

![Rest reporte csv](/capturas/descargaArchivo.png)

![Rest archivo csv](/capturas/csv.png)

<br />
<br />*Coleccion de servicios REST para postman se encuentran en la ruta: Christian Muyon Rivera. \recursos\Inventario_API.postman_collection.json

<br />
<br />**Aplicacion desarrollada por:** Christian Muyon Rivera. 