package principal;

import persistencia.*;
import datos.*;
import utilidades.Consola;

public class Principal {
    private static Archivo file;
            
    public static void mostrarTodo() {
        Registro reg;
        file.abrirParaLeerEscribir();
        file.irPrincipioArchivo();
        while (!file.eof()) {
            reg = file.leerRegistro();
            reg.mostrarRegistro();
        }
        file.cerrarArchivo();
    }

    public static void listar() {
        String formatoTabla = "%-15s%-15s%-10s\n";
        System.out.format(formatoTabla, "CodPersona", "Nombre", "Edad");
        System.out.format(formatoTabla, "------------", "--------", "--------");
        file.abrirParaLectura();
        file.irPrincipioArchivo();
        while (!file.eof()) {
            Registro reg = file.leerRegistro();
            if (reg.getActivo()) {
                Persona oTemp = (Persona) reg.getDatos();
                oTemp.mostarDatos(formatoTabla);
            }
        }
        file.cerrarArchivo();
    }

    /**
     * Carga un registro de Articulo por teclado
     */
    public static Registro leerPersona() {
        Persona art = new Persona();
        int codigo;
        do {
            codigo = Consola.readInt(0, "Codigo: " );
            if (obtenerArticulo(codigo) != null) {
                System.out.println("Codigo ya existente.");
                codigo = -1;
            }
        } while (codigo < 0 || codigo > art.tamArchivo());
        art.setCodPersona(codigo);
        art.cargarDatos();
        Registro reg = new Registro(art, art.getCodPersona());
        return reg;
    }

    public static Persona obtenerArticulo(int codigo) {
        file.abrirParaLectura();
        file.buscarRegistro(codigo);
        if (file.eof()) {
            return null;
        }
        Registro reg = file.leerRegistro();
        if (!reg.getActivo()) {
            return null; // El registro no esta activo
        }
        Persona art = (Persona) reg.getDatos();
        file.cerrarArchivo();

        return art;
    }

    public static void cargarArticulos() {
        file.abrirParaLeerEscribir();
        try {
            do {
                Registro reg = leerPersona();
                file.cargarUnRegistro(reg);
            } while (Consola.continuar());
        } catch (Exception e) {
            System.out.println("Error al cargar el archivo: " + e.getMessage());
            System.exit(1);
        }
        file.cerrarArchivo();
    }

    public static void bajaArticulos() {
        Registro reg = new Registro(new Persona(), 0);
        reg.cargarNroOrden();
        file.bajaRegistro(reg);
    }

    public static void modificarArticulo() {
        int codigo = Consola.readInt(0, "Codigo a modificar: ");

        file.abrirParaLeerEscribir();
        file.buscarRegistro(codigo);
        if (file.eof()) {
            Consola.prtRed("No existe el codigo ingresado");
            return;
        }

        Registro reg = file.leerRegistro(); // Lee el registro existente
        Persona art = (Persona) reg.getDatos();
        
        String titulo = "Menu";
        String[] opciones = {
            "1. Nombre",
            "2. Edad"
        };
        Consola.imprimirMenu(opciones, titulo);
        int op = Consola.readInt(0, "-->");
        switch (op){
            case 1:
                art.setNombre(Consola.readString("Nuevo nombre: "));
                break;
            case 2:
                art.setEdad(Consola.readInt(0, "Nueva edad: "));
                break;
        }
        System.out.println(" ");
        
        reg.setDatos(art);
        file.cargarUnRegistro(reg); // Sobreescribe el registro ya existente

        file.cerrarArchivo();

        System.out.println("Registro modificado");
    }

    public static void main(String[] args) {
        try {
            file = new Archivo(".\\Articulos.dat", new Persona());
        } catch (ClassNotFoundException e) {
            System.out.println("Error al crear los descriptores de archivos: " + e.getMessage());
            System.exit(1);
        }
        String titulo = "Menu de Opciones";
        String[] opciones = {
            "1. Alta de personas",
            "2. Baja de personas",
            "3. Modificar persona",
            "4. Listar personas",
            "0. Salir"
        };
        int op;
        Registro reg;
        do {
            Consola.imprimirMenu(opciones, titulo);
            op = Consola.readInt(0,"-->");
            switch (op) {
                case 1:
                    cargarArticulos();
                    break;
                case 2:
                    bajaArticulos();
                    break;
                case 3:
                    modificarArticulo();
                    break;
                case 4:
                    listar();
                    break;
            }
        } while (op != 0);
    }
}