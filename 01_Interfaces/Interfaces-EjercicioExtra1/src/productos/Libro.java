package productos;

import interfaces.*;

public class Libro extends Producto implements Vendible, Transportable, Empaquetable{

    public Libro(String nombre, float precio, String descripcion) {
        super(nombre, precio, descripcion);
    }

    @Override
    public void vender() {
        System.out.println("Libro vendido");
    }

    @Override
    public void empaquetar() {
        System.out.println("Libro empaquetado");
    }
    
    
    @Override
    public void transportar() {
        System.out.println("Libro transportado");
    }
    
}
