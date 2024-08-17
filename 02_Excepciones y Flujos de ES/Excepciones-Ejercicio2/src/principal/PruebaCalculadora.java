package principal;

import utilidades.Consola;

public class PruebaCalculadora {

    Calculadora calculadora = new Calculadora();
    
    public void menuDeOpciones() {
        String titulo = "Menu";
        String[] opciones = {
            "1. Cargar numeros",
            "2. Sumar",
            "3. Restar",
            "4. Multiplicar",
            "5. Dividir",
            "0. Salir"
        };
        int op;
        do {
            Consola.imprimirMenu(opciones, titulo);
            op = Consola.readInt(0, "-->");
            switch (op) {
                case 1: calculadora.cargarDatos();
                    break;
                case 2: System.out.println("Resultado de la suma: " + calculadora.sumar(calculadora.getNum1(), calculadora.getNum2()));
                    break;
                case 3: System.out.println("Resultado de la resta: " + calculadora.restar(calculadora.getNum1(), calculadora.getNum2()));
                    break;
                case 4: System.out.println("Resultado de la multiplicacion: " + calculadora.multiplicar(calculadora.getNum1(), calculadora.getNum2()));
                    break;
                case 5: System.out.println("Resultado de la division: " + calculadora.dividir(calculadora.getNum1(), calculadora.getNum2()));
                    break;
                case 0:
                    break;
            }
        } while (op != 0);
    }
    
    public static void main(String[] args) {
        PruebaCalculadora app = new PruebaCalculadora();
        app.menuDeOpciones();
    }
    
}
