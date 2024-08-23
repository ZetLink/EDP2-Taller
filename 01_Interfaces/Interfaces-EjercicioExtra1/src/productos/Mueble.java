package productos;

import interfaces.*;

public class Mueble extends Producto implements Vendible, Transportable, Empaquetable{

    public Mueble(String nombre, float precio, String descripcion) {
        super(nombre, precio, descripcion);
    }
    
    @Override
    public void vender() {
        System.out.println("Mueble vendido");
    }

    @Override
    public void empaquetar() {
        System.out.println("Mueble empaquetado");
    }
    
    @Override
    public void transportar() {
        System.out.println("Mueble transportado");
    }

}
