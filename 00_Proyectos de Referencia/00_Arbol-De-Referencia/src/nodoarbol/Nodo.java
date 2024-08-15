package nodoarbol;

public class Nodo {

    private Object dato;
    private Nodo izdo;
    private Nodo dcho;

    public Nodo(Object valor) {
        dato = valor;
        izdo = dcho = null;
    }

    public Nodo(Nodo ramaIzdo, Object valor, Nodo ramaDcho) {
        dato = valor;
        izdo = ramaIzdo;
        dcho = ramaDcho;
    }

    public Object getDato() {
        return dato;
    }

    public Nodo getIzdo() {
        return izdo;
    }

    public Nodo getDcho() {
        return dcho;
    }

    public void setDato(Object d) {
        dato = d;
    }

    public void setIzdo(Nodo n) {
        izdo = n;
    }

    public void setDcho(Nodo n) {
        dcho = n;
    }

    public void imprimirDato() {
        System.out.print(dato.toString() + "\n\n");
    }
}
