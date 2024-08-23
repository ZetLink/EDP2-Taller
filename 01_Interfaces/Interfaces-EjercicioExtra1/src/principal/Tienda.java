package principal;

import interfaces.*;
import productos.*;

public class Tienda {

    public void procesarVenta(Producto producto) {
        if (producto instanceof Vendible) {
            ((Vendible) producto).vender();
        }

        if (producto instanceof Empaquetable) {
            ((Empaquetable) producto).empaquetar();
        }

        if (producto instanceof Transportable) {
            ((Transportable) producto).transportar();
        }
    }
    
    
    public static void main(String[] args) {
        Tienda tienda = new Tienda();

        Libro libro = new Libro("Harry Potter y La pieda filosofal", 18000, "Editorial Salamanca tapa blanda");
        Mueble escritorio = new Mueble("Escritorio", 76000, "Escritorio Oficina Moderno Cajones Pc Living Ricchezze Color Blanco");
        Software windows = new Software("Windows 11 Home", 5709, "CD-KEY 100% LeGAl N0 F4ke");

        tienda.procesarVenta(libro);
        tienda.procesarVenta(escritorio);
        tienda.procesarVenta(windows);
    }
    
}
