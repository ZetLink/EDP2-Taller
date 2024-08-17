package principal;

import utilidades.Consola;
import interfaces.*;

public class VehiculoAcuatico extends Vehiculo implements Vela{
    private String tipo;
    private int capacidad;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    
    public void cargarDatos(){
        System.out.println("--Datos Acuatico--");
        setTipo(Consola.readString("Tipo: "));
        setCapacidad(Consola.readInt(0, "Capacidad: "));
    }
    
    public void recomendarV(int velocidadViento){
        if (velocidadViento >= 80){
            System.out.println("Velocidad Alta. Se recomienda no salir a nevegar");
        } else if (velocidadViento <= 10) {
            System.out.println("Velocidad Baja");
        } else {
            System.out.println("Velocidad de viento normal");
        }
    }
}
