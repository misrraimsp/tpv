import java.util.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Clase de los objetos RegProducto, que simulan el stock de una tienda.
 * 
 * @author (misrraim) 
 * @version (150514)
 */
public class RegProducto extends Registro
{
   // METODOS
    
        // METODO CONSTRUCTOR
    
    /**
     * Constructor de objetos de la clase RegProducto.
     */
    public RegProducto()
    {
        super();
    }
    
        // ALTA Y BAJA DE PRODUCTOS
    
    /**
     * Da de alta un nuevo producto.
     * 
     * @param   String descripcion
     * @param   float preciosiniva
     * @param   float iva
     * @param   int cantidad
     * @return  void
     * 
     */
    public void alta(String descripcion, float preciosiniva, float iva, int cantidad)
    {
        //variables locales
        Producto nuevoproducto;
        //cuerpo
        if (preciosiniva <= 0) {
                System.out.println("El precio debe ser positivo no nulo");
        }
        else {
            if (iva < 0 || cantidad < 0) {
                System.out.println("El iva y/o la cantidad deben ser positivas");
            }
            else {
                nuevoproducto = new Producto(descripcion, preciosiniva, iva, cantidad);
                putElemento(nuevoproducto);
            }
        }
    }
    
    /**
     * Da de baja un producto
     * 
     * @param   String idproducto
     * @return  void
     * 
     */
    public void baja(String idproducto)
    {
        //variables locales
        Producto eliminado = (Producto) removeElemento(idproducto);
        //cuerpo
        if (eliminado == null) {
                System.out.println("No existe el producto que se desea dar de baja");
        }
    }
    
    /**
     * Introduce en el sistema los productos válidos encontrados en el texto de entrada. El texto no reconocido se duelve.
     * 
     * @param   String texto
     * @return  String rechazo
     * 
     */
    public String altaAutomatica(String texto)
    {
        //variables locales
        String idproducto, descripcion, rechazo, scantidad, spreciosiniva, siva;
        char especial = 37; // caracter de tanto por ciento
        int cantidad;
        float preciosiniva, iva;
        Calendar fecha;
        Producto producto;
        Elemento elem = new Elemento(); // para usar extraerPatron()
        ArrayList<String> productos = splitElementos(texto, "===Elemento #[0-9]*===");
        ArrayList<String> rechazos = new ArrayList<String>();
        //cuerpo
        for (String sproducto : productos) {
            // separar los campos
            idproducto = elem.extraerPatron(sproducto, "ID Producto: ", ";", REGEX_ID);
            scantidad = elem.extraerPatron(sproducto, "Cantidad disponible: ", " unidades;", "[0-9]*");
            spreciosiniva = elem.extraerPatron(sproducto, "Precio sin IVA: ", " €", "[0-9]*.[0-9]*");
            siva = elem.extraerPatron(sproducto, "IVA aplicable: ", String.format(" %c", especial), "[0-9]*");
            descripcion = elem.extraerPatron(sproducto, "Descripción: ", ";", ".*");
            fecha = elem.extraerFecha(sproducto);
            if (idproducto == null || scantidad == null || spreciosiniva == null || siva == null || fecha == null) {
                rechazos.add(sproducto + "==Campo/s No Detectado/s==");
            }
            else {
                cantidad = Integer.parseInt(scantidad);
                preciosiniva = Float.parseFloat(spreciosiniva);
                iva = Float.parseFloat(siva);
                if (cantidad < 0 || preciosiniva <= 0 || iva < 0) { 
                    rechazos.add(sproducto + "==Cantidad/es No Correcta/s==");
                }
                else {
                    //introducir al sistema
                    if(containsKey(idproducto)) { // el producto ya estaba en el sistema: solo se cambia su cantidad
                        cambiarCantidad(idproducto, cantidad);
                    }
                    else { // se crea un nuevo producto
                        producto = new Producto(fecha, descripcion, preciosiniva, iva, cantidad);
                        producto.setId(idproducto);
                        putElemento(producto);
                    }
                }
            }
        }
        //comprobar el rechazo
        if (rechazos.isEmpty()) {
            return null;
        }
        //formatear el rechazo
        rechazo = "\r\n===TEXTO RECHAZADO===\r\n\r\n";
        for (String sproducto : rechazos) {
            rechazo += String.format("%s\r\n\r\n", sproducto);
        }
        return rechazo;
    }
   
        // MODIFICADORES
    
