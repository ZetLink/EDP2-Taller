package pila;

import interfaces.Pila;
import excepciones.DesapilarPVException;

public class PilaDinamica implements Pila{
    private Nodo tope;
    private int espacio;
    
    public PilaDinamica() {
        tope=null; 
        espacio=0;
    }
 
    public int espacio() {
        return espacio;
    }
    
    public boolean pilaVacia () {
        return(tope == null);
    }
 
    public void apilar(int e) {
        Nodo nuevo = new Nodo(e);   
        if(pilaVacia()){
            tope = nuevo;
        } else {
            nuevo.setPs(tope);
            tope = nuevo;
        }
        espacio++;
    }

    public Nodo desapilar(){
        Nodo aux = null;
        if(pilaVacia()){
            System.out.println("Pila Vacia");
        } else {
            aux = tope;
            tope = tope.getPs();
            espacio--;
        }
        return aux;
    }
}
