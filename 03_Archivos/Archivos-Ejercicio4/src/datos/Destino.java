package datos;

import utilidades.*;
import java.io.*;
import persistencia.*;

public class Destino implements Grabable {
    
    private int codDes;             // 4   bytes
    private String descripcion;     // 100 bytes

    private static int TAMARCHIVO = 100; // cantidad de registros que tendra el archivo
    private static int TAMAREG = 104;    // cantidad de bytes que tendrá el registro

    public Destino() {
        this.codDes = 0;
        this.descripcion = "";
    }

    public int getCodDes() {
        return codDes;
    }

    public void setCodDes(int codDes) {
        this.codDes = codDes;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public static int getTAMARCHIVO() {
        return TAMARCHIVO;
    }

    public static void setTAMARCHIVO(int TAMARCHIVO) {
        Destino.TAMARCHIVO = TAMARCHIVO;
    }

    public static int getTAMAREG() {
        return TAMAREG;
    }

    public static void setTAMAREG(int TAMAREG) {
        Destino.TAMAREG = TAMAREG;
    }
    
    @Override
    public int tamRegistro() {
        return TAMAREG;
    }

    @Override
    public int tamArchivo() {
        return TAMARCHIVO;
    }

    @Override
    public void grabar(RandomAccessFile file) {
        try {
            file.writeInt(codDes);
            Registro.writeString(file, descripcion, 50);
        } catch (IOException e) {
            System.out.println("Error al grabar el registro: " + e.getMessage());
            System.exit(1);
        }
    }

    @Override
    public void leer(RandomAccessFile file) {
        try {
            codDes = file.readInt();
            descripcion = Registro.readString(file, 50).trim();
        } catch (IOException e) {
            System.out.println("Error al leer el registro: " + e.getMessage());
            System.exit(1);
        }
    }

    @Override
    public boolean equals(Object x) {
        if (x == null) {
            return false;
        }

        Destino file = (Destino) x;
        return (codDes == file.codDes);
    }

    @Override
    public int hashCode() {
        return codDes;
    }

    @Override
    public void mostrarRegistro() {
        System.out.println(String.format("| %-4d | $%-30s |", codDes, descripcion));
    }

    @Override
    public void cargarDatos() {
        cargarDescripcion();
    }
    
    public void cargarDescripcion(){
        setDescripcion(Consola.readString("Descripcion: "));
    }
    
    public void mostarDatos(String formato){
        System.out.println(String.format(formato, codDes, descripcion));
    }
}