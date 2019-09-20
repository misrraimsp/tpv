import java.io.*;

/**
 * Gestiona la lectura y escritura de archivos
 * 
 * @author (misrraim) 
 * @version (150514)
 */
public class IO
{
    private final String UBICACION = "C:\\Users\\Public\\";
    
    
    /**
     * Constructor for objects of class IO
     */
    public IO()
    {  
    }

    /**
     * Escribe en el fichero especificado el texto dado. Si el fichero no existe lo crea
     * 
     * @param   String nombrearchivo // nombre del archivo donde se desea escribir
     * @param   String texto // texto que se desea escribir
     * @return  void
     * 
     */
    public void escribir(String nombrearchivo, String texto)
    {
        if (texto == null) {
            return;
        }
        try
        {
            FileWriter fw = new FileWriter(UBICACION + nombrearchivo); 
            fw.write(texto);
            fw.close();
        }
        catch(IOException e)
        {
            System.out.println("Error: " + e);
        }
    }
    
    /**
     * Lee un fichero y devuelve el texto leído
     * 
     * @param  String nombrearchivo // nombre del archivo donde se desea leer
     * @return String texto // texto leído
     * 
     */
    public String leer(String nombrearchivo)
    {
        String texto = "";
        FileReader fr = null;
        String linea = "";
        try {
            fr = new FileReader(UBICACION + nombrearchivo);
            BufferedReader br = new BufferedReader(fr);
            while ((linea = br.readLine()) != null) {
                texto += linea + "\n";
            }
        }   catch (FileNotFoundException e) {
                throw new RuntimeException("Archivo no encontrado");
        }   catch (IOException e) {
                throw new RuntimeException("I/O Error");
        }   finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return texto;
    }    
}
