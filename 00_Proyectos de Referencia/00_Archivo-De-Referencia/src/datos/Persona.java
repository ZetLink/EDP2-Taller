package datos;

import utilidades.Consola;
import java.io.*;
import persistencia.*;

public class Persona implements Grabable {
    
    private int codPersona;     // 4   bytes
    private String Nombre;      // 100 bytes
    private int edad;           // 4   bytes

    private static int TAMARCHIVO = 100; // Cantidad de registros que tendra el archivo
    private static int TAMAREG = 108;    // Cantidad de bytes que tendrá el registro

    public Persona() {
        this.codPersona = 0;
        this.Nombre = " ";
        this.edad = 0;
    }

    public int getCodPersona() {
        return codPersona;
    }

    public void setCodPersona(int codPersona) {
        this.codPersona = codPersona;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    public static int getTAMARCHIVO() {
        return TAMARCHIVO;
    }

    public static void setTAMARCHIVO(int TAMARCHIVO) {
        Persona.TAMARCHIVO = TAMARCHIVO;
    }

    public static int getTAMAREG() {
        return TAMAREG;
    }

    public static void setTAMAREG(int TAMAREG) {
        Persona.TAMAREG = TAMAREG;
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
            file.writeInt(codPersona);
            Registro.writeString(file, Nombre, 50);
            file.writeInt(edad);
        } catch (IOException e) {
            System.out.println("Error al grabar el registro: " + e.getMessage());
            System.exit(1);
        }
    }

    @Override
    public void leer(RandomAccessFile file) {
        try {
            codPersona = file.readInt();
            Nombre = Registro.readString(file, 50).trim();
            edad = file.readInt();
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

        Persona file = (Persona) x;
        return (codPersona == file.codPersona);
    }

    @Override
    public int hashCode() {
        return codPersona;
    }

    @Override
    public void mostrarRegistro() {
        System.out.println(String.format("| %4d | %-15s | $%4d |", codPersona, Nombre, edad));
    }

    @Override
    public void cargarDatos() {
        cargarNombre();
        cargarEdad();
    }
    
    public void cargarNombre(){
        setNombre(Consola.readString("Nombre: "));
    }
    
    public void cargarEdad(){
        setEdad(Consola.readInt(0, "Edad: "));
    }
    public void mostarDatos(String formato){
        System.out.println(String.format(formato, codPersona, Nombre, edad));
    }
}