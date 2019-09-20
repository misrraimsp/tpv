import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Clase Producto. Contiene la informacion y los metodos asociados a los productos
 * 
 * @author (misrraim)
 * @version (150514)
 */
public class Producto extends Elemento
{
   
    // VARIABLES DE INSTANCIA
    
    private String descripcion; // descripcion del producto
    private float preciosiniva; // precio sin iva
    private float iva; // iva aplicable
    private float precio; // precio con iva
    private int cantidad; // cantidad disponible en stock
    private float acumulado; // precio x cantidad
    
    // METODOS

        // METODOS CONSTRUCTORES
    
    /**
     * Constructor de objetos de la clase Producto, sin especificar la fecha de creacion del producto
     * 
     * @param   String descripcion
     * @param   float preciosiniva
     * @param   float iva
     * @param   int cantidad
     * @return  void
     * 
     */
    public Producto(String descripcion, float preciosiniva, float iva, int cantidad)
    {
        super();
        this.descripcion = descripcion;
        this.preciosiniva = preciosiniva;
        this.iva = iva;
        this.cantidad = cantidad;
        updateValores();
    }
    
    /**
     * Constructor de objetos de la clase Producto, especificando la fecha de alta del producto
     * 
     * @param   Calendar fecha
     * @param   String descripcion
     * @param   float preciosiniva
     * @param   float iva
     * @param   int cantidad
     * @return  void
     * 
     */
    public Producto(Calendar fecha, String descripcion, float preciosiniva, float iva, int cantidad)
    {
        super(fecha);
        this.descripcion = descripcion;
        this.preciosiniva = preciosiniva;
        this.iva = iva;
        this.cantidad = cantidad;
        updateValores();
    }
        
        // METODOS DE ACCESO
    
    /**
     * Redefinición de toString(): devuelve un String con toda la información ordenada del producto
     * 
     * @param   void
     * @return  String
     * 
    */
    public String toString()
    {
        //variables locales
        String resultado;
        char especial = 37; //codigo ascii del símbolo de porcentaje
        // punto como separador decimal
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat formateador = new DecimalFormat("###.##", simbolos);
        //cuerpo
        resultado = "";
        resultado += String.format("ID: %s;\r\n", getId());
        resultado += String.format("Descripción: %s;\r\n", descripcion);
        resultado += "Precio sin IVA: " + formateador.format(preciosiniva) + " €\r\n";
        resultado += String.format("IVA aplicable: %.0f %c\r\n", iva, especial);
        resultado += "Precio: " + formateador.format(precio) + " €\r\n";
        resultado += String.format("Cantidad disponible: %d unidades;\r\n", cantidad);
        resultado += fecha2String();
        return resultado;
    }    
    
    /**
     * Devuelve la descripcion de un producto
     * 
     * @param   void
     * @return  String descripcion
     * 
     */
    public String getDescripcion()
    {
        return descripcion;
    }
    
    /**
     * Devuelve la descripcion de un producto fijando el tamaño a 14 caracteres con alineación a izquierda
     * 
     * @param   void
     * @return  String descripcion
     * 
     */
    public String getFormatDescripcion()
    {
        if (descripcion.length() <= 14) {
            return String.format("%-14s", descripcion);
        }
        return descripcion.substring(0, 13);
    }    
    
    /**
     * Devuelve el precio de un producto sin IVA
     * 
     * @param   void
     * @return  float preciosiniva
     * 
     */
    public float getPreciosiniva()
    {
        return preciosiniva;
    }
    
    /**
     * Devuelve el precio de un producto, con IVA
     * 
     * @param   void
     * @return  float precio
     * 
     */
    public float getPrecio()
    {
        return precio;
    }
    
    /**
     * Devuelve el IVA asociado a un producto
     * 
     * @param   void
     * @return  float iva
     * 
     */
    public float getIva()
    {
        return iva;
    }
    
    /**
     * Devuelve la cantidad de un producto
     * 
     * @param   void
     * @return  int cantidad
     * 
     */
    public int getCantidad()
    {
        return cantidad;
    }
    
    /**
     * Devuelve el valor acumulado de un producto
     * 
     * @param   void
     * @return  float acumulado
     * 
     */
    public float getAcumulado()
    {
        return acumulado;
    }
    
        // METODOS MODIFICADORES
        
    /**
     * Modifica la cantidad de un producto
     * 
     * @param   int cantidad // nueva cantidad
     * @return  void
     * 
     */
    public void setCantidad(int cantidad)
    {
        this.cantidad = cantidad;
        updateValores();
    }
    
    /**
     * Modifica la descripcion de un producto
     * 
     * @param   String descripcion
     * @return  void
     * 
     */
    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }
    
    /**
     * Modifica el precio sin IVA de un producto
     * 
     * @param   float preciosiniva
     * @return  void
     * 
     */
    public void setPreciosiniva(float preciosiniva)
    {
        this.preciosiniva = preciosiniva;
        updateValores();
    }
    
    /**
     * Modifica el IVA aplicable a un producto
     * 
     * @param   float iva
     * @return  void
     * 
     */
    public void setIva(float iva)
    {
        this.iva = iva;
        updateValores();
    }
    
        // METODOS INTERNOS
    
    /**
     * Actualiza los valores del precio y del acumulado de un producto
     * 
     * @param   void
     * @return  void
     * 
     */
    private void updateValores()
    {
        precio = (1 + iva/100) * preciosiniva;
        acumulado = precio * cantidad;
    }

}
