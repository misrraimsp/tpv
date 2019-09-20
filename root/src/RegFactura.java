import java.util.*;

/**
 * Clase RegFactura: Contiene los métodos necesarios para gestionar y almacenar las facturas del sistema
 * 
 * @author (misrraim) 
 * @version (150514)
 */
public class RegFactura extends Registro
{
    // METODOS
    
        // CONSTRUCTOR

    /**
     * Constructor de objetos de la clase RegFactura
     */
    public RegFactura()
    {
        super();
    }

    /**
     * Introduce una nueva factura al registro
     * 
     * @param   Factura factura
     * @return  void
     * 
     */
    public void registrarFactura(Factura factura)
    {
        putElemento(factura);
    }
    
    /**
     * Introduce en el sistema las facturas válidas encontradas en el texto de entrada. El texto no reconocido es devuelto como rechazo
     * 
     * @param   String texto
     * @param   Registro regcliente
     * @param   Registro regventa
     * @return  String rechazo
     * 
     */
    public String altaAutomatica(String texto, Registro regcliente, Registro regventa)
    {
        //variables locales
        String idfactura, idventa, idcliente, sventa, pfiscal, vpfiscal, rechazo;
        Calendar fechafactura;
        Venta venta;
        Cliente cliente;
        Factura factura;
        boolean flag = true; //para abortar la factura cuando se encuentre una venta no correcta
        Elemento elem = new Elemento(); // para usar los metodos de servicio: extraerPatron() extraerFecha()
        ArrayList<String> facturas = splitElementos(texto, "===Elemento #[0-9]*===");
        ArrayList<String> ventas = new ArrayList<String>();
        Iterator it = ventas.iterator();
        ArrayList<String> rechazos = new ArrayList<String>();
        //comprobar que hay elementos
        if (facturas.isEmpty()) {
            System.out.println("No hay facturas en el archivo");
            return null;
        }
        //extraer campos y crear facturas
        for (String sfactura : facturas) {
            // separar los campos
            idfactura = elem.extraerPatron(sfactura, "ID Factura: ", ";", REGEX_ID);
            fechafactura = elem.extraerFecha(sfactura);
            idcliente = elem.extraerPatron(sfactura, "ID Cliente: ", ";", REGEX_ID);
            pfiscal = elem.extraerPatron(sfactura, "Periodo Fiscal: ", ";", "[12][09][019][0-9]");
            ventas = splitElementos(sfactura, "==");
            if (idfactura == null || idcliente == null || fechafactura == null || pfiscal == null || ventas.isEmpty()) {
                rechazos.add(sfactura + "==Campo/s No Detectado/s==");
            }
            else {
                if(containsKey(idfactura)) {
                    rechazos.add(sfactura + String.format("==Factura %s Existente==", idfactura));
                }
                else {
                    if (!regcliente.containsKey(idcliente)) { //el cliente no esta en el sistema
                        rechazos.add(sfactura + String.format("==Cliente %s Desconocido==", idcliente));
                    }
                    else {
                        cliente = (Cliente) regcliente.getElemento(idcliente);
                        factura = new Factura(fechafactura, cliente, pfiscal);
                        factura.setId(idfactura);
                        //recorrer las ventas
                        while (it.hasNext() && flag) {
                            sventa = (String) it.next();
                            idventa = elem.extraerPatron(sventa, "ID Venta: ", ";", REGEX_ID);
                            if (idventa == null) {
                                rechazos.add(sfactura + "==Campo/s Venta No Detectado/s==");
                                flag = false;
                            }
                            else {
                                if (!regventa.containsKey(idventa)) {
                                    rechazos.add(sfactura + String.format("==Venta %s Desconocida==", idventa));
                                    flag = false;
                                }
                                else {
                                    venta = (Venta) regventa.getElemento(idventa);
                                    vpfiscal = String.format("%s", venta.getFecha().get(Calendar.YEAR));
                                    if(!vpfiscal.equals(pfiscal)){ //mismo periodo fiscal
                                        rechazos.add(sfactura + String.format("==Venta %s Viola PFiscal==", idventa));
                                        flag = false;
                                    }
                                    else {
                                        if(!venta.getCliente().getId().equals(idcliente)){ //mismo cliente
                                            rechazos.add(sfactura + String.format("==Venta %s Viola IdCliente==", idventa));
                                            flag = false;
                                        }
                                        else {
                                            factura.incluir(venta);
                                        }
                                    }
                                }
                            }
                        }
                        if (flag) { //todos los productos reconocidos e incluidos en al venta correctamente. Se añade la venta al registro
                            registrarFactura(factura);
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
        for (String sfactura : rechazos) {
            rechazo += String.format("%s\r\n\r\n", sfactura);
        }
        return rechazo;
    }    

}
