package principal;

import excepciones.AlturaMinimaException;
import excepciones.AlturaNegativaException;
import excepciones.AlturaException;
import excepciones.AlturaMaximaException;
import utilidades.Consola;

public class Sensor {
    public int obtenerAltura() throws AlturaException {
        int altura = Consola.readInt(-214748364, "Altura: ");

        if (altura < 0) {
            throw new AlturaNegativaException();
        } else if (altura < 150) {
            throw new AlturaMinimaException();
        } else if (altura > 190) {
            throw new AlturaMaximaException();
        }

        return altura;
    }
}
