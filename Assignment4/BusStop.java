/*
 * Write your program inside the main method to find the order
 * which the bus the student needs to take will arrive
 * according to the assignemnt description. 
 *
 * To compile:
 *        javac BusStop.java
 * 
 * DO NOT change the class name
 * DO NOT use System.exit()
 * DO NOT change add import statements
 * DO NOT add project statement
 * 
 */
public class BusStop {  

    public static void main(String[] args) {

    int[] busOrder = new int[args.length - 1];

    for (int i = 0; i < args.length - 1; i++) {
      busOrder[i] = Integer.parseInt(args[i]);
    }

    int sBus = Integer.parseInt(args[args.length - 1]);
    
    int counter = -1;

    for (int i = 0; i < busOrder.length; i++) {
      if (busOrder[i] == sBus) {
        counter = i;
        break;
      }
    }

    if (counter == -1) {
      System.out.println(1000);
    } else {
      System.out.println(counter + 1); 
    }

  }
