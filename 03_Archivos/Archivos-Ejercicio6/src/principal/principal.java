package principal;

import utilidades.Consola;

public class principal {
    
//    Long: 8 bytes
//    Integer: 4 bytes
//    Short: 2 bytes
//    Byte: 1 byte
//    Double: 8 bytes
//    Float: 4 bytes
//    Boolean: 1 byte
//    Char: 2 bytes
//    String: 2 bytes por cada caracter
    
    public static void main(String[] args) {
        int op;

        CursoMenu.inicializarArchivo();
        AlumnosMenu.inicializarArchivo();

        String titulo = "Menu Principal";
        String[] opciones = {
            "1. Control de cursos",
            "2. Actualizacion de alumnos",
            "0. Salir"
        };
        do {
            Consola.imprimirMenu(opciones, titulo);
            op = Consola.readInt(0, "--> ");
            switch (op) {
                case 1:
                    CursoMenu.menu();
                    break;
                case 2:
                    AlumnosMenu.menu();
                    break;
            }
        } while (op != 0);
    }
}
