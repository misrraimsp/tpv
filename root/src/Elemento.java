import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Write a description of class Elemento here.
 * 
 * @author (misrraim) 
 * @version (150514)
 */
public class Elemento
{
    // CONSTANTES DE CLASE
    private static final String REGEX_FECHA = "\\d{4}-\\d{2}-\\d{2}";
    private static final String REGEX_HORA = "\\d{2}:\\d{2}:\\d{2}";
    
    // VARIABLES DE INSTANCIA
    private String id;
    private Calendar fecha;

    // METODOS DE INSTANCIA
    
        //CONSTRUCTOR
        
    /**
     * Constructor de la clase Elemento. Crea objetos Elemento con la fecha (y hora) actual
     */
    public Elemento()
    {
        fecha = new GregorianCalendar();
        setId();
    }
    
    /**
     * Constructor de la clase Elemento. Crea objetos Elemento en los que se impone la fecha (y hora) pasada como parámetro
     * 
     * @param   Calendar fecha // la fecha de alta que se quiere establecer para el elemento
     * 
     */
    public Elemento(Calendar fecha)
    {
        this.fecha = fecha;
        setId();
    }

        //MODIFICADOR
        
    /**
     * Crea el ID del elemento a partir del campo fecha
     * 
     * @param   void
     * @return  void
     * 
     */
    public void setId()
    {
        int year = fecha.get(Calendar.YEAR);
        int month = fecha.get(Calendar.MONTH) + 1;
        int day = fecha.get(Calendar.DAY_OF_MONTH);
        int hour = fecha.get(Calendar.HOUR_OF_DAY);
        int minute = fecha.get(Calendar.MINUTE);
        int second = fecha.get(Calendar.SECOND);
        //generar codigo
        id = String.format("%04d%02d%02d%02d%02d%02d", year, month, day, hour, minute, second);
    }
    
    /**
     * Establece como ID el pasado en parámetro. (Atención: Puede hacer perder la relación entre ID y fecha)
     * 
     * @param   String id
     * @return  void
     * 
     */
    public void setId(String id)
    {
        this.id = id;
    }
    
        //ACCESO
        
    /**
     * Devuelve el código del elemento
     * 
     * @param   void
     * @return  String id
     * 
     */
    public String getId()
    {
        return id;
    }
    
    /**
     * Devuelve la fecha de alta del elemento
     * 
     * @param   void
     * @return  Calendar fecha
     * 
     */
    public Calendar getFecha()
    {
        return fecha;
    }
    
    /**
     * Devuelve la fecha en formato texto
     * 
     * @param   void
     * @return  String sfecha
     * 
     */
    public String fecha2String()
    {
        //variables locales
        String sfecha;
        //cuerpo
        sfecha = String.format("Fecha: %04d-%02d-%02d;\r\n", fecha.get(Calendar.YEAR), fecha.get(Calendar.MONTH) + 1, fecha.get(Calendar.DAY_OF_MONTH));
        sfecha += String.format("Hora: %02d:%02d:%02d;\r\n", fecha.get(Calendar.HOUR_OF_DAY), fecha.get(Calendar.MINUTE), fecha.get(Calendar.SECOND));
        return sfecha;
    }    
    
        //SERVICIO
        
    /**
     * Detecta, y devuelve en su caso, un patrón en el texto de entrada. Caso contrario devuelve null
     * 
     * @param   String texto
     * @param   String marcainicial
     * @param   String marcafinal
     * @param   String regex
     * @return  String patron
     * 
     */
    public String extraerPatron(String texto, String marcainicial, String marcafinal, String regex)
    {
        //variables locales
        int indiceinicial = texto.indexOf(marcainicial);
        int indicefinal = texto.indexOf(marcafinal, indiceinicial);
        String patron;
        //cuerpo
        if (indiceinicial == -1 || indicefinal == -1) {
            return null;
        }
        patron = texto.substring(indiceinicial + marcainicial.length(), indicefinal);
        if (!patron.matches(regex)) {
            return null;
        }
        return patron;
    }   
    
    /**
     * Detecta, y devuelve en su caso, una fecha válida en el texto de entrada. Caso contrario devuelve null
     * 
     * @param   String texto
     * @return  Calendar fecha
     * 
     */
    public Calendar extraerFecha(String texto)
    {
        //variables locales
        Calendar fecha;
        int year, month, day, hour, minute, seconds;
        String sfecha = extraerPatron(texto, "Fecha: ", ";", REGEX_FECHA);
        String shora = extraerPatron(texto, "Hora: ", ";", REGEX_HORA);
        //cuerpo
        if(sfecha == null || shora == null) {
            return null;
        }
        else {
            year = Integer.parseInt(sfecha.substring(0, 4));
            month = Integer.parseInt(sfecha.substring(5, 7)) - 1; //month is 0 based (enero --> 0)
            day = Integer.parseInt(sfecha.substring(8));
            hour = Integer.parseInt(shora.substring(0, 2));
            minute = Integer.parseInt(shora.substring(3, 5));
            seconds = Integer.parseInt(shora.substring(6));
            fecha = new GregorianCalendar(year, month, day, hour, minute, seconds);
            return fecha;
        }
    }  
    
}
