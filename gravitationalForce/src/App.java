
public class App {
    public static void main(String[] args) throws Exception {

        
        double mass1 = Double.parseDouble(args[0]);
        double mass2 = Double.parseDouble(args[1]);
        double r = Double.parseDouble(args[2]);

        double g = 6.6743e-11;
        double force = g*(mass1*mass2)/(r*r);
        System.out.println(force);
 
       

       




        

    }
}
