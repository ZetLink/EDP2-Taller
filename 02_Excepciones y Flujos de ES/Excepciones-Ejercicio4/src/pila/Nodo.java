package pila;

public class Nodo {
    private int elem;
    private Nodo ps;

    public Nodo(int elem) {
        this.elem = elem;
    }

    public int getElem() {
        return elem;
    }

    public void setElem(int elem) {
        this.elem = elem;
    }

    public Nodo getPs() {
        return ps;
    }

    public void setPs(Nodo ps) {
        this.ps = ps;
    }
}
