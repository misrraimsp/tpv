import java.util.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Almacena todas las ventas efectuadas y metodos para manipularlas
 * 
 * @author (misrraim) 
 * @version (150514)
 */
public class RegVenta extends Registro
{
    // METODOS

    /**
     * Constructor de objetos de la clase RegVenta
     */
    public RegVenta()
    {
        super();
    }

    /**
     * Introduce una nueva venta al registro
     * 
     * @param   Venta venta
     * @return  void
     * 
     */
    public void registrarVenta(Venta venta)
    {
        putElemento(venta);
    }
    
    /**
     * Introduce en el sistema las ventas válidas encontradas en el texto de entrada. El texto no reconocido se devuelve como rechazo
     * 
     * @param   String texto
     * @param   Registro regcliente
     * @param   Registro regproducto
     * @return  String rechazo
     * 
     */
    public String altaAutomatica(String texto, Registro regcliente, Registro regproducto)
    {
        //variables locales
        String idventa, idcliente, idproducto, scantidad, sproducto, rechazo;
        int cantidad;
        Calendar fechaventa;
        Producto producto;
        Cliente cliente;
        Venta venta;
        boolean flag = true; //para abortar la venta cuando se encuentre un producto no correcto
        Elemento elem = new Elemento(); // para usar los metodos de servicio: extraerPatron() extraerFecha()
        ArrayList<String> ventas = splitElementos(texto, "===Elemento #[0-9]*===");
        ArrayList<String> productos = new ArrayList<String>();
        Iterator it = productos.iterator();
        ArrayList<String> rechazos = new ArrayList<String>();
        //comprobar que hay elementos
        if (ventas.isEmpty()) {
            System.out.println("No hay ventas en el archivo");
            return null;
        }
        //extraer campos y crear ventas
        for (String sventa : ventas) {
            // separar los campos
            idventa = elem.extraerPatron(sventa, "ID Venta: ", ";", "[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]");
            fechaventa = elem.extraerFecha(sventa);
            idcliente = elem.extraerPatron(sventa, "ID Cliente: ", ";", "[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]");
            productos = splitElementos(sventa, "==");
            if (idventa == null || idcliente == null || fechaventa == null || productos.isEmpty()) {
                rechazos.add(sventa + "==Campo/s No Detectado/s==");
            }
            else {
                if(containsKey(idventa)) {
                    rechazos.add(sventa + String.format("==Venta %s Existente==", idventa));
                }
                else {
                    if (!regcliente.containsKey(idcliente)) { //el cliente no esta en el sistema
                        rechazos.add(sventa + String.format("==Cliente %s Desconocido==", idcliente));
                    }
                    else {
                        cliente = (Cliente) regcliente.getElemento(idcliente);
                        venta = new Venta(fechaventa, cliente);
                        venta.setId(idventa);
                        //recorrer los productos
                        while (it.hasNext() && flag) {
                            sproducto = (String) it.next();
                            idproducto = elem.extraerPatron(sproducto, "ID Producto: ", ";", "[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]");
                            scantidad = elem.extraerPatron(sproducto, "Cantidad: ", " unidades;", "[0-9]*");
                            if (idproducto == null ||scantidad == null) {
                                rechazos.add(sventa + "==Campo/s Producto No Detectado/s==");
                                flag = false;
                            }
                            else {
                                if (!regproducto.containsKey(idproducto)) {
                                    rechazos.add(sventa + String.format("==Producto %s Desconocido==", idproducto));
                                    flag = false;
                                }
                                else {
                                    cantidad = Integer.parseInt(scantidad);
                                    producto = (Producto) regproducto.getElemento(idproducto);
                                    producto.setCantidad(cantidad);
                                    venta.incluir(producto);
                                }
                            }
                        }
                        if (flag) { //todos los productos reconocidos e incluidos en al venta correctamente. Se añade la venta al registro
                            registrarVenta(venta);
                        }
                        else { //hubo un rechazo: se resetea la bandera
                            flag = true;
                        }
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
        for (String sventa : rechazos) {
            rechazo += String.format("%s\r\n\r\n", sventa);
        }
        return rechazo;
    }
    
}