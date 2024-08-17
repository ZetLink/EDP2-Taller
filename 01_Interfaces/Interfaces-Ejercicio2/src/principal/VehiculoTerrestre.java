package principal;

import utilidades.Consola;
import interfaces.*;

public class VehiculoTerrestre extends Vehiculo implements Motor{
    private int cant_llantas;
    private String uso;

    public int getCant_llantas() {
        return cant_llantas;
    }

    public void setCant_llantas(int cant_llantas) {
        this.cant_llantas = cant_llantas;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }
    
    public void cargarDatos(){
        System.out.println("--Datos Terrestre--");
        setCant_llantas(Consola.readInt(0, "Cantidad de llantas: "));
        setUso(Consola.readString("Uso: "));
    }
    
    @Override
    public void calcularR(int fuerza, int radio){
        int revoluciones =  fuerza * radio;
        System.out.println("Revoluciones:" + revoluciones);
    }
    
}
