package datos;

import utilidades.Consola;
import java.io.*;
import persistencia.*;

public class Articulo implements Grabable {
    
    private int codArt;             // 4   bytes
    private String descripcion;     // 100 bytes
    private int codRubro;           // 4   bytes
    private float precio;           // 4   bytes

    private static int TAMARCHIVO = 100; // cantidad de registros que tendra el archivo
    private static int TAMAREG = 112;    // cantidad de bytes que tendrá el registro

    public Articulo() {
        this.codArt = 0;
        this.descripcion = " ";
        this.codRubro = 0;
        this.precio = 0;
    }

    public int getCodArt() {
        return codArt;
    }

    public void setCodArt(int codArt) {
        this.codArt = codArt;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCodRubro() {
        return codRubro;
    }

    public void setCodRubro(int codRubro) {
        this.codRubro = codRubro;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    public static int getTAMARCHIVO() {
        return TAMARCHIVO;
    }

    public static void setTAMARCHIVO(int TAMARCHIVO) {
        Articulo.TAMARCHIVO = TAMARCHIVO;
    }

    public static int getTAMAREG() {
        return TAMAREG;
    }

    public static void setTAMAREG(int TAMAREG) {
        Articulo.TAMAREG = TAMAREG;
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
            file.writeInt(codArt);
            Registro.writeString(file, descripcion, 50);
            file.writeInt(codRubro);
            file.writeFloat(precio);
        } catch (IOException e) {
            System.out.println("Error al grabar el registro: " + e.getMessage());
            System.exit(1);
        }
    }

    @Override
    public void leer(RandomAccessFile file) {
        try {
            codArt = file.readInt();
            descripcion = Registro.readString(file, 50).trim();
            codRubro = file.readInt();
            precio = file.readFloat();
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

        Articulo file = (Articulo) x;
        return (codArt == file.codArt);
    }

    @Override
    public int hashCode() {
        return codArt;
    }

    @Override
    public void mostrarRegistro() {
        System.out.println(String.format("| %4d | %-15s | $%4d | %8.2f |", codArt, descripcion, codRubro, precio));
    }

    @Override
    public void cargarDatos() {
        cargarDescripcion();
        cargarCodRubro();
        cargarPrecio();
    }
    
    public void cargarDescripcion(){
        setDescripcion(Consola.readString("Descripcion: "));
    }
    
    public void cargarCodRubro(){
        setCodRubro(Consola.readInt(0, "Codigo de Rubro: "));
    }
    
    public void cargarPrecio(){
        setPrecio(Consola.readFloat(0, "Precio: "));
    }
    
    public void mostarDatos(String formato){
        System.out.println(String.format(formato, codArt, descripcion, codRubro, precio));
    }
}