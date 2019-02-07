import java.util.*;
import java.io.*;
public class EjercicioPatrones{
    public static void leerArchivo(String nombreArchivo, int opcion, ArrayList<String> lineas){
        try{
            FileReader archivoLeido = new FileReader(nombreArchivo+".txt");
            BufferedReader br = new BufferedReader(archivoLeido);
            String lineaLeida;
            String [] palabraLinea = null;
            String lineaFiltrada = "";
            while(true){
                lineaLeida=br.readLine();
                if(lineaLeida == null){
                    break;
                }else{
                    palabraLinea=lineaLeida.split(" ");
                    for(String each : palabraLinea){ 
                        if(!"".equals(each)){
                            String palabraFiltrada = filtro(each, opcion);
                            if(lineaFiltrada!=""){
                                lineaFiltrada = lineaFiltrada+" "+palabraFiltrada;
                            }else
                                lineaFiltrada = palabraFiltrada;                                   
                        }        
                    }
                    if(lineaFiltrada!="")
                        lineas.add(lineaFiltrada);
                    lineaFiltrada = "";
                }
            }
            if(null!=archivoLeido){
                archivoLeido.close();
            }
        } catch(IOException e){
            System.out.println("No se encontro el archivo.");
        }
    }
    public static void exportarArchivo(String nombreArchivo, int opcion, ArrayList<String> lineas){
        try{
            FileWriter archivoExportado = new FileWriter("Filtrado"+opcion+"-"+nombreArchivo);
            PrintWriter pw = new PrintWriter(archivoExportado);
            for(int i=0; i<lineas.size();i++)
                pw.println(lineas.get(i));
            if (null != archivoExportado){
                archivoExportado.close();
            }
        } catch(Exception e){
            e.printStackTrace();
        } 
    }
    public static String filtro(String palabra, int opcion){
        switch(opcion){
            case 1:                
                break;
            case 2:
                if((palabra.charAt(0))!='t')
                    palabra = "";
                break;
            case 3:
                if(palabra.length()!= 5)
                    palabra="";
                break;
            case 4:
                String palindromo = "";
                for(int i=1; i<=palabra.length();i++)
                    palindromo = palindromo+palabra.charAt(palabra.length()-i);
                if(palindromo.compareTo(palabra)!=0)
                    palabra="";
                break;
        }        
        return palabra;        
    }
    public static void main(String[] args){
        ArrayList<String> lineas = new ArrayList<>();
        System.out.println("Ejercicio sobre Patrones\nDigite el nombre del archivo que desea filtrar :");
        Scanner entradaNombre = new Scanner (System.in);
        String nombreArchivo = entradaNombre.nextLine();
        System.out.println("Seleccione el tipo de filtro que quiere usar:\n1) Sin Filtrar\n2) Solo palabras que inicien con \"t\"\n3) Solo palabras con 5 caracteres\n4) Solo palabras que sean palindromos");
        Scanner entradaOpcion = new Scanner (System.in);
        int opcion = entradaOpcion.nextInt();                
        leerArchivo(nombreArchivo, opcion, lineas);
        if (lineas.size()!=0)
            exportarArchivo(nombreArchivo, opcion, lineas); 
    }
}