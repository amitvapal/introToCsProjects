/*
 * Write your program inside the main method to build
 * a staicase in a 2D array of characters according
 * to the assignment description
 *
 * To compile:
 *        javac StaircaseBuilder.java
 * 
 * DO NOT change the class name
 * DO NOT use System.exit()
 * DO NOT change add import statements
 * DO NOT add project statement
 * 
 */
public class StaircaseBuilder {
    
    public static void main(String[] args) {
        
        int d = Integer.parseInt(args[0]);
        int bricks = Integer.parseInt(args[1]);

        char[][] stairCase = new char[d][d];
        
        
        int i = 0;
        while(i < d){
            int j = 0;
            while(j<d-i-1){
                stairCase[i][j] = ' ';
                j++;
            }
            i++;
        }

        
        int c = 0;
        while (c < d) {
            int r = d - 1;
            while (r >= 0) {
                if (stairCase[r][c] != ' ' && bricks != 0) {
                    stairCase[r][c] = 'X';
                    bricks--;
                }
                r--;
            }
            c++;
        }

        

        i = 0;
        while (i < d) {
            int j = 0;
            while (j < d) {
                if (stairCase[i][j] == 'X') {
                    System.out.print("X");
                }
                else {
                    System.out.print(" ");
                }
                j++;
            }
            System.out.println();
            i++;
        }

        System.out.println("Bricks remaining: " + bricks);

    }
}




