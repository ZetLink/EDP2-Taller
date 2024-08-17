package principal;

public class Principal {
    
    public static void main(String[] args) {
        try {
            String[] vector = new String[25];
            System.out.println(vector[3].length());
        } catch (Exception e){
            System.out.println("Error: " + e);
        }
    }
    
}
