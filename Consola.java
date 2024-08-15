package utilidades;

import java.util.Arrays;
import java.util.Scanner;

public class Consola {
    
    private static Scanner scanner = new Scanner(System.in);
    
    /**
    * Lee la siguiente línea de entrada del usuario.
    *
    * Utiliza un objeto `Scanner` para leer una línea completa de entrada estándar.
    *
    * @return La línea de texto ingresada por el usuario.
    */
    public static String readLine(){
        return scanner.nextLine();
    }
    
    /**
    * Lee una cadena de texto desde la consola.
    *
    * Muestra un mensaje de solicitud al usuario y lee la entrada estándar.
    * Repite el proceso hasta que el usuario ingrese una cadena no vacía.
    *
    * @param mensaje El mensaje a mostrar al usuario antes de leer la entrada.
    * @return La cadena de texto ingresada por el usuario.
    */
    public static String readString(String mensaje){
        String sTemp = "";
        do {
            System.out.print(mensaje);
            sTemp = readLine();
        } while (sTemp.equals(""));
        return sTemp;
    }
    
    /**
    * Lee un número entero (Int) desde la consola.
    *
    * Muestra un mensaje de solicitud al usuario y lee la entrada estándar.
    * Verifica que el número ingresado sea un entero válido y mayor o igual al valor mínimo.
    * Repite el proceso hasta que se ingrese un valor válido.
    *
    * @param min El valor mínimo permitido para el número entero.
    * @param mensaje El mensaje a mostrar al usuario antes de leer la entrada.
    * @return El número entero ingresado por el usuario.
    */
    public static int readInt(int min, String mensaje){
        int iTemp = 0;
        while (true){
            try {
                do {
                    System.out.print(mensaje);
                    iTemp = Integer.parseInt(readLine());
                } while (iTemp < min);
                break;
            } catch (NumberFormatException e){
                System.out.println("Error: " + e.getMessage());
            }
        }
        return iTemp;
    }
    
    /**
    * Lee un número largo (Long) desde la consola.
    *
    * Muestra un mensaje de solicitud al usuario y lee la entrada estándar.
    * Verifica que el número ingresado sea un número largo válido y mayor o igual al valor mínimo.
    * Repite el proceso hasta que se ingrese un valor válido.
    *
    * @param min El valor mínimo permitido para el número largo.
    * @param mensaje El mensaje a mostrar al usuario antes de leer la entrada.
    * @return El número largo ingresado por el usuario.
    */
    public static long readLong(int min, String mensaje){
        long iTemp = 0;
        while (true){
            try {
                do {
                    System.out.print(mensaje);
                    iTemp = Long.parseLong(readLine());
                } while (iTemp < min);
                break;
            } catch (NumberFormatException e){
                System.out.println("Error: " + e.getMessage());
            }
        }
        return iTemp;
    }
    
    /**
    * Lee un número flotante (Float) desde la consola.
    *
    * Muestra un mensaje de solicitud al usuario y lee la entrada estándar.
    * Verifica que el número ingresado sea un número flotante válido y mayor o igual al valor mínimo.
    * Repite el proceso hasta que se ingrese un valor válido.
    *
    * @param min El valor mínimo permitido para el número flotante.
    * @param mensaje El mensaje a mostrar al usuario antes de leer la entrada.
    * @return El número flotante ingresado por el usuario.
    */
    public static float readFloat(int min, String mensaje){
        float iTemp = 0;
        while (true){
            try {
                do {
                    System.out.print(mensaje);
                    iTemp = Float.parseFloat(readLine());
                } while (iTemp < min);
                break;
            } catch (NumberFormatException e){
                System.out.println("Error: " + e.getMessage());
            }
        }
        return iTemp;
    }
    
    /**
    * Lee un único carácter (Char) desde la consola.
    *
    * Muestra un mensaje de solicitud al usuario y lee la entrada estándar.
    * Verifica que la entrada sea exactamente un carácter y lo devuelve.
    * Repite el proceso hasta que se ingrese un carácter válido.
    *
    * @param mensaje El mensaje a mostrar al usuario antes de leer la entrada.
    * @return El carácter ingresado por el usuario.
    */
    public static char readChar(String mensaje) {
        char cTemp = ' ';
        while (true) {
            try {
                System.out.print(mensaje);
                String input = readLine();
                if (input.length() == 1) {
                    cTemp = input.charAt(0);
                    break;
                } else {
                    System.out.println("Debes ingresar un único carácter.");
                }
            } catch (Exception e) {
                System.out.println("Error: Ocurrio un error al leer el caracter (Char)" + e.getMessage());
            }
        }
        return cTemp;
    }
    