     /**
     * Modifica la cantidad de un producto. El resultado debe ser positivo
     * 
     * @param   String idproducto
     * @param   int incremento
     * @return  void
     * 
     */
    public void cambiarCantidad(String idproducto, int incremento)
    {
        // variables locales
        Producto producto = (Producto) removeElemento(idproducto); //saco el producto del registro
        int nuevacantidad;
        //comprobar que el producto existe
        if (producto == null) {
            System.out.println("El producto al que se hace referencia no existe");
            return;
        }
        //comprobar que el resultado es positivo
        nuevacantidad = producto.getCantidad() + incremento;
        if (nuevacantidad < 0) {
            putElemento(producto); //devuelvo el producto sin modificar al registro
            System.out.println("El incremento pretendido no es valido: Produce cantidad negativa");
            return;
        }
        //actualizar la cantidad
        producto.setCantidad(nuevacantidad);
        putElemento(producto); //devuelvo el producto al registro con la cantidad modificada
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
        //variables locales
        Producto producto = (Producto) removeElemento(idproducto);
        //comprobar que el producto existe
        if (producto == null) {
            System.out.println("El producto al que se hace referencia no existe");
            return;
        }
        //comprobar que el nuevo IVA es positivo
        if (nuevoiva < 0) {
            putElemento(producto); //devuelvo el producto sin modificar al registro
            System.out.println("El IVA no puede ser negativo");
            return;
        }
        //actualizar el IVA
        producto.setIva(nuevoiva);
        putElemento(producto); //devuelvo el producto al registro con el IVA modificado
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
        //variables locales
        Producto producto = (Producto) removeElemento(idproducto);
        //comprobar que el producto existe
        if (producto == null) {
            System.out.println("El producto al que se hace referencia no existe");
            return;
        }
        //comprobar que el nuevo precio sin iva es positivo
        if (nuevoprecio < 0) {
            putElemento(producto); //devuelvo el producto sin modificar al registro
            System.out.println("El precio no puede ser negativo");
            return;
        }
        //actualizar el precio sin iva
        producto.setPreciosiniva(nuevoprecio);
        putElemento(producto); //devuelvo el producto al registro con el precio modificado
    }
    
    /**
     * Modifica la descripción de un producto.
     * 
     * @param   String idproducto
     * @param   String nuevadescripcion
     * @return  void
     * 
     */
    public void cambiarDescripcion(String idproducto, String nuevadescripcion)
    {
        //variables locales
        Producto producto = (Producto) removeElemento(idproducto);
        //comprobar que el producto existe
        if (producto == null) {
            System.out.println("El producto al que se hace referencia no existe");
            return;
        }
        //actualizar la descripcion
        producto.setDescripcion(nuevadescripcion);
        putElemento(producto); //devuelvo el producto al registro con la descripcion modificada
    }
    
        // ACCESO
        
    /**
     * Devuelve el objeto Producto buscado en la cantidad pedida. Devuelve null si el producto no existe o la cantidad no es válida
     * 
     * @param   String idproducto
     * @param   int cantidad
     * @return  Producto producto
     * 
    */
     
    public Producto solicitarProducto(String idproducto, int cantidad)
    {
        // variables locales
        Producto producto = (Producto) getElemento(idproducto);
        Producto productoventa;
        int disponible;
        //comprobar que el producto existe
        if (producto == null) {
            System.out.println("El producto al que se hace referencia no existe");
            return null;
        }
        //comprobar la cantidad
        disponible = producto.getCantidad();
        if ((disponible < cantidad)||(cantidad < 0)) {
            System.out.println("Error: La cantidad solicitada no es valida. " + "Cantidad disponible = " + disponible);
            return null;
        }
        //sintesis del producto pedido
        productoventa = new Producto(producto.getFecha(), producto.getDescripcion(), producto.getPreciosiniva(), producto.getIva(), cantidad);
        productoventa.setId(idproducto);
        //actualizar inventario
        cambiarCantidad(idproducto, -cantidad);
        //devolver producto pedido
        return productoventa;
    }
    
    /**
     * Devuelve el objeto Producto buscado (por la descripción) en la cantidad pedida. Devuelve null si el producto no existe o la cantidad no es válida
     * 
     * @param   int cantidad
     * @param   String descripcion
     * @return  Producto producto
     * 
    */
     
    public Producto solicitarProducto(int cantidad, String descripcion)
    {
        return solicitarProducto(descrip2id(descripcion), cantidad);
    }
    
    /**
     * Introduce el producto devuelto en el registro
     * 
     * @param   Producto producto
     * @return  void
     * 
    */
     
    public void devolverProducto(Producto producto)
    {
        //operacion valida?
        if (producto == null) {
            return;
        }
        cambiarCantidad(producto.getId(), producto.getCantidad());
    }
    
    /**
     * Devuelve el idproducto del primer producto encontrado que cumpla la descripción
     * 
     * @param   String descripcion
     * @return  String idproducto
     * 
     */
    
    private String descrip2id(String descripcion)
    {
        //variables locales
        String idproducto;
        Producto producto;
        Set llaves = getLlaves();
        Iterator iterator = llaves.iterator();
        //cuerpo
        while (iterator.hasNext()) {
            idproducto = (String) iterator.next();
            producto = (Producto) getElemento(idproducto);
            if (producto.getDescripcion().matches(".*" + descripcion + ".*")){
                return idproducto;
            }
        }
        return null;
    }

}