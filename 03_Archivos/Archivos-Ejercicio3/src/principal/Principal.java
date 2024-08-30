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

    public static void listarLibros() {
        String formatoTabla = "%-10s%-25s%-10s%-10s\n";
        System.out.format(formatoTabla, "CodLibro", "Titulo", "Autor", "Edicion");
        System.out.format(formatoTabla, "--------", "--------", "--------", "--------");
        file.abrirParaLectura();
        file.irPrincipioArchivo();
        while (!file.eof()) {
            Registro reg = file.leerRegistro();
            if (reg.getActivo()) {
                Libro oTemp = (Libro) reg.getDatos();
                oTemp.mostarDatos(formatoTabla);
            }
        }
        file.cerrarArchivo();
    }

    /**
     * Carga un registro de Libro por teclado
     */
    public static Registro leerLibro() {
        Libro tempLibro = new Libro();
        int codigo;
        do {
            codigo = Consola.readInt(0, "Codigo: " );
            if (obtenerLibro(codigo) != null) {
                System.out.println("Codigo ya existente.");
                codigo = -1;
            }
        } while (codigo < 0 || codigo > tempLibro.tamArchivo());
        tempLibro.setCodLib(codigo);
        tempLibro.cargarDatos();
        Registro reg = new Registro(tempLibro, tempLibro.getCodLib());
        return reg;
    }

    public static Libro obtenerLibro(int codigo) {
        file.abrirParaLectura();
        file.buscarRegistro(codigo);
        if (file.eof()) {
            return null;
        }
        Registro reg = file.leerRegistro();
        if (!reg.getActivo()) {
            return null; // El registro no esta activo
        }
        Libro tempLibro = (Libro) reg.getDatos();
        file.cerrarArchivo();

        return tempLibro;
    }

    public static void cargarLibros() {
        file.abrirParaLeerEscribir();
        try {
            do {
                Registro reg = leerLibro();
                file.cargarUnRegistro(reg);
            } while (Consola.continuar());
        } catch (Exception e) {
            System.out.println("Error al cargar el archivo: " + e.getMessage());
            System.exit(1);
        }
        file.cerrarArchivo();
    }

    public static void bajaLibros() {
        Registro reg = new Registro(new Libro(), 0);
        reg.cargarNroOrden();
        file.bajaRegistro(reg);
    }

    public static void modificarLibros() {
        int codigo = Consola.readInt(0, "Codigo a modificar: ");

        file.abrirParaLeerEscribir();
        file.buscarRegistro(codigo);
        if (file.eof()) {
            Consola.prtRed("No existe el codigo ingresado");
            return;
        }

        Registro reg = file.leerRegistro(); // Lee el registro existente
        Libro art = (Libro) reg.getDatos();
        
        System.out.println("Opcion a modificar");
        System.out.println("1. Titulo");
        System.out.println("2. Autor");
        System.out.println("3. Año de Edicion");
        int op = Consola.readInt(0, "-->");
        switch (op){
            case 1:
                art.setTitulo(Consola.readString("Nuevo titulo: "));
                break;
            case 2:
                art.setAutor(Consola.readString("Nuevo autor: "));
            case 3:
                art.setA_Edic(Consola.readInt(0, "Nuevo año de edicion: "));
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
            file = new Archivo(".\\Libros.dat", new Libro());
        } catch (ClassNotFoundException e) {
            System.out.println("Error al crear los descriptores de archivos: " + e.getMessage());
            System.exit(1);
        }
        String titulo = "Libreria";
        String[] opciones = {
            "1. Alta de libros",
            "2. Baja de libros",
            "3. Modificar libro",
            "4. Listar libros",
            "0. Salir"
        };
        int op;
        Registro reg;
        do {
            Consola.imprimirMenu(opciones, titulo);
            op = Consola.readInt(0,"-->");
            switch (op) {
                case 1:
                    cargarLibros();
                    break;
                case 2:
                    bajaLibros();
                    break;
                case 3:
                    modificarLibros();
                    break;
                case 4:
                    listarLibros();
                    break;
            }
        } while (op != 0);
    }
}