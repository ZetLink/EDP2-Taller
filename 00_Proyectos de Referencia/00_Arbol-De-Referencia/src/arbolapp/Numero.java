package arbolapp;

import interfaces.Comparador;
import java.util.Scanner;

public class Numero implements Comparador {

    private int valor;

    public Numero() {
        valor = 0;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public void cargarValor() {
        System.out.println("Ingrese un número de 3 cifras: ");
        boolean terminar = true;
        while (terminar) {
            valor = leerInt();
            if (valor > 99 && valor < 1000) {
                terminar = false;
            } else {
                System.out.println("Debe ingresar un número de 3 cifras... ");
            }
        }
    }

    private int leerInt() {
        Scanner entra = new Scanner(System.in);
        while (true) {
            try {
                return entra.nextInt();
            } catch (NumberFormatException e) {
                System.out.println("No es un entero. Por favor, pruebe otra vez!");
            }
        }
    }

    @Override
    public String toString() {
        return " " + this.getValor() + " ";
    }

    @Override
    public boolean igualQue(Object op2) {
        Numero x = (Numero) op2;
        return (getValor() == x.getValor());
    }

    @Override
    public boolean menorQue(Object op2) {
        Numero x = (Numero) op2;
        return (getValor() < x.getValor());
    }

    @Override
    public boolean menorIgualQue(Object op2) {
        Numero x = (Numero) op2;
        return (getValor() <= x.getValor());
    }

    @Override
    public boolean mayorQue(Object op2) {
        Numero x = (Numero) op2;
        return (getValor() > x.getValor());
    }

    @Override
    public boolean mayorIgualQue(Object op2) {
        Numero x = (Numero) op2;
        return (getValor() >= x.getValor());
    }

}
