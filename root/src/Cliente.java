
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Clase Cliente. Contiene la informacion y los metodos asociados a los clientes
 * 
 * @author (misrraim)
 * @version (150514)
 */
public class Cliente extends Elemento
{
   
    // VARIABLES DE INSTANCIA
    
    private String cif; // cif del cliente
    private String nombre; // nombre del cliente
    private String apellidos; // apellidos del cliente
    private String razonsocial; // razon social del cliente
    private String domicilio; // domicilio del cliente
    
    // METODOS

        // METODOS CONSTRUCTORES
    
    /**
     * Constructor de objetos de la clase Cliente, sin especificar la fecha de alta
     * 
     * @param   String cif
     * @param   String nombre
     * @param   String apellidos
     * @param   String razonsocial
     * @param   String domicilio
     * @return  void
     *
     */
    public Cliente(String cif, String nombre, String apellidos, String razonsocial, String domicilio)
    {
        super();
        this.cif = cif;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.razonsocial = razonsocial;
        this.domicilio = domicilio;
    }
    
    /**
     * Constructor de objetos de la clase Cliente, especificando fecha de alta
     * 
     * @param   Calendar fecha 
     * @param   String cif
     * @param   String nombre
     * @param   String apellidos
     * @param   String razonsocial
     * @param   String domicilio
     * @return  void
     * 
     */
    public Cliente(Calendar fecha, String cif, String nombre, String apellidos, String razonsocial, String domicilio)
    {
        super(fecha);
        this.cif = cif;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.razonsocial = razonsocial;
        this.domicilio = domicilio;
    }

        // METODOS DE ACCESO
    
    /**
     * Devuelve el nombre del cliente
     * 
     * @param   void  
     * @return  String nombre
     * 
     */
    public String getNombre()
    {
        return nombre;
    }
    
    /**
     * Devuelve el CIF del cliente
     * 
     * @param   void  
     * @return  String cif
     * 
     */
    public String getCif()
    {
        return cif;
    }
    
    /**
     * Devuelve los apellidos del cliente
     * 
     * @param   void  
     * @return  String apellidos
     * 
     */
    public String getApellidos()
    {
        return apellidos;
    }
    
    /**
     * Devuelve la razón social del cliente
     * 
     * @param   void  
     * @return  String razonsocial
     * 
     */
    public String getRazonsocial()
    {
        return razonsocial;
    }
    
    /**
     * Devuelve el domicilio del cliente
     * 
     * @param   void
     * @return  String domicilio
     * 
     */
    public String getDomicilio()
    {
        return domicilio;
    }
    
    /**
     * Redefinición de toString(): devuelve un String con toda la información ordenada del cliente
     * 
     * @param   void
     * @return  String cliente
     * 
    */
    public String toString()
    {
        //variables locales
        String resultado;
        //cuerpo
        resultado = "";
        resultado += String.format("ID: %s;\r\n", getId());
        resultado += String.format("CIF: %s;\r\n", cif);
        resultado += String.format("Nombre: %s;\r\n", nombre);
        resultado += String.format("Apellidos: %s;\r\n", apellidos);
        resultado += String.format("Razón Social: %s;\r\n", razonsocial);
        resultado += String.format("Domicilio: %s;\r\n", domicilio);
        resultado += fecha2String();
        return resultado;
    } 
    
        // METODOS MODIFICADORES
    
    /**
     * Modifica el nombre del cliente
     * 
     * @param   String nombre
     * @return  void
     * 
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
    
    /**
     * Modifica el CIF del cliente
     * 
     * @param   String cif
     * @return  void
     * 
     */
    public void setCif(String cif)
    {
        this.cif = cif;
    }
    
    /**
     * Modifica los apellidos del cliente
     * 
     * @param   String apellidos
     * @return  void
     * 
     */
    public void setApellidos(String apellidos)
    {
        this.apellidos = apellidos;
    }
    
    /**
     * Modifica la razón social del cliente
     * 
     * @param   String razonsocial
     * @return  void
     * 
     */
    public void setRazonsocial(String razonsocial)
    {
        this.razonsocial = razonsocial;
    }
    
    /**
     * Modifica el domicilio del cliente
     * 
     * @param   String domicilio
     * @return  void
     * 
     */
    public void setDomicilio(String domicilio)
    {
        this.domicilio = domicilio;
    }
   
}