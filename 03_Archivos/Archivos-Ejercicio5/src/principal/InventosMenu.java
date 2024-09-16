package principal;

import persistencia.*;
import datos.*;
import utilidades.*;

public class InventosMenu {
    private static Archivo file;
    
    public static void altas() {
        file.abrirParaLeerEscribir();
        try {
            do {
                Registro reg = cargarInvento();
                file.cargarUnRegistro(reg);
            } while (Consola.continuar());
        } catch (Exception e) {
            System.out.println("Error al cargar el archivo: " + e.getMessage());
            System.exit(1);
        }
        file.cerrarArchivo();
    }
    
    public static Registro cargarInvento() {
        Invento inv = new Invento();
        int codigo = 0;
        do {
            codigo = Consola.readInt(0, "Codigo: ");
            if (obtener(codigo) != null) {
                System.out.println("Codigo ya existente.");
                codigo = -1;
            }
        } while (codigo < 0 || codigo > inv.tamArchivo());
        inv.setCod_Inv(codigo);
        inv.cargarDatos();
        Registro reg = new Registro(inv, inv.getCod_Inv());
        return reg;
    }
    
    public static Invento obtener(int codigo) {
        file.abrirParaLectura();
        file.buscarRegistro(codigo);
        if (file.eof()) {
            return null;
        }
        Registro reg = file.leerRegistro();
        if (!reg.getActivo()) {
            return null;
        }
        Invento inv = (Invento) reg.getDatos();
        file.cerrarArchivo();

        return inv;
    }
    
    public static void bajas() {
        Registro reg = new Registro(new Invento(), 0);
        reg.cargarNroOrden();
        file.bajaRegistro(reg);
    }

    public static void modificar() {
        int codigo = Consola.readInt(0, "Codigo a modificar: ");

        file.abrirParaLeerEscribir();
        file.buscarRegistro(codigo);
        if (file.eof()) {
            System.out.println("No existe el registro");
            Consola.pausa();
            return;
        }

        Registro reg = file.leerRegistro(); // Lee el registro existente
        Invento inv = (Invento) reg.getDatos();
        
        String titulo = "Menu Modificar";
        String[] opciones = {
            "1. Monto",
            "2. Patente"
        };
        Consola.imprimirMenu(opciones, titulo);
        int op = Consola.readInt(0, "-->");
        switch (op){
            case 1:
                inv.setMonto(Consola.readFloat(0, "Nuevo monto: "));
                break;
            case 2:
                inv.setPatente(Consola.readInt(0, "Nueva patente: "));
                break;
        }
        System.out.println(" ");
        
        reg.setDatos(inv);
        file.cargarUnRegistro(reg); // Sobreescribe el registro ya existente

        file.cerrarArchivo();

        System.out.println("Registro modificado");
        Consola.pausa();
    }
    
    public static int cantidad() {
        file.abrirParaLectura();
        file.irPrincipioArchivo();
        int contador = 0;
        while (!file.eof()) {
            Registro reg = file.leerRegistro();
            if (reg.getActivo()) {
                contador++;
            }
        }
        file.cerrarArchivo();
        return contador;
    }

    public static void listarInventos() {
        String formatoTabla = "%-12s%-25s%-12s%-12s\n";
        System.out.format(formatoTabla, "Codigo", "Denominacion", "Monto", "Patente");
        System.out.format(formatoTabla, "----------", "----------------", "----------", "----------");
        file.abrirParaLectura();
        file.irPrincipioArchivo();
        while (!file.eof()) {
            Registro reg = file.leerRegistro();
            if (reg.getActivo()) {
                Invento aux = (Invento) reg.getDatos();
                aux.mostarDatos(formatoTabla);
            }
        }
        file.cerrarArchivo();
    }
    
    public static void listarPorInvestigador(int codigo){
        String formatoTabla = "%-12s%-25s%-12s%-12s\n";
        System.out.format(formatoTabla, "Codigo", "Denominacion", "Monto", "Patente");
        System.out.format(formatoTabla, "----------", "----------------", "----------", "----------");
        file.abrirParaLectura();
        file.irPrincipioArchivo();
        while (!file.eof()) {
            Registro reg = file.leerRegistro();
            if (reg.getActivo()) {
                Invento aux = (Invento) reg.getDatos();
                if (aux.getCodPersonal() == codigo){
                    aux.mostarDatos(formatoTabla);
                }
            }
        }
        file.cerrarArchivo();
    }
    
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

    public static void inicializarArchivo() {
        try {
            file = new Archivo("Inventos.dat", new Invento());
            if (!file.getFd().exists()){
                file.crearArchivoVacio(new Registro(new Invento(), 0));
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error al crear los descriptores de archivos: " + e.getMessage());
            System.exit(1);
        }
    }

    public static void menu() {
        int op;
        Registro reg;

        String titulo = "Menu (Inventos)";
        String[] opciones = {
            "1. Altas",
            "2. Bajas",
            "3. Modificaciones",
            "4. Listado de inventos",
            "5. Listado de inventos por investigador",
            "0. Salir"
        };
        do {
            Consola.imprimirMenu(opciones, titulo);
            op = Consola.readInt(0, "--> ");
            switch (op) {
                case 1:
                    altas();
                    break;
                case 2:
                    bajas();
                    break;
                case 3:
                    modificar();
                    break;
                case 4:
                    listarInventos();
                    break;
                case 5:
                    PersonalMenu.listarPorInvestigador();
                    break;
            }
        } while (op != 0);
    }
}