package datos;

import utilidades.Consola;
import java.io.*;
import persistencia.*;
import principal.PersonalMenu;

public class Invento implements Grabable {

    private int Cod_Inv;      // 4
    private String Den;       // 60 [30]
    private int codPersonal;  // 4
    private float monto;      // 4 bytes
    private int patente;      // 4 bytes

    private static int TAMARCHIVO = 100; // Cantidad de registros que tendra el archivo
    private static int TAMAREG = 76;     // Cantidad de bytes que tendrá el registro

    public Invento() {
        this.Cod_Inv = 0;
        this.Den = "";
        this.codPersonal = 0;
        this.monto = 0;
        this.patente = 0;
    }

    public int getCod_Inv() {
        return Cod_Inv;
    }

    public void setCod_Inv(int Cod_Inv) {
        this.Cod_Inv = Cod_Inv;
    }

    public String getDen() {
        return Den;
    }

    public void setDen(String Den) {
        this.Den = Den;
    }

    public int getCodPersonal() {
        return codPersonal;
    }

    public void setCodPersonal(int codPersonal) {
        this.codPersonal = codPersonal;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public int getPatente() {
        return patente;
    }

    public void setPatente(int patente) {
        this.patente = patente;
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
            file.writeInt(Cod_Inv);
            Registro.writeString(file, Den, 30);
            file.writeInt(codPersonal);
            file.writeFloat(monto);
            file.writeInt(patente);
        } catch (IOException e) {
            System.out.println("Error al grabar el registro: " + e.getMessage());
            System.exit(1);
        }
    }

    @Override
    public void leer(RandomAccessFile file) {
        try {
            Cod_Inv = file.readInt();
            Den = Registro.readString(file, 30).trim();
            codPersonal = file.readInt();
            monto = file.readFloat();
            patente = file.readInt();
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

        Invento file = (Invento) x;
        return (Cod_Inv == file.Cod_Inv);
    }

    @Override
    public int hashCode() {
        return Cod_Inv;
    }

    @Override
    public void mostrarRegistro() {
        System.out.println(String.format("| %6d | %10s | %6d | $%5.2f | %6d |", getCod_Inv(), getDen(), getCodPersonal(), getMonto(), getPatente()));
    }

    @Override
    public void cargarDatos() {
        cargarDen();
        cargarCodPersonal();
        cargarMonto();
        cargarPatente();
    }
    
    public void cargarDen(){
        setDen(Consola.readString("Denominacion: "));
    }
    
    public void cargarCodPersonal(){
        int aux = 0;
        do {
            aux = Consola.readInt(0, "Codigo Personal: ");
        } while (!PersonalMenu.verificarExistencia(Cod_Inv));
        PersonalMenu.mostrarApe_Nom(codPersonal);
        setCodPersonal(aux);
    }
    
    public void cargarMonto(){
        setMonto(Consola.readFloat(0, "Monto: "));
    }
    
    public void cargarPatente(){
        setPatente(Consola.readInt(0, "Patente: "));
    }
    
    public void mostarDatos(String formato){
        System.out.println(String.format(formato, getCod_Inv(), getDen(), getMonto(), getPatente()));
    }
}