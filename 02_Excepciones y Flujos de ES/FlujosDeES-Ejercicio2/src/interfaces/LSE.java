package interfaces;

import lista.Nodo;

public interface LSE {
    
    public Nodo inicio();
    
    public boolean listaVacia();
    
    public void insertar(String entrada);
    
    public Nodo eliminar(String elem);
    
    public Nodo quitar(Nodo p, Nodo ant);
}
