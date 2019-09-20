import java.util.*;

/**
 * Clase principal de la aplicación. Genera objetos Terminal Punto de Venta (TPV). Realiza el interface con el usuario
 * 
 * @author (misrraim) 
 * @version (150514)
 */
public class TPV
{
    
    // VARIABLES DE INSTANCIA
    
    private RegProducto almacen;
    private RegVenta regventa;
    private RegCliente regcliente;
    private RegFactura regfactura;
    private Venta venta;
    private Factura factura;
    private IO io;

    // METODOS
    
        // MAIN: (CLASE PRINCIPAL DEL SISTEMA)
        
    public static void main (String [ ] args) {}
    
        // METODO CONSTRUCTOR
    
    /**
     * Constructor de objetos de la clase TPV
     */
    public TPV()
    {
        almacen = new RegProducto();
        regventa = new RegVenta();
        regcliente = new RegCliente();
        regfactura = new RegFactura();
        io = new IO();
        venta = null;
        factura = null;
    }
    
        // ALTA Y BAJA DE PRODUCTOS
    
    /**
     * Da de alta un nuevo producto
     * 
     * @param   String descripcion
     * @param   float preciosiniva
     * @param   float iva
     * @param   int cantidad
     * @return  void
     * 
     */
    public void altaProducto(String descripcion, float preciosiniva, float iva, int cantidad)
    {
        almacen.alta(descripcion, preciosiniva, iva, cantidad);
    }
    
    /**
     * Da de baja un producto
     * 
     * @param  String idproducto
     * @return void
     * 
     */
    public void bajaProducto(String idproducto)
    {
        almacen.baja(idproducto);
    }
    
        // MODIFICAR PROPIEDADES DE UN PRODUCTO
    
     /**
     * Modifica la cantidad de un producto
     * 
     * @param   String idproducto
     * @param   int incremento
     * @return  void
     * 
     */
    public void cambiarCantidad(String idproducto, int incremento)
    {
        almacen.cambiarCantidad(idproducto, incremento);
    }
    
    /**
     * Modifica el IVA de un producto.
     * 
     * @param   String idproducto
     * @param   float nuevoiva
     * @return  void
     * 
     */
    public void cambiarIva(String idproducto, float nuevoiva)
    {
        almacen.cambiarIva(idproducto, nuevoiva);
    }
    
    /**
     * Modifica el precio sin IVA de un producto.
     * 
     * @param   String idproducto
     * @param   float nuevoprecio
     * @return  void
     * 
     */
    public void cambiarPreciosiniva(String idproducto, float nuevoprecio)
    {
        almacen.cambiarPreciosiniva(idproducto, nuevoprecio);
    }
    
    /**
     * Metodo cambiarDescripcion(): Modifica la descripcion de un producto.
     * 
     * @param   String idproducto
     * @param   String nuevadescripcion
     * @return  void
     * 
     */
    public void cambiarDescripcion(String idproducto, String nuevadescripcion)
    {
        almacen.cambiarDescripcion(idproducto, nuevadescripcion);
    }
    
        // IMPORTAR/EXPORTAR
    
    /**
     * Escribe en fichero todos los productos del almacén
     * 
     * @param   String nombrearchivo
     * @return  void
     * 
     */
    public void exportarProductos(String nombrearchivo)
    {
        io.escribir(nombrearchivo, almacen.toString());
    }
    
    /**
     * Da de alta en el sistema los productos leidos de un archivo. Toda la información que no se corresponda con un producto correctamente definido
     * se escribe en otro fichero, con nombre "Rechazo[nombrearchivo]"
     * 
     * @param  String nombrearchivo
     * @return void
     * 
     */
    public void importarProductos(String nombrearchivo)
    {
        io.escribir("Rechazo" + nombrearchivo, almacen.altaAutomatica(io.leer(nombrearchivo)));
    }    
    