    /**
    * Lee un único carácter desde la consola.
    *
    * Lee la entrada estándar y verifica que sea exactamente un carácter.
    * Repite el proceso hasta que se ingrese un carácter válido.
    *
    * @return El carácter ingresado por el usuario.
    */
    public static char readChar() {
        char cTemp = ' ';
        while (true) {
            try {
                String input = readLine();
                if (input.length() == 1) {
                    cTemp = input.charAt(0);
                    break;
                } else {
                    System.out.println("Debes ingresar un único carácter.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return cTemp;
    }
    
    /**
    * Imprime un menú formateado en la consola.
    *
    * Recibe un arreglo de cadenas que representan las opciones del menú y un título personalizado.
    * El menú se presenta con el título centrado, opciones alineadas a la izquierda, y bordes decorativos utilizando el caracter '='.
    *
    * @param opciones Un arreglo de cadenas que representan las opciones del menú.
    * @param titulo El título que se mostrará en la cabecera del menú.
    */
    public static void imprimirMenu(String[] opciones, String titulo) {
        int maxLongitud = Arrays.stream(opciones)
                                .mapToInt(String::length)
                                .max()
                                .orElse(0);
        int longitudTitulo = titulo.length();
        int espaciosIzquierda = (maxLongitud - longitudTitulo) / 2;
        int espaciosDerecha = maxLongitud - longitudTitulo - espaciosIzquierda;
        System.out.println("=".repeat(maxLongitud + 4));
        System.out.printf("| %" + espaciosIzquierda + "s%s%" + espaciosDerecha + "s |%n", "", titulo, "");
        System.out.println("=".repeat(maxLongitud + 4));
        for (String opcion : opciones) {
            System.out.printf("| %-" + maxLongitud + "s |%n", opcion);
        }
        System.out.println("=".repeat(maxLongitud + 4));
    }
    
    /**
    * Pregunta al usuario si desea continuar con una operación.
    *
    * Muestra un mensaje al usuario y lee la entrada estándar.
    * Verifica que la respuesta sea 's' o 'n' (en minúscula).
    * Repite el proceso hasta que se ingrese una respuesta válida.
    *
    * @return `true` si el usuario desea continuar, `false` en caso contrario.
    */
    public static boolean continuar() {
        boolean continuar = false;
        char respuesta = ' ';
        do {
            System.out.print("¿Desea continuar? (S/N): ");
            respuesta = Character.toLowerCase(readLine().charAt(0));
        } while (respuesta != 's' && respuesta != 'n');
            continuar = respuesta == 's';
        return continuar;
    }
    
    /**
    * Pausa la ejecución del programa hasta que el usuario presione Enter.
    *
    * Muestra un mensaje indicando al usuario que pulse Enter para continuar y espera
    * la entrada del usuario.
    */
    public static void pausa() {
        System.out.println("Pulse ENTER para continuar");
        readLine();
    }
    
    /**
    * Imprime un mensaje en color rojo en la consola.
    *
    * Utiliza códigos de escape ANSI para establecer el color de fondo rojo
    * y reiniciar el color al finalizar.
    *
    * @param mensaje El mensaje a imprimir en color rojo.
    */
    public static void prtRed(String mensaje){
        System.out.print("\u001B[41m" + mensaje + "\u001B[0m\n");
    }
    
    /**
    * Imprime un mensaje en color amarillo en la consola.
    *
    * Utiliza códigos de escape ANSI para establecer el color de fondo rojo
    * y reiniciar el color al finalizar.
    *
    * @param mensaje El mensaje a imprimir en color rojo.
    */
    public static void prtYellow(String mensaje){
        System.out.print("\u001B[33m" + mensaje + "\u001B[0m\n");
    }
    
    /**
    * Imprime un mensaje en color verde en la consola.
    *
    * Utiliza códigos de escape ANSI para establecer el color de fondo rojo
    * y reiniciar el color al finalizar.
    *
    * @param mensaje El mensaje a imprimir en color rojo.
    */
    public static void prtGreen(String mensaje){
        System.out.print("\u001B[32m" + mensaje + "\u001B[0m\n");
    }
    
    /**
    * Imprime un mensaje en color azul en la consola.
    *
    * Utiliza códigos de escape ANSI para establecer el color de fondo rojo
    * y reiniciar el color al finalizar.
    *
    * @param mensaje El mensaje a imprimir en color rojo.
    */
    public static void prtBlue(String mensaje){
        System.out.print("\u001B[44m" + mensaje + "\u001B[0m\n");
    }
}
