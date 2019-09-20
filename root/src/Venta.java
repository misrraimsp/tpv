import java.util.*;

/**
 * Simula los elementos de una venta en particular
 * 
 * @author (misrraim) 
 * @version (150514)
 */
public class Venta extends Elemento
{
    // VARIABLES DE INSTANCIA
    
    private HashMap<String, Producto> items;
    private Cliente cliente;
    private float preciototal;
    
    // METODOS
    
        // METODO CONSTRUCTOR

    /**
     * Constructor de objetos Venta. Crea una Venta con la fecha (y hora) actual
     * 
     * @param   Cliente cliente
     * @return  void
     * 
     */
    public Venta(Cliente cliente)
    {
        super(); // se establece el id de la venta y su fecha
        items = new HashMap<String, Producto>();
        this.cliente = cliente;
        preciototal = 0;
    }
    
    /**
     * Constructor de objetos Venta. Crea una Venta con la fecha (y hora) suministrada en parámetro
     * 
     * @param   Cliente cliente
     * @param   Calendar fecha
     * @return  void
     * 
     */
    public Venta(Calendar fecha, Cliente cliente)
    {
        super(fecha); // se establece el id de la venta y su fecha
        this.cliente = cliente;
        items = new HashMap<String, Producto>();
        preciototal = 0;
    }
    
        // AUMENTAR-DISMINUIR VENTA
    
    /**
     * Introduce, si no estaba ya, un producto a la venta. Si estaba aumenta su cantidad
     * 
     * @param   Producto nuevoproducto
     * @return  void
     * 
     */
    public void incluir(Producto nuevoproducto)
    {
        //operacion valida?
        if (nuevoproducto == null) {
            return;
        }
        //variables locales
        String id = nuevoproducto.getId();
        Producto producto;
        //cuerpo
        if (items.containsKey(id)) { // el producto ya existe en la venta
            producto = items.remove(id);// extraigo el producto
            producto.setCantidad(producto.getCantidad() + nuevoproducto.getCantidad()); //modifico su cantidad
            items.put(id, producto);// vuelvo a introducirlo, ya modificado
        }
        else { // el producto es nuevo en la venta
             items.put(id, nuevoproducto);
        }
    }
    
    /**
     * Elimina el producto de la venta y lo devuelve como parametro
     * 
     * @param   String id
     * @return  Producto producto
     * 
     */
    public Producto excluir(String id)
    {
        //variables locales
        Producto producto = items.remove(id);
        //comprobar que el producto existe
        if(producto == null) {
            System.out.println("El producto al que se hace referencia no existe");
            return null;
        }
        //devolver producto al almacen
        return producto;
    }
    
    /**
     * Establece el precio total de la venta
     * 
     * @param   void
     * @return  void
     * 
     */
    public void updatePrecio()
    {
        //variables locales
        Producto producto;
        String idproducto;
        Set llaves = items.keySet();
        Iterator iterator = llaves.iterator();
        //cuerpo
        while(iterator.hasNext()) {
            idproducto = (String) iterator.next();
            producto = items.get(idproducto);
            preciototal += producto.getAcumulado();
        }
    }    
    
        // METODOS DE ACCESO
    
    /**
     * Devuelve la coleccion completa de productos de la venta
     * 
     * @param   void
     * @return  HashMap<String, Producto> productos
     * 
     */
    public HashMap<String, Producto> getProductos()
    {
        return items;
    }
    
    /**
     * Devuelve el cliente de la venta
     * 
     * @param   void
     * @return  Cliente cliente
     * 
     */
    public Cliente getCliente()
    {
        return cliente;
    }
    
    /**
     * Devuelve el precio total de la venta
     * 
     * @param   void
     * @return  float precio
     * 
     */
    public float getPreciototal()
    {
        return preciototal;
    }      
    
    /**
     * Devuelve un String con toda la información de la venta en formato de ticket
     * 
     * @param   void
     * @return  String ticket
     * 
    */
    public String printTicket()
    {
        //variables locales
        String resultado, idproducto;
        Producto producto;
        char porcentaje = 37; //codigo ascii del símbolo de porcentaje
        Set llaves = items.keySet();
        Iterator it = llaves.iterator();
        //cuerpo
        resultado = "\r\n======================================================================\r\n\r\n";
        resultado += String.format("ID Venta: %s\r\n", getId());
        resultado += String.format("ID Cliente: %s\r\n\r\n", cliente.getId());
        resultado += String.format("ID Producto         Descripción         Unidades  Precio/u  IVA %c     Precio\r\n\r\n", porcentaje);
        while (it.hasNext()){
            idproducto = (String) it.next();
            producto = items.get(idproducto);
            resultado += String.format("%-20s", producto.getId());
            resultado += String.format("%-20s", producto.getFormatDescripcion());
            resultado += String.format("%-10d", producto.getCantidad());
            resultado += String.format("%-10.2f", producto.getPrecio());
            resultado += String.format("%-10.0f", producto.getIva(), porcentaje);
            resultado += String.format("%-5.2f\r\n", producto.getAcumulado());
        }
        resultado += String.format("\r\n                                                  TOTAL: %.2f €\r\n", preciototal);
        return resultado;
    }
    
    /**
     * Redefinición de toString(): Devuelve un String con la información que define a la venta
     * 
     * @param   void
     * @return  String venta
     * 
    */
    public String toString()
    {
        //variables locales
        String resultado = "";
        String idproducto;
        Set llaves = items.keySet();
        Iterator it = llaves.iterator();
        //cuerpo
        resultado += String.format("ID Venta: %s;\r\n", getId());
        resultado += fecha2String();
        resultado += String.format("ID Cliente: %s;\r\n", cliente.getId());
        resultado += "\r\n";
        while (it.hasNext()) {
            idproducto = (String) it.next();
            resultado += "==\r\n";
            resultado += String.format("ID Producto: %s;\r\n", idproducto);
            resultado += String.format("Cantidad: %d unidades;\r\n", items.get(idproducto).getCantidad());
        }
        resultado += "\r\n";
        return resultado;
    }
    
}
