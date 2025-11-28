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

        Gridsolver gridsolver = new Gridsolver();

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

        Sequence solution = gridsolver.findLargestSequence(grid);

        inputReader.close();
    }

    /**
     * Finds the largest sequence of four numbers in a two-dimensional square grid of numbers
     * @param grid - the grid to be checked
     */
    public Sequence findLargestSequence(int[][] grid) {
        Sequence largestFoundSequence = new Sequence(new int[2][4], 0);
        for (int rowIndex = 0; rowIndex < grid.length; rowIndex++) {
            for (int colIndex = 0; colIndex < grid.length; colIndex++) {
                Sequence largestDirectionalSequence = checkAllDirections(rowIndex, colIndex, grid);

                //compare largest directional sequence to largest found sequence and update if larger  
                if (largestDirectionalSequence.isLargerThan(largestFoundSequence)) {
                    largestFoundSequence = largestDirectionalSequence;
                }
            }
        }
        return largestFoundSequence;
    }

    private Sequence checkAllDirections(int rowIndex, int colIndex, int[][] grid) {
        //initialize return value and sequence storage
        Sequence largestDirectionalSequence = new Sequence(new int[2][4], 0);
        Sequence[] directionalSequences = new Sequence[4];
        
        //find and store all directional sequences
        directionalSequences[0] = checkHorizontalSequence(rowIndex, colIndex, grid);
        directionalSequences[1] = checkVerticalSequence(rowIndex, colIndex, grid);
        directionalSequences[2] = checkDiagonalRightSequence(rowIndex, colIndex, grid);
        directionalSequences[3] = checkDiagonalLeftSequence(rowIndex, colIndex, grid);

        for (Sequence seq : directionalSequences) {
            //go to next sequence if no valid sequence could be calculated
            if (seq == null) {
                continue;
            }
            //update largest directional sequence if a larger one is encountered
            if (seq.isLargerThan(largestDirectionalSequence)) {
                largestDirectionalSequence = seq;
            }
        }

        return largestDirectionalSequence;
    }

    private Sequence checkHorizontalSequence(int rowIndex, int colIndex, int[][] grid) {
        //return if there are not enough numbers to form a valid sequence
        if (colIndex >= grid.length-3) {
            return null;
        }

        //calculate product and save indices
        int product = 1;
        int[][] indices = new int[2][4];
        for (int i = 0; i < 4; i++) {
            product = product * grid[rowIndex][colIndex+i];
            indices[i] = new int[] {rowIndex, colIndex+i};
        }

        return new Sequence(indices, product); 
    }

    private Sequence checkVerticalSequence(int rowIndex, int colIndex, int[][] grid) {
        //return if there are not enough numbers to form a valid sequence
        if (rowIndex >= grid.length-3) {
            return null;
        }

        //calculate product and save indices
        int product = 1;
        int[][] indices = new int[2][4];

        for (int i = 0; i < 4; i++) {
            product = product * grid[rowIndex+i][colIndex];
            indices[i] = new int[] {rowIndex+i, colIndex};
        }

        return new Sequence(indices, product); 
    }
    private Sequence checkDiagonalRightSequence(int rowIndex, int colIndex, int[][] grid) {
        //return if there are not enough numbers to form a valid sequence in minimum one dimension
        if (colIndex >= grid.length-3 || rowIndex >= grid.length-3) {
            return null;
        }

        //calculate product and save indices
        int product = 1;
        int[][] indices = new int[2][4];
        for (int i = 0; i < 4; i++) {
            product = product * grid[rowIndex+i][colIndex+i];
            indices[i] = new int[] {rowIndex+i, colIndex+i};
        }

        return new Sequence(indices, product); 
    }
    private Sequence checkDiagonalLeftSequence(int rowIndex, int colIndex, int[][] grid) {
        //return if there are not enough numbers to form a valid sequence in minimum one dimension
        if (colIndex <= 3 || rowIndex >= grid.length-3) {
            return null;
        }

        //calculate product and save indices
        int product = 1;
        int[][] indices = new int[2][4];

        for (int i = 0; i < 4; i++) {
            product = product * grid[rowIndex+i][colIndex-i];
            indices[i] = new int[] {rowIndex+i, colIndex-i};
        }

        return new Sequence(indices, product); 
    }
}
