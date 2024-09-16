package principal;

import utilidades.Consola;

public class principal {
    
    public static void main(String[] args) {
        int op;

        PersonalMenu.inicializarArchivo();
        InventosMenu.inicializarArchivo();

        
        String titulo = "Menu Principal";
        String[] opciones = {
            "1. Carga de Investigador",
            "2. Actualizacion de Inventos",
            "0. Salir"
        };
        do {
            Consola.imprimirMenu(opciones, titulo);
            op = Consola.readInt(0, "--> ");
            switch (op) {
                case 1:
                    PersonalMenu.cargar();
                    break;
                case 2:
                    InventosMenu.menu();
                    break;
                case 3:
                    PersonalMenu.mostrar();
                    break;
            }
        } while (op != 0);
    }
}
