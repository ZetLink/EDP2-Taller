package datos;

import utilidades.*;
import java.io.*;
import persistencia.*;

public class Viaje implements Grabable {
    
    private int codV;             // 4  bytes
    private int codDes;           // 4  bytes
    private String fecha;         // 20 bytes
    private int hora;             // 4  bytes
    private float tarifa;         // 4  bytes
    private int cantDias;         // 4  bytes

    private static int TAMARCHIVO = 100; // cantidad de registros que tendra el archivo
    private static int TAMAREG = 40;    // cantidad de bytes que tendrá el registro

    public Viaje() {
        this.codV = 0;
        this.codDes = 0;
        this.fecha = "";
        this.hora = 0;
        this.tarifa = 0;
        this.cantDias = 0;
    }

    public int getCodV() {
        return codV;
    }

    public void setCodV(int codV) {
        this.codV = codV;
    }

    public int getCodDes() {
        return codDes;
    }

    public void setCodDes(int codDes) {
        this.codDes = codDes;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public float getTarifa() {
        return tarifa;
    }

    public void setTarifa(float tarifa) {
        this.tarifa = tarifa;
    }

    public int getCantDias() {
        return cantDias;
    }

    public void setCantDias(int cantDias) {
        this.cantDias = cantDias;
    }
    
    public static int getTAMARCHIVO() {
        return TAMARCHIVO;
    }

    public static void setTAMARCHIVO(int TAMARCHIVO) {
        Viaje.TAMARCHIVO = TAMARCHIVO;
    }

    public static int getTAMAREG() {
        return TAMAREG;
    }

    public static void setTAMAREG(int TAMAREG) {
        Viaje.TAMAREG = TAMAREG;
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
            file.writeInt(codV);
            file.writeInt(codDes);
            Registro.writeString(file, fecha, 10);
            file.writeInt(hora);
            file.writeFloat(tarifa);
            file.writeInt(cantDias);
        } catch (IOException e) {
            System.out.println("Error al grabar el registro: " + e.getMessage());
            System.exit(1);
        }
    }

    @Override
    public void leer(RandomAccessFile file) {
        try {
            codV = file.readInt();
            codDes = file.readInt();
            fecha = Registro.readString(file, 10).trim();
            hora = file.readInt();
            tarifa = file.readFloat();
            cantDias = file.readInt();
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

        Viaje file = (Viaje) x;
        return (codV == file.codV);
    }

    @Override
    public int hashCode() {
        return codV;
    }

    @Override
    public void mostrarRegistro() {
        System.out.println(String.format("| %-4d | %-4s | $%-20s | %-10d | %-8.2f | %-4d |", codV, codDes, fecha, hora, tarifa, cantDias));
    }

    @Override
    public void cargarDatos() {
        cargarFecha();
        cargarHora();
        cargarTarifa();
        cargarCantDias();
    }
    
    public void cargarFecha(){
        Fecha fechaTemp = new Fecha();
        fechaTemp.cargarFecha();
        setFecha(fechaTemp.obtenerFechaComoString());
    }
    
    public void cargarHora(){
        setHora(Consola.readInt(0, "Hora: "));
    }
    
    public void cargarTarifa(){
        setTarifa(Consola.readFloat(0, "Tarifa: "));
    }
    
    public void cargarCantDias(){
        setCantDias(Consola.readInt(0, "Cantidad de dias: "));
    }
    
    public void mostarDatos(String formato){
        System.out.println(String.format(formato, codV, codDes, fecha, hora, tarifa, cantDias));
    }
}