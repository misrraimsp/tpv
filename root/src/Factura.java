import java.util.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Representa las facturas del sistema.
 * 
 * @author (misrraim) 
 * @version (150514)
 */
public class Factura extends Elemento
{
    // CONSTANTES DE CLASE
    private static final String CIF_VENDEDOR = "CIF VENDEDOR";
    private static final String RAZON_SOCIAL_VENDEDOR = "RAZON SOCIAL VENDEDOR";
    
    // VARIABLES DE INSTANCIA
    private Cliente cliente;
    private String pfiscal;
    private HashMap<String, Venta> items;

    //METODOS
        // CONSTRUCTORES
    /**
     * Constructor de objetos de la clase Factura. Se establece la fecha actual como fecha de la factura
     * 
     * @param   Cliente cliente
     * @param   String pfiscal
     * @return  void
     * 
     */
    public Factura(Cliente cliente, String pfiscal)
    {
        super();
        this.cliente = cliente;
        this.pfiscal = pfiscal;
        items = new HashMap<String, Venta>();
    }
    
    /**
     * Constructor de objetos de la clase Factura. Se establece la fecha pasada como parámetro como fecha de la factura
     * 
     * @param   Calendar fecha
     * @param   Cliente cliente
     * @param   String pfiscal
     * @return  void
     * 
     */
    public Factura(Calendar fecha, Cliente cliente, String pfiscal)
    {
        super(fecha);
        this.cliente = cliente;
        this.pfiscal = pfiscal;
        items = new HashMap<String, Venta>();
    }    
    
        // AUMENTAR-DISMINUIR FACTURA
    
    /**
     * Introduce una venta a la factura
     * 
     * @param   Venta venta
     * @return  void
     * 
     */
    public void incluir(Venta venta)
    {
        items.put(venta.getId(), venta);
    }
    
    /**
     * Elimina la venta de la factura
     * 
     * @param   String idventa
     * @return  void
     * 
     */
    public void excluir(String idventa)
    {
        items.remove(idventa);
    }

    // ACCESO
    
    /**
     * Devuelve el periodo fiscal de la factura
     * 
     * @param   void
     * @return  String pfiscal
     * 
     */
    public String getPfiscal()
    {
        return pfiscal;
    } 
    
    /**
     * Devuelve el cliente de la factura
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
     * Redefinición de toString(): Devuelve un String con la información que define a la factura
     * 
     * @param   void
     * @return  String factura
     * 
     */
    public String toString()
    {
        //variables locales
        String resultado = "";
        String idventa;
        Set llaves = items.keySet();
        Iterator it = llaves.iterator();
        //cuerpo
        resultado += String.format("ID Factura: %s;\r\n", getId());
        resultado += fecha2String();
        resultado += String.format("ID Cliente: %s;\r\n", cliente.getId());
        resultado += String.format("Periodo Fiscal: %s;\r\n", pfiscal);
        resultado += "\r\n";
        while (it.hasNext()) {
            idventa = (String) it.next();
            resultado += "==\r\n";
            resultado += String.format("ID Venta: %s;\r\n", idventa);
        }
        resultado += "\r\n";
        return resultado;        
    }
    
    /**
     * Devuelve un String con formato de factura
     * 
     * @param   void
     * @return  String factura
     * 
    */
    public String printFactura()
    {
        //variables locales
        String resultado, idventa, idproducto;
        float acumulado = 0;
        Venta venta;
        Producto producto;
        HashMap<String, Producto> pitems = new HashMap<String, Producto>();
        Set pllaves = pitems.keySet();
        Iterator pit = pllaves.iterator();
        Set vllaves = items.keySet();
        Iterator vit = vllaves.iterator();
        //cuerpo
        resultado = "\r\n======================================================================\r\n\r\n";
        resultado += String.format("ID Factura: %s\r\n", getId());
        resultado += String.format("CIF Vendedor: %s\r\n", CIF_VENDEDOR);
        resultado += String.format("Razón Social Vendedor: %s\r\n", RAZON_SOCIAL_VENDEDOR);
        resultado += fecha2String();
        resultado += "\r\n";
        resultado += String.format("ID Cliente: %s\r\n", cliente.getId());
        resultado += String.format("CIF Cliente: %s\r\n", cliente.getCif());
        resultado += String.format("Nombre: %s\r\n", cliente.getNombre());
        resultado += String.format("Apellidos: %s\r\n", cliente.getApellidos());
        resultado += String.format("Razón Social Cliente: %s\r\n", cliente.getRazonsocial());
        resultado += String.format("Domicilio: %s\r\n", cliente.getDomicilio());
        resultado += "ID Producto         ID Venta            Unidades  Importe(€)\r\n\r\n";
        while (vit.hasNext()){
            idventa = (String) vit.next();
            venta = items.get(idventa);
            acumulado += venta.getPreciototal();
            pitems = venta.getProductos();
            while (pit.hasNext()) {
                idproducto = (String) pit.next();
                producto = pitems.get(idproducto);
                resultado += String.format("%-20s", idproducto);
                resultado += String.format("%-20s", idventa);
                resultado += String.format("%-10d", producto.getCantidad());
                resultado += String.format("%-5.2f\r\n", producto.getPrecio() * producto.getCantidad());
            }
        }
        resultado += "\r\n                                                  TOTAL: " + String.format("%-5.2f", acumulado) + " €\r\n";
        return resultado;
    }
    
}
