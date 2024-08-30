package datos;

import utilidades.Consola;
import java.io.*;
import persistencia.*;

public class Libro implements Grabable {
    
    private int codLib;             // 4   bytes
    private String titulo;          // 80 bytes
    private String autor;           // 60   bytes
    private int a_Edic;             // 4   bytes

    private static int TAMARCHIVO = 100; // cantidad de registros que tendra el archivo
    private static int TAMAREG = 148;    // cantidad de bytes que tendrá el registro

    public Libro() {
        this.codLib = 0;
        this.titulo = " ";
        this.autor = " ";
        this.a_Edic = 0;
    }

    public int getCodLib() {
        return codLib;
    }

    public void setCodLib(int codLib) {
        this.codLib = codLib;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getA_Edic() {
        return a_Edic;
    }

    public void setA_Edic(int a_Edic) {
        this.a_Edic = a_Edic;
    }
    
    public static int getTAMARCHIVO() {
        return TAMARCHIVO;
    }

    public static void setTAMARCHIVO(int TAMARCHIVO) {
        Libro.TAMARCHIVO = TAMARCHIVO;
    }

    public static int getTAMAREG() {
        return TAMAREG;
    }

    public static void setTAMAREG(int TAMAREG) {
        Libro.TAMAREG = TAMAREG;
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
            file.writeInt(codLib);
            Registro.writeString(file, titulo, 40);
            Registro.writeString(file, autor, 30);
            file.writeInt(a_Edic);
        } catch (IOException e) {
            System.out.println("Error al grabar el registro: " + e.getMessage());
            System.exit(1);
        }
    }

    @Override
    public void leer(RandomAccessFile file) {
        try {
            codLib = file.readInt();
            titulo = Registro.readString(file, 40).trim();
            autor = Registro.readString(file, 30).trim();
            a_Edic = file.readInt();
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

        Libro file = (Libro) x;
        return (codLib == file.codLib);
    }

    @Override
    public int hashCode() {
        return codLib;
    }

    @Override
    public void mostrarRegistro() {
        System.out.println(String.format("| %-4d | %-20s | $%-20d | %-4d |", codLib, titulo, autor, a_Edic));
    }

    @Override
    public void cargarDatos() {
        cargarTitulo();
        cargarAutor();
        cargarAEdic();
    }
    
    public void cargarTitulo(){
        setTitulo(Consola.readString("Titulo: "));
    }
    
    public void cargarAutor(){
        setAutor(Consola.readString("Autor: "));
    }
    
    public void cargarAEdic(){
        do {
        setA_Edic(Consola.readInt(1969, "Año de Edicion: "));
        } while (getA_Edic() > 2024);
    }
    
    public void mostarDatos(String formato){
        System.out.println(String.format(formato, codLib, titulo, autor, a_Edic));
    }
}