    /**
     * Escribe en un fichero los datos de los clientes
     * 
     * @param  String nombrearchivo
     * @return void
     * 
     */
    public void exportarClientes(String nombrearchivo)
    {
        io.escribir(nombrearchivo, regcliente.toString());
    }
    
    /**
     * Da de alta en el sistema los clientes leidos de un archivo. Toda la información que no se corresponda con un cliente correctamente definido
     * se escribe en otro archivo, con nombre "Rechazo[nombrearchivo]"
     * 
     * @param  String nombrearchivo
     * @return void
     * 
     */
    public void importarClientes(String nombrearchivo)
    {
        io.escribir("Rechazo" + nombrearchivo, regcliente.altaAutomatica(io.leer(nombrearchivo)));
    }
    
    /**
     * Escribe en un fichero los datos de las ventas
     * 
     * @param  String nombrearchivo
     * @return void
     * 
     */
    public void exportarVentas(String nombrearchivo)
    {
        io.escribir(nombrearchivo, regventa.toString());
    }
    
    /**
     * Da de alta en el sistema las ventas leidas de un archivo. Toda la información que no se corresponda con una venta correctamente definida
     * se escribe en otro archivo, con nombre "Rechazo[nombrearchivo]"
     * 
     * @param  String nombrearchivo
     * @return void
     * 
     */
    public void importarVentas(String nombrearchivo)
    {
        io.escribir("Rechazo" + nombrearchivo, regventa.altaAutomatica(io.leer(nombrearchivo), regcliente, almacen));
    }
    
    /**
     * Escribe en un fichero los datos de las facturas
     * 
     * @param  String nombrearchivo
     * @return void
     * 
     */
    public void exportarFacturas(String nombrearchivo)
    {
        io.escribir(nombrearchivo, regfactura.toString());
    }
    
    /**
     * Da de alta en el sistema las facturas leidas de un archivo. Toda la información que no se corresponda con una factura correctamente definida
     * se escribe en otro archivo, con nombre "Rechazo[nombrearchivo]"
     * 
     * @param  String nombrearchivo
     * @return void
     * 
     */
    public void importarFacturas(String nombrearchivo)
    {
        io.escribir("Rechazo" + nombrearchivo, regfactura.altaAutomatica(io.leer(nombrearchivo), regcliente, regventa));
    }    
    
        // ALTA Y BAJA DE CLIENTES
    
    /**
     * Da de alta un nuevo cliente
     * 
     * @param   String cif 
     * @param   String nombre
     * @param   String apellidos
     * @param   String razonsocial
     * @param   String domicilio
     * @return  void
     * 
     */
    public void altaCliente(String cif, String nombre, String apellidos, String razonsocial, String domicilio)
    {
        regcliente.alta(cif, nombre, apellidos, razonsocial, domicilio);
    }
    
    /**
     * Da de baja un cliente
     * 
     * @param  String idcliente
     * @return void
     * 
     */
    public void bajaCliente(String idcliente)
    {
        regcliente.baja(idcliente);
    }
    
        // MODIFICAR PROPIEDADES DE UN CLIENTE
    
    /**
     * Modifica el CIF de un cliente.
     * 
     * @param   String idcliente
     * @param   String cif
     * @return  void
     * 
     */
    public void cambiarCif(String idcliente, String cif)
    {
        regcliente.cambiarCif(idcliente, cif);
    }
    
     /**
     * Modifica el nombre de un cliente.
     * 
     * @param   String idcliente
     * @param   String nombre
     * @return  void
     * 
     */
    public void cambiarNombre(String idcliente, String nombre)
    {
        regcliente.cambiarNombre(idcliente, nombre);
    }
    
     /**
     * Modifica los apellidos de un cliente.
     * 
     * @param   String idcliente
     * @param   String apellidos
     * @return  void
     * 
     */
    public void cambiarApellidos(String idcliente, String apellidos)
    {
        regcliente.cambiarApellidos(idcliente, apellidos);
    }
    
