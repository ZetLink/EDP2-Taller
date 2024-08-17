package principal;

import utilidades.Consola;

public class principal {

    VehiculoTerrestre vT = new VehiculoTerrestre();
    VehiculoAcuatico vA = new VehiculoAcuatico();
    
    public void crm(){
        int fuerza = Consola.readInt(0, "Fuerza: ");
        int radio = Consola.readInt(0, "Radio: ");
        vT.calcularR(fuerza, radio);
    }
    
    public void rv(){
        int viento = Consola.readInt(0, "Velocidad de Viento: ");
        vA.recomendarV(viento);
    }
    
    public void menu() {
        String titulo = "Menu de Opciones";
        String[] opciones = {
            "1. Cargar Datos",
            "2. Calcular Revoluciones (Terrestre)",
            "3. Recomendar Velocidad (Acuatico)",
            "0. Salir"
        };
        int op;
        do {
            Consola.imprimirMenu(opciones, titulo);
            op = Consola.readInt(0, "-->");
            switch (op) {
                case 1: vT.cargarDatos();
                        vA.cargarDatos();
                        break;
                case 2: crm();
                        break;
                case 3: rv();
                        break;
                case 0: break;
            }
        } while (op != 0);
    }
    
    public static void main(String[] args) {
        principal app = new principal();
        app.menu();
    }
    
}
