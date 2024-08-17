package principal;

import excepciones.PilaException;
import excepciones.PilaVaciaException;
import interfaces.Pila;
import pila.*;
import utilidades.*;

public class Principal {
    
    PilaDinamica pila = new PilaDinamica();
    
        public void cargarPila(){
            boolean b = false;
            while (b == false){
                String input = Consola.readString("Numero: ");
                try {
                    int number = Integer.parseInt(input);
                    pila.apilar(number);
                } catch (NumberFormatException e) {
                    System.out.println("Error: El numero ingresado no es un ");
                }
                b = !Consola.continuar();
            } 
        }
    
    public void eliminarNodo() throws PilaVaciaException{
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
            throw new PilaVaciaException();
        }
    }
    
    public void mostrarPila() throws PilaVaciaException{
        if (!pila.pilaVacia()){
            PilaDinamica aux = new PilaDinamica();
            while (!pila.pilaVacia()){
                Nodo oTemp = pila.desapilar();
                System.out.println("Numero: " + oTemp.getElem());
                aux.apilar(oTemp.getElem());
            }
            retornar(pila, aux);
        } else {
            throw new PilaVaciaException();
        }
    }
    
    private void retornar(Pila A, Pila B){
        while (!B.pilaVacia()) {
            Nodo x = B.desapilar();  
            pila.apilar(x.getElem());
        }
    }
    
    public void menuDeOpciones() throws PilaException {
        String titulo = "Menu de Opciones";
        String[] opciones = {
            "1. Cargar pila",
            "2. Eliminar elemento",
            "3. Mostrar pila",
            "0. Salir"
        };
        int op;
        do {
            Consola.imprimirMenu(opciones, titulo);
            op = Consola.readInt(0,"-->");
            System.out.println("");
            switch (op) {
                case 1: cargarPila();
                        break;
                case 2: try {
                            eliminarNodo();
                        } catch (PilaVaciaException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                case 3: try {
                            mostrarPila();
                        } catch (PilaVaciaException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                case 0: break;
            }
        } while (op != 0);
    }
    
    public static void main(String[] args) throws PilaException {
        Principal app = new Principal();
        app.menuDeOpciones();
    }

}
