package excepciones;

public class AlturaMaximaException extends AlturaException {
    public AlturaMaximaException() {
        super("Se ha excedido la altura máxima.");
    }
}
