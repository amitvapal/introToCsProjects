import java.util.Scanner;
public class App {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your bus number: ");
        int busNum = input.nextInt();
        String num = Integer.toString(busNum);
        int sum = 0;
        for(int i=0; i<=num.length()-1; i++){
            sum += num.charAt(i);

        }
        if (sum % 2 ==0){
            System.out.println("Your bus is LX");
        }
        else{
            System.out.println("Your bus is H");
        }
        // What are the inputs, outputs, error conditions?
        // Inputs: bus number
        // Outputs: LX or H
        // Error conditions: bus number is not a number

        // Write the algorithm in pseudocode
        // READ busNum
        // SET sum = 0
        // FOR i FROM 0 to length of busNum -1
        //     ADD num at postion i to sum
        // END FOR
        // IF sum MODULO 2 is 0
        //     DISPLAY "Your bus is LX"
        // ELSE
        //    DISPLAY "Your bus is H"
        // END IF




        
    }
}
