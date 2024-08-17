package excepciones;

public class DesapilarPVException extends PilaException{
    public DesapilarPVException() {
        super("No se puede desapilar de una pila vacia");
    }
    
}
