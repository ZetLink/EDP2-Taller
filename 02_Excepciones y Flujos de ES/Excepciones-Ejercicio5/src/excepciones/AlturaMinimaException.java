package excepciones;

public class AlturaMinimaException extends AlturaException {
    public AlturaMinimaException() {
        super("No alcanza la altura minima");
    }
}
