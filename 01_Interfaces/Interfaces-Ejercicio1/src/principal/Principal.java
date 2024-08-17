package principal;

import pila.PilaDinamica;
import pila.Nodo;
import interfaces.Pila;
import utilidades.Consola;

public class Principal {
    
    PilaDinamica pila = new PilaDinamica();
    
    public void cargarPila(){
        boolean b = false;
        while (b == false){
            int num = Consola.readInt(0, "Numero: ");
            pila.apilar(num);
            b = !Consola.continuar();
        } 
    }
    
    public void eliminarNodo(){
        if (!pila.pilaVacia()){
            PilaDinamica aux = new PilaDinamica();
            int nAux = Consola.readInt(0, "Numero a eliminar: ");
            while (!pila.pilaVacia()){
                Nodo oTemp = pila.desapilar();
                if (oTemp.getElem() == nAux){
                    System.out.println(nAux + " eliminado");
                } else {
                    aux.apilar(oTemp.getElem());
                }
            }
            retornar(pila, aux);
        } else {
            System.out.println("Pila Vacia");
        }
    }
    
    public void mostrarPila(){
        if (!pila.pilaVacia()){
            PilaDinamica aux = new PilaDinamica();
            while (!pila.pilaVacia()){
                Nodo oTemp = pila.desapilar();
                System.out.println("Numero: " + oTemp.getElem());
                aux.apilar(oTemp.getElem());
            }
            retornar(pila, aux);
        } else {
            System.out.println("Pila Vacia");
        }
    }
    
    private void retornar(Pila A, Pila B){
        while (!B.pilaVacia()) {
            Nodo x = B.desapilar();  
            pila.apilar(x.getElem());
        }
    }
    
    public void menuDeOpciones() {
        String titulo = "Menu de Opciones";
        String[] opciones = {
            "1. Cargar Pila",
            "2. Eliminar Elemento",
            "3. Mostrar",
            "0. Salir"
        };
        int op;
        do {
            Consola.imprimirMenu(opciones, titulo);
            op = Consola.readInt(0, "-->");
            System.out.println("");
            switch (op) {
                case 1: cargarPila();
                        break;
                case 2: eliminarNodo();
                        break;
                case 3: mostrarPila();
                        break;
                case 0: break;
            }
        } while (op != 0);
    }
    
    public static void main(String[] args) {
        Principal app = new Principal();
        app.menuDeOpciones();
    }

}
