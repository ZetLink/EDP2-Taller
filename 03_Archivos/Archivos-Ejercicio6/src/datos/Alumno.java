package datos;

import utilidades.Consola;
import java.io.*;
import persistencia.*;
import principal.CursoMenu;

public class Alumno implements Grabable {

    private int cod_Alum;           // 4
    private int cod_Curso;          // 4
    private int dni;                // 4
    private String ape_nom;         // 60 [30]
    private float saldo;            // 4 

    private static int TAMARCHIVO = 100; // Cantidad de registros en el archivo
    private static int TAMAREG = 76;     // Bytes por Registro

    public Alumno() {
        this.cod_Alum = 0;
        this.cod_Curso = 0;
        this.dni = 0;
        this.ape_nom = "";
        this.saldo = 0;
    }

    public int getCod_Alum() {
        return cod_Alum;
    }

    public void setCod_Alum(int cod_Alum) {
        this.cod_Alum = cod_Alum;
    }

    public int getCod_Curso() {
        return cod_Curso;
    }

    public void setCod_Curso(int cod_Curso) {
        this.cod_Curso = cod_Curso;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getApe_nom() {
        return ape_nom;
    }

    public void setApe_nom(String ape_nom) {
        this.ape_nom = ape_nom;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
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
            file.writeInt(cod_Alum);
            file.writeInt(cod_Curso);
            file.writeInt(dni);
            Registro.writeString(file, ape_nom, 30);
            file.writeFloat(saldo);
        } catch (IOException e) {
            System.out.println("Error al grabar el registro: " + e.getMessage());
            System.exit(1);
        }
    }

    @Override
    public void leer(RandomAccessFile file) {
        try {
            cod_Alum = file.readInt();
            cod_Curso = file.readInt();
            dni = file.readInt();
            ape_nom = Registro.readString(file, 30).trim();
            saldo = file.readFloat();
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

        Alumno file = (Alumno) x;
        return (cod_Alum == file.cod_Alum);
    }

    @Override
    public int hashCode() {
        return cod_Alum;
    }

    @Override
    public void mostrarRegistro() {
        // https://www.javatpoint.com/java-string-format
        System.out.println(String.format("| %6d | %6d | %12d | %25s | $%8.2f |", cod_Alum, cod_Curso, dni, ape_nom, saldo));
    }

    @Override
    public void cargarDatos() {
        cargarCod_C();
        cargarDni();
        cargarApe_Nom();
        cargarMonto();
    }

    public void cargarCod_C(){
        int aux = 0;
        do {
            aux = Consola.readInt(0, "Codigo del Curso: ");
        } while (!CursoMenu.verificarExistencia(aux));
        setCod_Curso(aux);
    }
    
    public void cargarDni(){
        setDni(Consola.readInt(0, "DNI: "));
    }
        
    public void cargarApe_Nom(){
        setApe_nom(Consola.readString("Apellido y Nombre: "));
    }
    
    public void cargarMonto(){
        setSaldo(Consola.readFloat(0, "Monto: "));
    }
    
    public void mostarDatos(String formato){
        System.out.println(String.format(formato, getCod_Alum(), getDni(), getApe_nom(), getSaldo()));
    }
}