package principal;

import persistencia.*;
import datos.*;
import utilidades.Consola;

public class Principal {

    public static void main(String[] args) {
        PrincipalViajes appViajes = new PrincipalViajes();
        PrincipalDestinos appDestinos = new PrincipalDestinos();
        String titulo = "Menú Principal";
        String[] opciones = {
            "1. Actualización de Viajes",
            "2. Actualización de Destinos",
            "0. Salir"
        };
        int op;
        do {
            Consola.imprimirMenu(opciones, titulo);
            op = Consola.readInt(0,"-->");
            switch (op) {
                case 1:
                    appViajes.menu();
                    break;
                case 2:
                    appDestinos.menu();
                    break;
            }
        } while (op != 0);
    }
}