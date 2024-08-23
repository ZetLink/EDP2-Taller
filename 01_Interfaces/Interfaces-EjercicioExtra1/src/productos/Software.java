package productos;

import interfaces.*;

public class Software extends Producto implements Vendible{

    public Software(String nombre, float precio, String descripcion) {
        super(nombre, precio, descripcion);
    }

    @Override
    public void vender() {
        System.out.println("Software vendido");
    }

}
