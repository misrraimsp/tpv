import java.util.*;

/**
 * Clase de los objetos RegCliente, donde se almacenan los clientes.
 * 
 * @author (misrraim) 
 * @version (150514)
 */
public class RegCliente extends Registro
{
    // METODOS
    
        // METODO CONSTRUCTOR
    
    /**
     * Constructor de objetos de la clase RegCliente
     */
    public RegCliente()
    {
        super();
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
    public void alta(String cif, String nombre, String apellidos, String razonsocial, String domicilio)
    {
        //variables locales
        Cliente nuevocliente = new Cliente(cif, nombre, apellidos, razonsocial, domicilio);
        //cuerpo
        putElemento(nuevocliente);
    }
    
    /**
     * Da de baja un cliente
     * 
     * @param   String idcliente
     * @return  void
     * 
     */
    public void baja(String idcliente)
    {
        //variables locales
        Cliente eliminado = (Cliente) removeElemento(idcliente);
        //cuerpo
        if (eliminado == null) {
                System.out.println("No existe el cliente que se desea dar de baja");
        }
    }
    
    /**
     * Introduce en el sistema los clientes válidos encontrados en el texto de entrada. El texto no reconocido se devuelve
     * 
     * @param   String texto
     * @return  String rechazo
     * 
     */
    public String altaAutomatica(String texto)
    {
        //variables locales
        String idcliente, cif, nombre, apellidos, razonsocial, domicilio, rechazo;
        Calendar fecha;
        Cliente cliente;
        Elemento elem = new Elemento(); // para usar extraerPatron()
        ArrayList<String> clientes = splitElementos(texto, "===Elemento #[0-9]*===");
        ArrayList<String> rechazos = new ArrayList<String>();
        //cuerpo
        for (String scliente : clientes) {
            // separar los campos
            idcliente = elem.extraerPatron(scliente, "ID Cliente: ", ";", REGEX_ID);
            cif = elem.extraerPatron(scliente, "CIF: ", ";", ".*");
            nombre = elem.extraerPatron(scliente, "Nombre: ", ";", ".*");
            apellidos = elem.extraerPatron(scliente, "Apellidos: ", ";", ".*");
            razonsocial = elem.extraerPatron(scliente, "Razón Social: ", ";", ".*");
            domicilio = elem.extraerPatron(scliente, "Domicilio: ", ";", ".*");
            fecha = elem.extraerFecha(scliente);
            if (idcliente == null || cif == null || nombre == null || apellidos == null || razonsocial == null || domicilio == null || fecha == null) {
                rechazos.add(scliente + "==Campo/s No Detectado/s==");
            }
            else {
                //introducir al sistema
                if(!containsKey(idcliente)) { // se crea un nuevo cliente si éste ya no estaba en el sistema
                    cliente = new Cliente(fecha, cif, nombre, apellidos, razonsocial, domicilio);
                    cliente.setId(idcliente);
                    putElemento(cliente);
                }
                else {
                    rechazos.add(scliente + String.format("==Cliente %s Existente==", idcliente));
                }
            }
        }
        //comprobar rechazo
        if (rechazos.isEmpty()) {
            return null;
        }
        //formatear el rechazo
        rechazo = "\r\n===TEXTO RECHAZADO===\r\n\r\n";
        for (String scliente : rechazos) {
            rechazo += String.format("%s\r\n\r\n", scliente);
        }
        return rechazo;
    }
   
        // MODIFICADORES
    
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
        // variables locales
        Cliente cliente = (Cliente) removeElemento(idcliente); //saco el cliente del registro
        // comprobar que el cliente existe
        if (cliente == null) {
            System.out.println("El cliente al que se hace referencia no existe");
            return;
        }
        // actualizar el CIF
        cliente.setCif(cif);
        putElemento(cliente); //devuelvo el cliente al registro con el cif modificado
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
        // variables locales
        Cliente cliente = (Cliente) removeElemento(idcliente); //saco el cliente del registro
        // comprobar que el cliente existe
        if (cliente == null) {
            System.out.println("El cliente al que se hace referencia no existe");
            return;
        }
        // actualizar el nombre
        cliente.setNombre(nombre);
        putElemento(cliente); //devuelvo el cliente al registro con el nombre modificado
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
        // variables locales
        Cliente cliente = (Cliente) removeElemento(idcliente); //saco el cliente del registro
        // comprobar que el cliente existe
        if (cliente == null) {
            System.out.println("El cliente al que se hace referencia no existe");
            return;
        }
        // actualizar el nombre
        cliente.setApellidos(apellidos);
        putElemento(cliente); //devuelvo el cliente al registro con los apellidos modificados
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
        // variables locales
        Cliente cliente = (Cliente) removeElemento(idcliente); //saco el cliente del registro
        // comprobar que el cliente existe
        if (cliente == null) {
            System.out.println("El cliente al que se hace referencia no existe");
            return;
        }
        // actualizar el nombre
        cliente.setRazonsocial(razonsocial);
        putElemento(cliente); //devuelvo el cliente al registro con la razon social modificada
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
        // variables locales
        Cliente cliente = (Cliente) removeElemento(idcliente); //saco el cliente del registro
        // comprobar que el cliente existe
        if (cliente == null) {
            System.out.println("El cliente al que se hace referencia no existe");
            return;
        }
        // actualizar el nombre
        cliente.setDomicilio(domicilio);
        putElemento(cliente); //devuelvo el cliente al registro con el domicilio modificado
    }

}