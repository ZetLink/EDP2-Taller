package principal;

import interfaces.Manejable;

public class Vehiculo implements Manejable{
    private int velocidad_max;
    private int velocidad_actual;

    public int getVelocidad_max() {
        return velocidad_max;
    }

    public void setVelocidad_max(int velocidad_max) {
        this.velocidad_max = velocidad_max;
    }

    public int getVelocidad_actual() {
        return velocidad_actual;
    }

    public void setVelocidad_actual(int velocidad_actual) {
        this.velocidad_actual = velocidad_actual;
    }
    
    @Override
    public void acelerar(){}
    
    @Override
    public void frenar(){}
}
