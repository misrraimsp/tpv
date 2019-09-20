import java.util.*;

/**
 * Write a description of class Registro here.
 * 
 * @author (misrraim) 
 * @version (150514)
 */
public class Registro
{
    // CONSTANTES DE CLASE
    protected static final String REGEX_ID = "\\d{14}";
    
    // VARIABLES DE INSTANCIA
    private HashMap<String, Elemento> registro;

    /**
     * Constructor de objetos de la clase Registro
     */
    public Registro()
    {
        registro = new HashMap<String, Elemento>();
    }

    /**
     * Retorna un ArrayList<String> resultado de cortar la cadena de entrada según la expresión de separación proporcionada
     * 
     * @param   String texto, 
     * @param   String regex
     * @return  ArrayList<String>
     * 
     */
    public ArrayList<String> splitElementos(String texto, String regex)
    {
        //valido?
        if ( texto == null || regex == null) {
            return null;
        }
        //variables locales
        ArrayList<String> lista = new ArrayList();
        String[] cadenas = texto.split(regex); // como minimo habrá 1 cadena (igual al texto de entrada), que es el caso de no encontrar el patron regex
        //cuerpo
        for (String cadena : cadenas) {
            lista.add(cadena);
        }
        lista.remove(0); //el primer objeto de la lista nunca es un elemento, es lo que esta antes del primer elemento. Se elimina por tanto
        return lista;
    }

    /**
     * Redefinición de toString(): devuelve un String con toda la información ordenada del elemento correspondiente
     * 
     * @param   void
     * @return  String
     * 
    */
    public String toString()
    {
        //variables locales
        int indice = 1;
        String resultado, id, elemento;
        Set llaves = registro.keySet();
        Iterator it = llaves.iterator();
        //cuerpo
        resultado = "";
        while(it.hasNext()) {
            id = (String) it.next();
            elemento = registro.get(id).toString();
            resultado += String.format("\r\n===Elemento #%d===\r\n\r\n", indice) + elemento + "\r\n";
            indice ++;
        }
        return resultado;
    }
    
    /**
     * Devuelve un conjunto con las llaves del registro
     * 
     * @param   void
     * @return  Set<String> llaves
     * 
    */
    public Set<String> getLlaves()
    {
        //variables locales
        Set llaves = registro.keySet();
        return llaves;
    }
    
    /**
     * Devuelve el elemento correspondiente a la llave dada como parámetro
     * 
     * @param   String llave
     * @return  Elemento elemento
     * 
    */
    public Elemento getElemento(String llave)
    {
        return registro.get(llave);
    }
    
    /**
     * Introduce un elemento al registro
     * 
     * @param   Elemento elemento
     * @return  void
     * 
    */
    public void putElemento(Elemento elemento)
    {
        registro.put(elemento.getId(), elemento);
    }
    
    /**
     * Elimina del registro el elemento correspondiente a la llave dada como parámetro. Caso del elemento existir es devuelto como parámetro.
     * Si el elemento no existe se retorna null.
     * 
     * @param   String llave
     * @return  Elemento elemento
     * 
    */
    public Elemento removeElemento(String llave)
    {
        return registro.remove(llave);
    }    

    /**
     * Devuelve true si el registro contiene la llave, false en caso contrario
     * 
     * @param   String llave
     * @return  boolean
     * 
    */
    public boolean containsKey(String llave)
    {
        return registro.containsKey(llave);
    }
    
}

