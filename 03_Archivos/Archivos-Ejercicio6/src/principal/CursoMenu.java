package principal;

import persistencia.*;
import datos.*;
import utilidades.*;

public class CursoMenu {
    private static Archivo file;

    public static void cargar() {
        file.abrirParaLeerEscribir();
        try {
            do {
                Registro reg = cargarCurso();
                file.cargarUnRegistro(reg);
                reg.mostrarRegistro();
            } while (Consola.continuar());
        } catch (Exception e) {
            System.out.println("Error al cargar el archivo: " + e.getMessage());
            System.exit(1);
        }
        file.cerrarArchivo();
    }
    
    public static Registro cargarCurso() {
        Curso pers = new Curso();
        int codigo;
        do {
            codigo = Consola.readInt(0, "Codigo: ");
            if (obtener(codigo) != null) {
                System.out.println("Codigo ya existente.");
                codigo = -1;
            }
        } while (codigo < 0 || codigo > pers.tamArchivo());
        pers.setCod_C(codigo);
        pers.cargarDatos();
        Registro reg = new Registro(pers, pers.getCod_C());
        return reg;
    }

    public static Curso obtener(int codigo) {
        file.abrirParaLectura();
        file.buscarRegistro(codigo);
            if (file.eof()) {
                return null;
            }
            Registro reg = file.leerRegistro();
                if (!reg.getActivo()) {
                    return null;
                }
        Curso art = (Curso) reg.getDatos();
        file.cerrarArchivo();

        return art;
    }
    
    public static boolean verificarExistencia(int codigo){
        boolean b = false;
        Registro reg;
        file.abrirParaLeerEscribir();
        file.irPrincipioArchivo();
        while (!file.eof()) {
            reg = file.leerRegistro();
            if (reg.getActivo() && reg.getNroOrden() == codigo){
                b = true;
            }
        }
        file.cerrarArchivo();
        return b;
    }
    
    public static void inicializarArchivo() {
        try {
            file = new Archivo("Cursos.dat", new Curso());
            if (!file.getFd().exists()){
                file.crearArchivoVacio(new Registro(new Curso(), 0));
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error al crear los descriptores de archivos: " + e.getMessage());
            System.exit(1);
        }
    }

    public static void mostrar() {
        String formatoTabla = "%-9s%-9s%-9s\n";
        System.out.format(formatoTabla, "Codigo", "Año", "Division");
        System.out.format(formatoTabla, "--------", "--------", "--------");
        file.abrirParaLeerEscribir();
        file.irPrincipioArchivo();
        while (!file.eof()) {
            Registro reg = file.leerRegistro();
            if (reg.getActivo()){
                Curso aux = (Curso) reg.getDatos();
                aux.mostarDatos(formatoTabla);
            }
        }
        file.cerrarArchivo();
    }
    
    public static void mostrarPorCurso() {
        System.out.println(" ");
        file.abrirParaLeerEscribir();
        file.irPrincipioArchivo();
        while (!file.eof()) {
            Registro reg = file.leerRegistro();
            if (reg.getActivo()){
                Curso aux = (Curso) reg.getDatos();
                System.out.println("Curso: (" + aux.getAnio() + " " + aux.getDivision() + ")");
                AlumnosMenu.mostrarAlumnoPorCurso(aux.getCod_C());
                System.out.println(" ");
            }
        }
        file.cerrarArchivo();
    }
    
    public static void menu() {
        int op;
        String titulo = "Control de Cursos";
        String[] opciones = {
            "1. Altas",
            "2. Listado de cursos",
            "0. Salir"
        };
        do {
            Consola.imprimirMenu(opciones, titulo);
            op = Consola.readInt(0, "--> ");
            switch (op) {
                case 1:
                    cargar();
                    break;
                case 2:
                    mostrar();
                    break;
            }
        } while (op != 0);
    }
}