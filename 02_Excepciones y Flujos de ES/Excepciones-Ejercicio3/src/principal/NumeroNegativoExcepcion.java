package principal;

public class NumeroNegativoExcepcion extends Exception {
    
    NumeroNegativoExcepcion(){
        super();
        System.out.println("Se convocó a constructor de Exception sin parámetros");
    }
    NumeroNegativoExcepcion(String p){
        super(p);
        System.out.println("Se convocó a constructor de Exception con parámetro String");
    }
    
}