package interfaces;

import pila.Nodo;

public interface Pila {
    public int espacio();
    
    public boolean pilaVacia();
    
    public void apilar(int oTemp);
    
    public Nodo desapilar();
    
}