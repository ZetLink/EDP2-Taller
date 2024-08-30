package principal;

import persistencia.*;
import datos.*;
import utilidades.*;

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
        String formatoTabla = "%-10s%-25s%-10s%-15s%-15s%-10s\n";
        System.out.format(formatoTabla, "CodM", "Descripcion", "Precio", "CantStock", "FechaVenc", "Tipo");
        System.out.format(formatoTabla, "--------", "--------", "--------", "--------","--------", "--------");
        file.abrirParaLectura();
        file.irPrincipioArchivo();
        while (!file.eof()) {
            Registro reg = file.leerRegistro();
            if (reg.getActivo()) {
                Medicamento oTemp = (Medicamento) reg.getDatos();
                oTemp.mostarDatos(formatoTabla);
            }
        }
        file.cerrarArchivo();
    }

    public static Registro Medicamento() {
        Medicamento medi = new Medicamento();
        int codigo;
        do {
            codigo = Consola.readInt(0, "Codigo: " );
            if (obtenerMedicamento(codigo) != null) {
                System.out.println("Codigo ya existente.");
                codigo = -1;
            }
        } while (codigo < 0 || codigo > medi.tamArchivo());
        medi.setCodM(codigo);
        medi.cargarDatos();
        Registro reg = new Registro(medi, medi.getCodM());
        return reg;
    }

    public static Medicamento obtenerMedicamento(int codigo) {
        file.abrirParaLectura();
        file.buscarRegistro(codigo);
        if (file.eof()) {
            return null;
        }
        Registro reg = file.leerRegistro();
        if (!reg.getActivo()) {
            return null; // El registro no esta activo
        }
        Medicamento medi = (Medicamento) reg.getDatos();
        file.cerrarArchivo();

        return medi;
    }

    public static void cargarMedicamentos() {
        file.abrirParaLeerEscribir();
        try {
            do {
                Registro reg = Medicamento();
                file.cargarUnRegistro(reg);
            } while (Consola.continuar());
        } catch (Exception e) {
            System.out.println("Error al cargar el archivo: " + e.getMessage());
            System.exit(1);
        }
        file.cerrarArchivo();
    }

    public static void bajaMedicamentos() {
        Registro reg = new Registro(new Medicamento(), 0);
        reg.cargarNroOrden();
        file.bajaRegistro(reg);
    }

    public static void modificarMedicamentos() {
        int codigo = Consola.readInt(0, "Codigo a modificar: ");

        file.abrirParaLeerEscribir();
        file.buscarRegistro(codigo);
        if (file.eof()) {
            Consola.prtRed("No existe el codigo ingresado");
            return;
        }

        Registro reg = file.leerRegistro(); // Lee el registro existente
        Medicamento medi = (Medicamento) reg.getDatos();
        
        System.out.println("Opcion a modificar");
        System.out.println("1. Descripcion");
        System.out.println("2. Precio");
        System.out.println("3. Cantidad en Stock");
        System.out.println("4. Fecha de Vencimiento");
        System.out.println("5. Tipo");
        int op = Consola.readInt(0, "-->");
        switch (op){
            case 1:
                medi.setDescripcion(Consola.readString("Nueva descripcion: "));
                break;
            case 2:
                medi.setPrecio(Consola.readFloat(0, "Nuevo precio: "));
                break;
            case 3:
                medi.setCant_S(Consola.readInt(0, "Nueva cantidad en stock: "));
                break;
            case 4:
                medi.setFecha_Venc(modificarFecha());
                break;
            case 5:
                medi.setTipo(modificarTipo());
                break;
        }
        System.out.println(" ");
        
        reg.setDatos(medi);
        file.cargarUnRegistro(reg); // Sobreescribe el registro ya existente

        file.cerrarArchivo();

        System.out.println("Registro modificado");
    }
    
    public static String modificarFecha(){
        Fecha fechaTemp = new Fecha();
        fechaTemp.cargarFecha();
        return fechaTemp.obtenerFechaComoString();
    }
    
    public static char modificarTipo(){
        char cTemp = ' ';
        do{
            cTemp = Consola.readChar("Tipo (P/J): ");
        } while (Character.compare(cTemp, 'P') != 0 && Character.compare(cTemp, 'J') != 0);
        return cTemp;
    }
    
    public static void consultarStock(){
        int codigo = Consola.readInt(0, "Codigo del medicamento: ");
        file.abrirParaLeerEscribir();
        file.buscarRegistro(codigo);
        if (file.eof()) {
            Consola.prtRed("No existe el codigo ingresado");
            return;
        }
        Registro reg = file.leerRegistro(); // Lee el registro existente
        Medicamento medi = (Medicamento) reg.getDatos();
        System.out.println("Cantidad en stock de " + medi.getDescripcion() + ":" + medi.getCant_S());
        file.cerrarArchivo();
    }

    public static void main(String[] args) {
        try {
            file = new Archivo(".\\Medicamentos.dat", new Medicamento());
        } catch (ClassNotFoundException e) {
            System.out.println("Error al crear los descriptores de archivos: " + e.getMessage());
            System.exit(1);
        }
        String titulo = "Medicamentos";
        String[] opciones = {
            "1. Alta de medicamentos",
            "2. Baja de medicamentos",
            "3. Modificar medicamento",
            "4. Stock de medicamento",
            "5. Listar medicamentos",
            "0. Salir"
        };
        int op;
        Registro reg;
        do {
            Consola.imprimirMenu(opciones, titulo);
            op = Consola.readInt(0,"-->");
            switch (op) {
                case 1:
                    cargarMedicamentos();
                    break;
                case 2:
                    bajaMedicamentos();
                    break;
                case 3:
                    modificarMedicamentos();
                    break;
                case 4:
                    consultarStock();
                    break;
                case 5:
                    listar();
                    break;
            }
        } while (op != 0);
    }
}