package utilidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Fecha {
    private int dia;
    private int mes;
    private int anio;

    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public Fecha() {
        this.dia = 0;
        this.mes = 0;
        this.anio = 0;
    }

    private boolean esFechaValida(int dia, int mes, int año) {
        try {
            LocalDate.of(año, mes, dia);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public String obtenerFechaComoString() {
        LocalDate fecha = LocalDate.of(anio, mes, dia);
        return fecha.format(FORMATO_FECHA);
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public void setAnio(int año) {
        this.anio = año;
    }

    public void cargarFecha() {
        boolean fValida = false;
        do {
            do {
                setDia(Consola.readInt(0, "Dia: "));
            } while (getDia() > 31 || getDia() < 1);
            do {
                setMes(Consola.readInt(0, "Mes: "));
            } while (getMes() > 12 || getMes() < 1);
            do {
                setAnio(Consola.readInt(0, "Año: "));
            } while (getAnio() > 2024 || getAnio() < 1979);
            fValida = esFechaValida(getDia(), getMes(), getAnio());
        } while (!fValida);
    }
}