    /**
     * Modifica la razón social de un cliente.
     * 
     * @param   String idcliente
     * @param   String razonsocial
     * @return  void
     * 
     */
    public void cambiarRazonsocial(String idcliente, String razonsocial)
    {
        regcliente.cambiarRazonsocial(idcliente, razonsocial);
    }
    
    /**
     * Modifica el domicilio de un cliente.
     * 
     * @param   String idcliente
     * @param   String domicilio
     * @return  void
     * 
     */
    public void cambiarDomicilio(String idcliente, String domicilio)
    {
        regcliente.cambiarDomicilio(idcliente, domicilio);
    }
    
        // GESTION DE VENTA
    
    /**
     * Abre una nueva venta
     * 
     * @param  String idcliente //Código del cliente
     * @return void
     * 
     */
    public void nuevaVenta(String idcliente)
    {
        //variables locales
        Cliente cliente = (Cliente) regcliente.getElemento(idcliente);
        //cuerpo
        if (venta != null) {
            System.out.println("Ya existe una venta en curso");
            return;
        }
        
        if (cliente == null) {
            System.out.println("No existe el cliente especificado");
            return;
        }
        venta = new Venta(cliente);
    }
    
    /**
     * Adiciona un nuevo producto a la venta
     * 
     * @param   String idproducto
     * @param   int cantidad
     * @return  void
     * 
     */
    public void aumentarVenta(String idproducto, int cantidad)
    {
        //valido?
        if (venta == null) {
            System.out.println("No hay ninguna venta abierta");
            return;
        }
        venta.incluir(almacen.solicitarProducto(idproducto, cantidad));
    }
    
    /**
     * Adiciona un nuevo producto a la venta, buscándolo por descripción
     * 
     * @param   int cantidad
     * @param   String descripcion
     * @return  void
     * 
     */
    public void aumentarVenta(int cantidad, String descripcion)
    {
        //valido?
        if (venta == null) {
            System.out.println("No hay ninguna venta abierta");
            return;
        }
        venta.incluir(almacen.solicitarProducto(cantidad, descripcion));
    }
    
    /**
     * Elimina el producto de la venta
     * 
     * @param  String idproducto
     * @return void
     * 
     */
    public void disminuirVenta(String idproducto)
    {
        //valido?
        if (venta == null) {
            System.out.println("No hay ninguna venta abierta");
            return;
        }
        almacen.devolverProducto(venta.excluir(idproducto));
    }
    
    /**
     * Cierra el proceso de venta. Devuelve todos los productos al almacén
     * 
     * @param   void
     * @return  void
     * 
     */
    public void cancelarVenta()
    {
        //valido?
        if (venta == null) {
            System.out.println("No hay ninguna venta abierta");
            return;
        }
        //variables locales
        HashMap<String, Producto> productos = venta.getProductos();
        String id;
        Set llaves = productos.keySet();
        Iterator iterator = llaves.iterator();
        //cuerpo
        while(iterator.hasNext()) {
            id = (String) iterator.next();
            almacen.devolverProducto(productos.get(id));
        }
        venta = null;
    }
    
    /**
     * Cierra el proceso de venta. Imprime el ticket de la venta. Envia la venta al registro de ventas
     * 
     * @param   void
     * @return  void
     * 
     */
    public void concluirVenta()
    {
        //valido?
        if (venta == null) {
            System.out.println("No hay ninguna venta abierta");
            return;
        }
        //establecer el precio de la venta
        venta.updatePrecio();
        //almacenar venta
        regventa.registrarVenta(venta);
        //generar ticket
        System.out.println(venta.printTicket());
        //cerrar venta
        venta = null;
    }
    
