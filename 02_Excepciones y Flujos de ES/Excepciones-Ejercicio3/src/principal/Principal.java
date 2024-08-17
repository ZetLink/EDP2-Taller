package principal;

public class Principal {
    
    public void verificarNumero(int numero) throws NumeroNegativoExcepcion {
        if (numero < 0) {
            throw new NumeroNegativoExcepcion("El número no puede ser negativo");
        }
    }
    
    public static void main(String[] args) {
        Principal app = new Principal();
        int numero = -5;
        
        try {
            app.verificarNumero(numero);
            System.out.println("El número es válido.");
        } catch (NumeroNegativoExcepcion e) {
            System.out.println("Excepción: " + e);
        }
    }
}
    