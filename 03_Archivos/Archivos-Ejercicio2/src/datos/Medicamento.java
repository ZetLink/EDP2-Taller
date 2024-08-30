package datos;

import utilidades.*;
import java.io.*;
import persistencia.*;

public class Medicamento implements Grabable {
    
    private int codM;               // 4   bytes
    private String descripcion;     // 100 bytes
    private float precio;           // 4   bytes
    private int cant_S;             // 4   bytes
    private String fecha_Venc;      // 20  bytes
    private char tipo;              // 2   bytes

    private static int TAMARCHIVO = 100; // cantidad de registros que tendra el archivo
    private static int TAMAREG = 134;    // cantidad de bytes que tendrá el registro

    public Medicamento() {
        this.codM = 0;
        this.descripcion = "";
        this.precio = 0;
        this.cant_S = 0;
        this.fecha_Venc = null;
        this.tipo = ' ';
    }

    public int getCodM() {
        return codM;
    }

    public void setCodM(int codM) {
        this.codM = codM;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCant_S() {
        return cant_S;
    }

    public void setCant_S(int cant_S) {
        this.cant_S = cant_S;
    }

    public String getFecha_Venc() {
        return fecha_Venc;
    }

    public void setFecha_Venc(String fecha_Venc) {
        this.fecha_Venc = fecha_Venc;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }
    
    public static int getTAMARCHIVO() {
        return TAMARCHIVO;
    }

    public static void setTAMARCHIVO(int TAMARCHIVO) {
        Medicamento.TAMARCHIVO = TAMARCHIVO;
    }

    public static int getTAMAREG() {
        return TAMAREG;
    }

    public static void setTAMAREG(int TAMAREG) {
        Medicamento.TAMAREG = TAMAREG;
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
            file.writeInt(codM);
            Registro.writeString(file, descripcion, 50);
            file.writeFloat(precio);
            file.writeInt(cant_S);
            Registro.writeString(file, fecha_Venc, 10);
            file.writeChar(tipo);
        } catch (IOException e) {
            System.out.println("Error al grabar el registro: " + e.getMessage());
            System.exit(1);
        }
    }

    @Override
    public void leer(RandomAccessFile file) {
        try {
            codM = file.readInt();
            descripcion = Registro.readString(file, 50).trim();
            precio = file.readFloat();
            cant_S = file.readInt();
            fecha_Venc = Registro.readString(file, 10).trim();
            tipo = file.readChar();
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

        Medicamento file = (Medicamento) x;
        return (codM == file.codM);
    }

    @Override
    public int hashCode() {
        return codM;
    }

    @Override
    public void mostrarRegistro() {
        System.out.println(String.format("| %-4d | %-15s | %-8.2f | %-4d | %-10s | %-4s |", codM, descripcion, precio, cant_S, fecha_Venc, tipo));
    }

    @Override
    public void cargarDatos() {
        cargarDescripcion();
        cargarPrecio();
        cargarCantS();
        cargarFechaVenc();
        cargarTipo();
    }
    
    public void cargarDescripcion(){
        setDescripcion(Consola.readString("Descripcion: "));
    }
    
    public void cargarPrecio(){
        setPrecio(Consola.readFloat(0, "Precio: "));
    }
    
    public void cargarCantS(){
        setCant_S(Consola.readInt(0, "Cantidad de Stock: "));
    }
    
    public void cargarFechaVenc(){
        Fecha fechaTemp = new Fecha();
        fechaTemp.cargarFecha();
        setFecha_Venc(fechaTemp.obtenerFechaComoString());
    }
    
    public void cargarTipo(){
        char cTemp = ' ';
        do{
            cTemp = Consola.readChar("Tipo (P/J): ");
        } while (Character.compare(cTemp, 'P') != 0 && Character.compare(cTemp, 'J') != 0);
        setTipo(cTemp);
    }
    
    public void mostarDatos(String formato){
        System.out.println(String.format(formato, codM, descripcion, precio, cant_S, fecha_Venc, tipo));
    }
}