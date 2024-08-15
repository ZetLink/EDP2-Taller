package arbolapp;

import nodoarbol.Nodo;
import estructura.ArbolBinarioBusqueda;
import utilidades.Consola;

public class ArbolBBAPP {

    private ArbolBinarioBusqueda abb;

    public ArbolBBAPP() {
        abb = new ArbolBinarioBusqueda();
    }

    public void menuDeOpciones() {
        String titulo = "Menu de Opciones";
        String[] opciones = {
            "1. Generar Arbol",
            "2. Eliminar Elementos",
            "3. Imprimir Elementos",
            "0. Salir"
        };
        int op;
        do {
            Consola.imprimirMenu(opciones, titulo);
            op = Consola.readInt(0, "--> ");
            switch (op) {
                case 1:
                    generarArbol();
                    break;
                case 2:
                    borrar();
                    break;
                case 3:
                    imprimir();
                    break;
                case 0:
                    break;
            }
        } while (op != 0);
    }

    public void generarArbol() {
        do {
            int elemento = Consola.readInt(0, "Numero: ");
            Numero nro = new Numero();
            nro.setValor(elemento);
            abb.insertar(nro);
        } while (Consola.continuar());
    }

    public void borrar() {
        Numero nro = new Numero();
        Nodo b = null;
        do {
            int elemento = Consola.readInt(0, "Numero: ");
            nro.setValor(elemento);
            b = abb.borrar(abb.getRaiz(), null, nro, b);
            if (b != null) {
                System.out.println("EL ELEMENTO ELMINADOS ES: " + b.getDato());
            } else {
                System.out.println("EL ELEMENTO NO EXISTE");
            }
        } while (Consola.continuar());
    }

    public void imprimir() {
        String titulo = "Menu de Impresion";
        String[] opciones = {
            "1. Imprimir EntreOrder",
            "2. Imprimir PreOrden",
            "3. Imprimir PostOrden",
            "0. Salir"
        };
        int op;
        do {
            Consola.imprimirMenu(opciones,titulo);
            op = Consola.readInt(0, "--> ");
            switch (op) {
                case 1:
                    abb.entreorden();
                    break;
                case 2:
                    abb.preorden();
                    break;
                case 3:
                    abb.postorden();
                    break;
                case 0:
                    break;
            }
        } while (op != 0);
    }

    public static void main(String[] args) {
        ArbolBBAPP MiArbol = new ArbolBBAPP();
        MiArbol.menuDeOpciones();
    }
}
