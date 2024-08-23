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
        int cantidadArticulos = 0;
        String formatoTabla = "%-10s%-25s%-10s%-10s\n";
        System.out.format(formatoTabla, "CodArt", "Descripcion", "CodRubro", "Precio");
        System.out.format(formatoTabla, "--------", "--------", "--------", "--------");
        file.abrirParaLectura();
        file.irPrincipioArchivo();
        while (!file.eof()) {
            Registro reg = file.leerRegistro();
            if (reg.getActivo()) {
                Articulo oTemp = (Articulo) reg.getDatos();
                oTemp.mostarDatos(formatoTabla);
                cantidadArticulos++;
            }
        }
        System.out.println("Cantidad de articulos: " + cantidadArticulos);
        file.cerrarArchivo();
    }

    /**
     * Carga un registro de Articulo por teclado
     */
    public static Registro leerArticulo() {
        Articulo art = new Articulo();
        int codigo;
        do {
            codigo = Consola.readInt(0, "Codigo: " );
            if (obtenerArticulo(codigo) != null) {
                System.out.println("Codigo ya existente.");
                codigo = -1;
            }
        } while (codigo < 0 || codigo > art.tamArchivo());
        art.setCodArt(codigo);
        art.cargarDatos();
        Registro reg = new Registro(art, art.getCodArt());
        return reg;
    }

    public static Articulo obtenerArticulo(int codigo) {
        file.abrirParaLectura();
        file.buscarRegistro(codigo);
        if (file.eof()) {
            return null;
        }
        Registro reg = file.leerRegistro();
        if (!reg.getActivo()) {
            return null; // El registro no esta activo
        }
        Articulo art = (Articulo) reg.getDatos();
        file.cerrarArchivo();

        return art;
    }

    public static void cargarArticulos() {
        file.abrirParaLeerEscribir();
        try {
            do {
                Registro reg = leerArticulo();
                file.cargarUnRegistro(reg);
            } while (Consola.continuar());
        } catch (Exception e) {
            System.out.println("Error al cargar el archivo: " + e.getMessage());
            System.exit(1);
        }
        file.cerrarArchivo();
    }

    public static void bajaArticulos() {
        Registro reg = new Registro(new Articulo(), 0);
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
        Articulo art = (Articulo) reg.getDatos();
        
        System.out.println("Opcion a modificar");
        System.out.println("1. Descripcion");
        System.out.println("2. Precio");
        int op = Consola.readInt(0, "-->");
        switch (op){
            case 1:
                art.setDescripcion(Consola.readString("Nueva descripcion: "));
                break;
            case 2:
                art.setPrecio(Consola.readFloat(0, "Nuevo precio: "));
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
            file = new Archivo(".\\Articulos.dat", new Articulo());
        } catch (ClassNotFoundException e) {
            System.out.println("Error al crear los descriptores de archivos: " + e.getMessage());
            System.exit(1);
        }
        String titulo = "kiosko JuanitoZaz";
        String[] opciones = {
            "1. Alta de articulos",
            "2. Baja de articulos",
            "3. Modificar articulo",
            "4. Listar articulos",
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