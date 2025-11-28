import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Gridsolver {

    //main method to run the grid solver program
    public static void main(String[] args) throws IOException {
        //initialize program constants
        final String INPUT_FILE = "inputs/grid3.txt";
        final int GRID_SIZE = 20;

        //pre-load grid
        int[][] grid = new int[GRID_SIZE][GRID_SIZE];

        //buffer inputReader
        BufferedReader inputReader;

        //try to initialize input file and handle FileNotFoundException
        try {
            inputReader = new BufferedReader(new FileReader(INPUT_FILE));
        } catch (FileNotFoundException e) {
            System.out.println("\nCould not find the specified file. Please make sure it is present.\n");
            return;
        }

        //read the input file and parse to array
        try {
            System.out.println("Reading file...");
            //read the two-dimensional file
            for (int i = 0; i < GRID_SIZE; i++) {
                String[] inputRow = inputReader.readLine().split(" ");
                int rowIndex = 0;
                //parse every number of each line to an integer and add to grid
                for (String number : inputRow) {
                    grid[i][rowIndex] = Integer.parseInt(number);
                    rowIndex++;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("\nGrid input is handled incorrectly. Could not build grid.\n");
            return;
        } catch (IOException e) {
            System.out.println("\nSomething went wrong when reading input file...\n");
            e.printStackTrace();
            return;
        } finally {
            inputReader.close();
        }
        
        //print feedback and grid
        System.out.println("Grid created successfully.");
        for (int[] row : grid) {
            System.out.println(Arrays.toString(row));
        }

        inputReader.close();
    }
}
