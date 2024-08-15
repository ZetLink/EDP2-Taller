package estructura;

import nodoarbol.Nodo;
import estructura.ArbolBinario;
import interfaces.Comparador;

// HEREDA metodos getRaiz, esVacio, preorden, entreorden, postorden, pertenece, 
// desengancharNodo, eliminarDosSubArbol, eliminarHoja y eliminarUnSubArbol
// REDEFINE metodo borrar
// APLICA el orden binario en sus operaciones

public class ArbolBinarioBusqueda extends ArbolBinario {

    public ArbolBinarioBusqueda() {
        super();
    }

    // Inserción en el árbol aplicando orden binario
    public void insertar(Object dato) {
        if (esVacio()) {
            raiz = new Nodo(dato);
        } else {
            insertarNodo(raiz, (Comparador) dato);
        }
    }

    // El nodo se inserta a la izq si es menor y a la der si es mayor
    private void insertarNodo(Nodo p, Comparador dato) {
        if (dato.mayorQue(p.getDato())) {
            if (p.getDcho() == null) {
                p.setDcho(new Nodo(dato));
            } else {
                insertarNodo(p.getDcho(), dato);
            }
        } else if (p.getIzdo() == null) {
            p.setIzdo(new Nodo(dato));
        } else {
            insertarNodo(p.getIzdo(), dato);
        }
    }

    // Para buscar se considera el ordenamiento binario
    public Nodo buscar(Object buscado) {
        Comparador dato;
        dato = (Comparador) buscado;
        if (raiz == null) {
            return null;
        } else {
            return localizar(getRaiz(), dato);
        }
    }

    private Nodo localizar(Nodo raizSub, Comparador buscado) {
        if (raizSub == null) {
            return null;
        } else if (buscado.igualQue(raizSub.getDato())) {
            return raizSub;
        } else if (buscado.menorQue(raizSub.getDato())) {
            return localizar(raizSub.getIzdo(), buscado);
        } else {
            return localizar(raizSub.getDcho(), buscado);
        }
    }

    // Recorre el árbol de acuerdo a su orden para eliminar un nodo
    @Override
    public Nodo borrar(Nodo p, Nodo ant, Object dato, Nodo q) {
        Comparador buscado;
        buscado = (Comparador) dato;
        if (p != null) {
            if (buscado.igualQue(p.getDato())) {
                q = desengancharNodo(p, ant);
            } else {
                if (buscado.mayorIgualQue(p.getDato())) {
                    q = borrar(p.getDcho(), p, dato, q);
                } else {
                    q = borrar(p.getIzdo(), p, dato, q);
                }
            }
        }
        return q;
    }
}