package lista;

import interfaces.LSE;

public class ListaDinamica implements LSE{
    private Nodo list;
    
    public ListaDinamica(){
        list = null;
    }
    
    @Override
    public Nodo inicio(){
        return list;
    }
    
    @Override
    public boolean listaVacia() {
        return (list == null);
    }
    
    //Insertar LSE
    @Override
    public void insertar(String entrada) {
        Nodo x = new Nodo(entrada);
        if (list == null) {
            list = x;
        } else {
            x.setPs(list);
            list = x;
        }
    }
    //------------
    
    //Eliminar LSE
    @Override
    public Nodo quitar(Nodo p, Nodo ant) {
        Nodo x = p;

        if (p == list) {
            list = p.getPs();
        } else {
            ant.setPs(p.getPs());

        }
        return x;
    }
    
    @Override
    public Nodo eliminar(String elem) {
        Nodo x = null;
        Nodo p = list;
        Nodo ant = null;
        if(list == null)
            return x;
        boolean band = false;
        while (p != null && !band) {
            if (p.getDato().equals(elem)) {
                band = true;
            } else {
                ant = p;
                p = p.getPs();

            }
        }
        if (band == true) {
            quitar(p, ant);
            x = p;
        }
        return x;
    }
    //------------

}
