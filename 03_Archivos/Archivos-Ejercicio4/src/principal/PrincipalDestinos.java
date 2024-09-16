package principal;

import persistencia.*;
import datos.Destino;
import utilidades.*;

public class PrincipalDestinos {
    private static Archivo file;
    
    public static void cargarDestinos() {
        file.abrirParaLeerEscribir();
        try {
            do {
                Registro reg = leerDestino();
                file.cargarUnRegistro(reg);
            } while (Consola.continuar());
        } catch (Exception e) {
            System.out.println("Error al cargar el archivo destinos: " + e.getMessage());
            System.exit(1);
        }
        file.cerrarArchivo();
    }
    
    public static Registro leerDestino() {
        Destino tempDestino = new Destino();
        int codigo;
        do {
            codigo = Consola.readInt(0, "Codigo: " );
            if (obtenerDestino(codigo) != null) {
                System.out.println("Codigo ya existente.");
                codigo = -1;
            }
        } while (codigo < 0 || codigo > tempDestino.tamArchivo());
        tempDestino.setCodDes(codigo);
        tempDestino.cargarDatos();
        Registro reg = new Registro(tempDestino, tempDestino.getCodDes());
        return reg;
    }
    
    public static Destino obtenerDestino(int codigo) {
        file.abrirParaLectura();
        file.buscarRegistro(codigo);
        if (file.eof()) {
            return null;
        }
        Registro reg = file.leerRegistro();
        if (!reg.getActivo()) {
            return null; // El registro no esta activo
        }
        Destino tempLibro = (Destino) reg.getDatos();
        file.cerrarArchivo();
        return tempLibro;
    }
    
    public static void bajaDestino() {
        Registro reg = new Registro(new Destino(), 0);
        reg.cargarNroOrden();
        file.bajaRegistro(reg);
    }
    
    public static void modificarDestino() {
        int codigo = Consola.readInt(0, "Codigo a modificar: ");

        file.abrirParaLeerEscribir();
        file.buscarRegistro(codigo);
        if (file.eof()) {
            Consola.prtRed("No existe el codigo ingresado");
            return;
        }

        Registro reg = file.leerRegistro(); // Lee el registro existente
        Destino tempDestino = (Destino) reg.getDatos();
        
        System.out.println("Opcion a modificar");
        System.out.println("1. Descripcion");
        int op = Consola.readInt(0, "-->");
        switch (op){
            case 1:
                tempDestino.setDescripcion(Consola.readString("Descripcion: "));
                break;
        }
        System.out.println(" ");
        reg.setDatos(tempDestino);
        file.cargarUnRegistro(reg); // Sobreescribe el registro ya existente
        file.cerrarArchivo();
        System.out.println("Registro modificado");
    }
    
    public static void listarDestinos() {
        String formatoTabla = "%-10s%-25s\n";
        System.out.format(formatoTabla, "CodDes", "Descipcion");
        System.out.format(formatoTabla, "--------", "--------");
        file.abrirParaLectura();
        file.irPrincipioArchivo();
        while (!file.eof()) {
            Registro reg = file.leerRegistro();
            if (reg.getActivo()) {
                Destino oTemp = (Destino) reg.getDatos();
                oTemp.mostarDatos(formatoTabla);
            }
        }
        file.cerrarArchivo();
    }
    
    public Destino obtenerCodDestino(int codigo) {
        file.abrirParaLectura();
        file.buscarRegistro(codigo);
        if (file.eof()) {
            return null;
        }
        Registro reg = file.leerRegistro();
        if (!reg.getActivo()) {
            return null; // El registro no esta activo
        }
        Destino tempDestino = (Destino) reg.getDatos();
        file.cerrarArchivo();
        return tempDestino;
    }
    
    public void menu(){
        try {
            file = new Archivo(".\\Destinos.dat", new Destino());
        } catch (ClassNotFoundException e) {
            System.out.println("Error al crear los descriptores de archivos: " + e.getMessage());
            System.exit(1);
        }
        String titulo = "Menu Destinos";
        String[] opciones = {
            "1. Alta de destinos",
            "2. Baja de destinos",
            "3. Modificar destino",
            "4. Listar destinos",
            "0. Salir"
        };
        int op;
        Registro reg;
        do {
            Consola.imprimirMenu(opciones, titulo);
            op = Consola.readInt(0,"-->");
            switch (op) {
                case 1:
                    cargarDestinos();
                    break;
                case 2:
                    bajaDestino();
                    break;
                case 3:
                    modificarDestino();
                    break;
                case 4:
                    listarDestinos();
                    break;
            }
        } while (op != 0);
    }
}
