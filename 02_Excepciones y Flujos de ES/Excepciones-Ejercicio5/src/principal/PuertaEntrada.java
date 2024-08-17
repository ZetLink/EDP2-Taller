package principal;

import excepciones.AlturaException;

public class PuertaEntrada {
    
    public static void main(String[] args) {
        Sensor sensor = new Sensor();

        try {
            int altura = sensor.obtenerAltura();
            System.out.println("Altura válida: " + altura + " cm");
        } catch (AlturaException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
