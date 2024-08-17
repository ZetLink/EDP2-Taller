package principal;

import utilidades.Consola;

public class Calculadora {

    private int num1;
    private int num2;

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }
    
    public void cargarDatos(){
        setNum1(Consola.readInt(0, "Primer Numero: "));
        setNum2(Consola.readInt(0, "Segundo Numero: "));
    }
    
    public int sumar(int a, int b) {
        return a + b;
    }

    public int restar(int a, int b) {
        return a - b;
    }

    public int multiplicar(int a, int b) {
        return a * b;
    }

    public int dividir(int a, int b) {
        try {
            return a / b;
        } catch (ArithmeticException e) {
            System.out.println("Error: División por cero no permitida.");
            return 0;
        }
    }
}