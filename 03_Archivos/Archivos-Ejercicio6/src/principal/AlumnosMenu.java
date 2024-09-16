package principal;

import persistencia.*;
import datos.*;
import utilidades.*;

public class AlumnosMenu {
    private static Archivo file;
    
    public static void cargar() {
        file.abrirParaLeerEscribir();
        try {
            do {
                Registro reg = cargarAlumno();
                file.cargarUnRegistro(reg);
            } while (Consola.continuar());
        } catch (Exception e) {
            System.out.println("Error al cargar el archivo: " + e.getMessage());
            System.exit(1);
        }
        file.cerrarArchivo();
    }
    
    public static Registro cargarAlumno() {
        Alumno inv = new Alumno();
        int codigo;
        do {
            codigo = Consola.readInt(0, "Codigo ");
            if (obtener(codigo) != null) {
                System.out.println("Codigo ya existente.");
                codigo = -1;
            }
        } while (codigo < 0 || codigo > inv.tamArchivo());
        inv.setCod_Alum(codigo);
        inv.cargarDatos();
        Registro reg = new Registro(inv, inv.getCod_Alum());
        return reg;
    }
    
    public static Alumno obtener(int codigo) {
        file.abrirParaLectura();
        file.buscarRegistro(codigo);
        if (file.eof()) {
            return null;
        }
        Registro reg = file.leerRegistro();
        if (!reg.getActivo()) {
            return null;
        }
        Alumno art = (Alumno) reg.getDatos();
        file.cerrarArchivo();

        return art;
    }
    
    public static void baja() {
        Registro reg = new Registro(new Curso(), 0);
        reg.cargarNroOrden();
        file.bajaRegistro(reg);
        Consola.pausa();
    }

    public static void modificar() {
        int codigo = Consola.readInt(0, "Codigo: ");

        file.abrirParaLeerEscribir();
        file.buscarRegistro(codigo);
        if (file.eof()) {
            System.out.println("No existe el registro");
            Consola.pausa();
            return;
        }

        Registro reg = file.leerRegistro(); // Lee el registro existente
        Alumno alum = (Alumno) reg.getDatos();
        
        String titulo = "Menu Modificar";
        String[] opciones = {
            "1. Apellido y Nombre",
            "2. Monto"
        };
        int op = Consola.readInt(0, "-->");
        switch (op){
            case 1:
                alum.setApe_nom(Consola.readString("Nuevo Apellido y Nombre: "));
                break;
            case 2:
                alum.setSaldo(Consola.readFloat(0, "Nuevo monto: "));
                break;
        }
        System.out.println(" ");
        
        reg.setDatos(alum);
        file.cargarUnRegistro(reg); // Sobreescribe el registro ya existente

        file.cerrarArchivo();

        System.out.println("Registro modificado");
    }

    public static void mostrarAlumnoPorCurso(int codigo) {
        String formatoTabla = "%-12s%-12s%-25s%-12s\n";
        System.out.format(formatoTabla, "Codigo", "DNI","Apellido y Nombre", "Saldo");
        System.out.format(formatoTabla, "--------", "--------", "--------","--------");
        file.abrirParaLeerEscribir();
        file.irPrincipioArchivo();
        while (!file.eof()) {
            Registro reg = file.leerRegistro();
            if (reg.getActivo()){
                Alumno aux = (Alumno) reg.getDatos();
                if (aux.getCod_Curso() == codigo){
                    aux.mostarDatos(formatoTabla);
                }
            }
        }
        file.cerrarArchivo();
    }
    
    public static void actualizarSaldo(){
        int codigo = Consola.readInt(0, "Codigo: ");

        file.abrirParaLeerEscribir();
        file.buscarRegistro(codigo);
        if (file.eof()) {
            System.out.println("No existe el registro");
            Consola.pausa();
            return;
        }

        Registro reg = file.leerRegistro();
        Alumno alum = (Alumno) reg.getDatos();
        
        float montoPagado = Consola.readFloat(Integer.MIN_VALUE, "Nuevo monto pagado: ");
        alum.setSaldo(alum.getSaldo() + montoPagado);
        
        reg.setDatos(alum);
        file.cargarUnRegistro(reg);
        file.cerrarArchivo();
    }

    public static void inicializarArchivo() {
        try {
            file = new Archivo("Alumnos.dat", new Alumno());
            if (!file.getFd().exists()){
                file.crearArchivoVacio(new Registro(new Alumno(), 0));
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error al crear los descriptores de archivos: " + e.getMessage());
            System.exit(1);
        }
    }

    public static void menu() {
        int op;
        Registro reg;

        String titulo = "Actualizacion de Alumnos";
        String[] opciones = {
            "1. Altas",
            "2. Bajas",
            "3. Modificaciones",
            "4. Listado de Alumnos por Curso",
            "5. Actualizacion de saldo",
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
                    baja();
                    break;
                case 3:
                    modificar();
                    break;
                case 4:
                    CursoMenu.mostrarPorCurso();
                    break;
                case 5:
                    actualizarSaldo();
                    break;
            }
        } while (op != 0);
    }
}