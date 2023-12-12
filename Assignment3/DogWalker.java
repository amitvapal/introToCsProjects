/*
 *  
 * Write the DogWalker program inside the main method
 * according to the assignment description.
 * 
 * To compile:
 *        javac DogWalker.java
 * To execute:
 *        java DogWalker 5
 * 
 * DO NOT change the class name
 * DO NOT use System.exit()
 * DO NOT change add import statements
 * DO NOT add project statement
 * 
 *
 */
public class DogWalker {

    public static void main(String[] args) {

        int n  = Integer.parseInt(args[0]);

        int x = 0;
        int y = 0;

        for(int i = 0; i <= n; i++) {
            double r = Math.random();
            if(r < 0.25) {
                x++;
            }
            else if(r<0.5) {
                x--;
            }
            else if(r<0.75) {
                y++;
            }
            else {
                y--;
            }
            System.out.println("(" + x + "," + y + ")");
        }
        double distance = x*x + y*y;
        System.out.println("Squared distance = " + distance);


       
			   
    }
}
