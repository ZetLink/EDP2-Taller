package principal;

import java.io.*;
import lista.ListaDinamica;
import lista.Nodo;

public class Principal {

    ListaDinamica l1 = new ListaDinamica();
    
    public void leerArchivo(){
        try {
            FileInputStream file = new FileInputStream(".\\prueba.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(file));
            String linea;
            while ((linea = br.readLine()) != null) {
                l1.insertar(linea);
            }
            br.close();
            file.close();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        }
    }
    
    public void mostrarLista(){
        Nodo p = l1.inicio();
        while (p != null){
            System.out.println(p.getDato());
            p = p.getPs();
        }
    }
    
    public static void main(String[] args) {
        Principal app = new Principal();
        app.leerArchivo();
        app.mostrarLista();
    }

}
