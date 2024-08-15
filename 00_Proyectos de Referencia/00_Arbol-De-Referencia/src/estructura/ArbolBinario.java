package estructura;

import nodoarbol.Nodo;
import interfaces.Comparador;

public class ArbolBinario {

    protected Nodo raiz;

    public ArbolBinario() {
        raiz = null;
    }

    public ArbolBinario(Nodo raiz) {
        this.raiz = raiz;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    boolean esVacio() {
        return raiz == null;
    }

    public void preorden() {
        this.preorden(this.raiz);
    }

    public void entreorden() {
        this.entreorden(this.raiz);
    }

    public void postorden() {
        this.postorden(this.raiz);
    }

// Recorrido de un árbol binario en preorden
    private void preorden(Nodo r) {
        if (r != null) {
            r.imprimirDato();
            preorden(r.getIzdo());
            preorden(r.getDcho());
        }
    }
    
// Recorrido de un árbol binario en inorden o entreorden
    private void entreorden(Nodo r) {
        if (r != null) {
            entreorden(r.getIzdo());
            r.imprimirDato();
            entreorden(r.getDcho());
        }
    }
    
// Recorrido de un árbol binario en postorden
    private void postorden(Nodo r) {
        if (r != null) {
            postorden(r.getIzdo());
            postorden(r.getDcho());
            r.imprimirDato();
        }
    }

    public boolean pertenece(Object buscado) {
        Comparador dato;
        dato = (Comparador) buscado;
        if (raiz == null) {
            return false;
        } else {
            return pertenece(getRaiz(), dato);
        }
    }

    private boolean pertenece(Nodo raizSub, Comparador buscado) {
        if (raizSub == null) {
            return false;
        } else if (buscado.igualQue(raizSub.getDato())) {
            return true;
        } else if (pertenece(raizSub.getIzdo(), buscado)) {
            return true;
        } else if (pertenece(raizSub.getDcho(), buscado)) {
            return true;
        } else {
            return false;
        }
    }
// Elimina un nodo del árbol y lo retorna
    public Nodo borrar(Nodo p, Nodo ant, Object dato, Nodo q) {
        Comparador buscado;
        buscado = (Comparador) dato;
        if (p != null) {
            if (buscado.igualQue(p.getDato())) {
                q = desengancharNodo(p, ant);
            } else {
                q = borrar(p.getIzdo(), p, dato, q);
                if (q == null) {
                    q = borrar(p.getDcho(), p, dato, q);
                }
            }
        }
        return q;
    }

    public Nodo desengancharNodo(Nodo p, Nodo ant) {
        Nodo q;
        if (p.getIzdo() == null && p.getDcho() == null) {
            q = eliminarHoja(p, ant);
        } else {
            if (p.getIzdo() != null && p.getDcho() != null) {
                q = eliminarDosSubArbol(p);
            } else {
                q = eliminarUnSubArbol(p, ant);
            }
        }
        return q;
    }

    public Nodo eliminarHoja(Nodo p, Nodo ant) {
        if (ant == null) {
            raiz = null;
        } else {
            if (ant.getIzdo() == p) {
                ant.setIzdo(null);
            } else {
                ant.setDcho(null);
            }
        }
        return p;
    }

    public Nodo eliminarUnSubArbol(Nodo p, Nodo ant) {
        if (ant == null) {
            if (p.getIzdo() == null) {
                raiz = p.getDcho();
            } else {
                raiz = p.getIzdo();
            }
        } else {
            if (ant.getIzdo() == p) {
                if (p.getIzdo() == null) {
                    ant.setIzdo(p.getDcho());
                } else {
                    ant.setIzdo(p.getIzdo());
                }
            } else {
                if (p.getDcho() == null) {
                    ant.setDcho(p.getIzdo());
                } else {
                    ant.setDcho(p.getDcho());
                }
            }
        }
        return p;
    }

    public Nodo eliminarDosSubArbol(Nodo p) {
        Nodo ant = p;
        Nodo q = p.getDcho();
        while (q.getIzdo() != null) {
            ant = q;
            q = q.getIzdo();
        }
        Object Aux = p.getDato();
        p.setDato(q.getDato());
        q.setDato(Aux);
        q = desengancharNodo(q, ant);
        return q;
    }
}
