package principal;

import persistencia.*;
import datos.*;
import utilidades.*;

public class PersonalMenu {
    private static Archivo file;
    
    public static void cargar() {
        file.abrirParaLeerEscribir();
        try {
            do {
                Registro reg = cargarPersonal();
                file.cargarUnRegistro(reg);
                reg.mostrarRegistro();
            } while (Consola.continuar());
        } catch (Exception e) {
            System.out.println("Error al cargar el archivo: " + e.getMessage());
            System.exit(1);
        }
        file.cerrarArchivo();
    }

    public static Registro cargarPersonal() {
        Personal pers = new Personal();
        int codigo;
        do {
            codigo = Consola.readInt(0, "Codigo: ");
            if (obtener(codigo) != null) {
                System.out.println("Codigo ya existente.");
                codigo = -1;
            }
        } while (codigo < 0 || codigo > pers.tamArchivo());
        pers.setCodPersonal(codigo);
        pers.cargarDatos();
        Registro reg = new Registro(pers, pers.getCodPersonal());
        return reg;
    }

    public static Personal obtener(int codigo) {
        file.abrirParaLectura();
        file.buscarRegistro(codigo);
            if (file.eof()) {
                return null;
            }
            Registro reg = file.leerRegistro();
                if (!reg.getActivo()) {
                    return null;
                }
        Personal art = (Personal) reg.getDatos();
        file.cerrarArchivo();

        return art;
    }
    
    public static void mostrarApe_Nom(int cod) {
        file.abrirParaLectura();
        file.irPrincipioArchivo();
        while (!file.eof()) {
            Registro reg = file.leerRegistro();
            if (reg.getActivo() && reg.getNroOrden() == cod) {
                Personal aux = (Personal) reg.getDatos();
                System.out.println("Investigador: " + aux.getApe_Nom());
            }
        }
        file.cerrarArchivo();
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
    
    public static void listarPorInvestigador() {
        System.out.println(" ");
        file.abrirParaLectura();
        file.irPrincipioArchivo();
        while (!file.eof()) {
            Registro reg = file.leerRegistro();
            if (reg.getActivo()) {
                Personal aux = (Personal) reg.getDatos();
                System.out.println("Investigador: (" + aux.getCodPersonal() + ", " + aux.getApe_Nom() + ")");
                InventosMenu.listarPorInvestigador(aux.getCodPersonal());
                System.out.println(" ");
            }
        }
        file.cerrarArchivo();
    }
    
    public static void inicializarArchivo() {
        try {
            file = new Archivo("Personal.dat", new Personal());
            if (!file.getFd().exists()){
                file.crearArchivoVacio(new Registro(new Personal(), 0));
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error al crear los descriptores de archivos: " + e.getMessage());
            System.exit(1);
        }
    }

    public static void mostrar() {
        Registro reg;
        file.abrirParaLeerEscribir();
        file.irPrincipioArchivo();
        while (!file.eof()) {
            reg = file.leerRegistro();
            if (reg.getActivo()){
                reg.mostrarRegistro();
            }
        }
        file.cerrarArchivo();
    }
    
}