package principal;

import persistencia.*;
import datos.Viaje;
import utilidades.*;

public class PrincipalViajes {
    
    private static Archivo file;
    private static PrincipalDestinos tempPD;
    
    public static void cargarViajes() {
        file.abrirParaLeerEscribir();
        try {
            do {
                Registro reg = leerViaje();
                file.cargarUnRegistro(reg);
            } while (Consola.continuar());
        } catch (Exception e) {
            System.out.println("Error al cargar el archivo viajes: " + e.getMessage());
            System.exit(1);
        }
        file.cerrarArchivo();
    }
    
    public static Registro leerViaje() {
        Viaje tempViaje = new Viaje();
        int codigoV;
        int codigoD;
        do {
            codigoV = Consola.readInt(0, "Codigo de viaje: " );
            codigoD = Consola.readInt(0, "Codigo de destino: ");
            if (obtenerViaje(codigoV) != null) {
                System.out.println("Codigo ya existente.");
                codigoV = -1;
                if (tempPD.obtenerCodDestino(codigoD) != null) {
                    System.out.println("Codigo de destino inexistente.");
                    codigoD = -1;
                }
            }
        } while (codigoV < 0 || codigoV > tempViaje.tamArchivo() || codigoD < 0);
        tempViaje.setCodDes(codigoV);
        tempViaje.setCodDes(codigoD);
        tempViaje.cargarDatos();
        Registro reg = new Registro(tempViaje, tempViaje.getCodDes());
        return reg;
    }
    
    public static Viaje obtenerViaje(int codigo) {
        file.abrirParaLectura();
        file.buscarRegistro(codigo);
        if (file.eof()) {
            return null;
        }
        Registro reg = file.leerRegistro();
        if (!reg.getActivo()) {
            return null; // El registro no esta activo
        }
        Viaje tempLibro = (Viaje) reg.getDatos();
        file.cerrarArchivo();
        return tempLibro;
    }
    
    public static void bajaViaje() {
        Registro reg = new Registro(new Viaje(), 0);
        reg.cargarNroOrden();
        file.bajaRegistro(reg);
    }
    
    public static void modificarViaje() {
        int codigo = Consola.readInt(0, "Codigo a modificar: ");

        file.abrirParaLeerEscribir();
        file.buscarRegistro(codigo);
        if (file.eof()) {
            Consola.prtRed("No existe el codigo ingresado");
            return;
        }

        Registro reg = file.leerRegistro(); // Lee el registro existente
        Viaje tempViaje = (Viaje) reg.getDatos();
        
        System.out.println("Opcion a modificar");
        System.out.println("1. Fecha");
        System.out.println("2. Hora");
        System.out.println("3. Tarifa");
        System.out.println("4. Cantidad de dias");
        int op = Consola.readInt(0, "-->");
        switch (op){
            case 1:
                tempViaje.setFecha(modificarFecha());
                break;
            case 2:
                tempViaje.setHora(Consola.readInt(0, "Nueva hora: "));
                break;
            case 3:
                tempViaje.setTarifa(Consola.readFloat(0, "Nueva tarifa: "));
                break;
            case 4:
                tempViaje.setCantDias(Consola.readInt(0, "Nueva cantidad de dias: "));
                break;
        }
        System.out.println(" ");
        reg.setDatos(tempViaje);
        file.cargarUnRegistro(reg); // Sobreescribe el registro ya existente
        file.cerrarArchivo();
        System.out.println("Registro modificado");
    }
    
    private static String modificarFecha(){
        Fecha fechaTemp = new Fecha();
        fechaTemp.cargarFecha();
        return fechaTemp.obtenerFechaComoString();
    }
    
    public static void listarViajes() {
        String formatoTabla = "%-8s%-8s%-12s%-8s%-10s%-6s\n";
        System.out.format(formatoTabla, "CodV", "CodDes", "Fecha", "Hora", "Tarifa", "CantDias");
        System.out.format(formatoTabla, "--------", "--------", "--------", "--------", "--------", "--------");
        file.abrirParaLectura();
        file.irPrincipioArchivo();
        while (!file.eof()) {
            Registro reg = file.leerRegistro();
            if (reg.getActivo()) {
                Viaje oTemp = (Viaje) reg.getDatos();
                oTemp.mostarDatos(formatoTabla);
            }
        }
        file.cerrarArchivo();
    }
    
    public void menu(){
        try {
            file = new Archivo(".\\Viajes.dat", new Viaje());
        } catch (ClassNotFoundException e) {
            System.out.println("Error al crear los descriptores de archivos: " + e.getMessage());
            System.exit(1);
        }
        String titulo = "Menu Viajes";
        String[] opciones = {
            "1. Alta de viajes",
            "2. Baja de viaje",
            "3. Modificar viaje",
            "4. Listar viajes",
            "0. Salir"
        };
        int op;
        Registro reg;
        do {
            Consola.imprimirMenu(opciones, titulo);
            op = Consola.readInt(0,"-->");
            switch (op) {
                case 1:
                    cargarViajes();
                    break;
                case 2:
                    bajaViaje();
                    break;
                case 3:
                    modificarViaje();
                    break;
                case 4:
                    listarViajes();
                    break;
            }
        } while (op != 0);
    }
}