    /**
     * Imprime el ticket de una venta
     * 
     * @param  String idventa //Código de la venta
     * @return  void
     * 
     */
    public void imprimirVenta(String idventa)
    {
        //variables locales
        Venta venta = (Venta) regventa.getElemento(idventa);
        //comprobar que existe
        if (venta == null) {
            System.out.println("No existe la venta especificada");
            return;
        }
        System.out.println(venta.printTicket());
    }
    
    
        // GESTION DE FACTURA
    
    /**
     * Abre una nueva factura
     * 
     * @param   String idcliente //Cliente de la factura
     * @param   String pfiscal //Perido fiscal de la factura
     * @return  void
     * 
     */
    public void nuevaFactura(String idcliente, String pfiscal)
    {
        //valido?
        if (factura != null) {
            System.out.println("Ya existe una factura en curso");
            return;
        }
        //variables locales
        Cliente cliente = (Cliente) regcliente.getElemento(idcliente);
        //comprobaciones
        if (cliente == null) { //cliente correcto?
            System.out.println("No existe el cliente especificado");
            return;
        }
        if (!pfiscal.matches("[12][09][019][0-9]")) { //primera aproximación a una buena máscara
            System.out.println("El periodo fiscal no es válido");
            return;
        }
        //nueva factura correcta
        factura = new Factura(cliente, pfiscal);
    }
    
    /**
     * Adiciona una venta a la factura
     * 
     * @param  String idventa //Código de la venta a adicionar
     * @return void
     * 
     */
    public void aumentarFactura(String idventa)
    {
        //valido?
        if (factura == null) {
            System.out.println("No hay ninguna factura abierta");
            return;
        }
        //variables locales
        String fpfiscal = factura.getPfiscal();
        String vpfiscal;
        Venta venta = (Venta) regventa.getElemento(idventa);
        //comprobaciones
        if (venta == null) { //la venta existe
            System.out.println("No existe la venta especificada");
            return;
        }
        vpfiscal = String.format("%s", venta.getFecha().get(Calendar.YEAR));
        if(!vpfiscal.equals(fpfiscal)){ //mismo periodo fiscal
            System.out.println("La venta especificada no pertenece al periodo fiscal de la factura");
            return;
        }
        if(!venta.getCliente().getId().equals(factura.getCliente().getId())){ //mismo cliente
            System.out.println("El cliente de la factura no coincide con el de la venta");
            return;
        }
        //incluir venta correcta
        factura.incluir(venta);
    }
    
    /**
     * Elimina una venta de la factura
     * 
     * @param  String idventa //Código de la venta a eliminar
     * @return void
     * 
     */
    public void disminuirFactura(String idventa)
    {
        //valido?
        if (factura == null) {
            System.out.println("No hay ninguna factura abierta");
            return;
        }
        factura.excluir(idventa);
    }
    
    /**
     * Cancela la factura en curso
     * 
     * @param   void
     * @return  void
     * 
     */
    public void cancelarFactura()
    {
        //valido?
        if (factura == null) {
            System.out.println("No hay ninguna factura abierta");
            return;
        }
        factura = null;
    }
    
    /**
     * Cierra el proceso de facturar. Imprime la factura. Envia la factura al registro de facturas
     * 
     * @param   void
     * @return  void
     * 
     */
    public void concluirFactura()
    {
        //valido?
        if (factura == null) {
            System.out.println("No hay ninguna factura abierta");
            return;
        }
        //almacenar factura
        regfactura.registrarFactura(factura);
        //generar ticket
        System.out.println(factura.printFactura());
        //cerrar venta
        factura = null;
    }
    
    /**
     * Imprime una factura
     * 
     * @param   String idfactura //Código de la factura a imprimir
     * @return  void
     * 
     */
    public void imprimirFactura(String idfactura)
    {
        //variables locales
        Factura factura = (Factura) regfactura.getElemento(idfactura);
        //comprobar que existe
        if (factura == null) {
            System.out.println("No existe la factura especificada");
            return;
        }
        System.out.println(factura.printFactura());
    }  

}