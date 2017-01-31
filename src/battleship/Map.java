
package battleship;

import java.util.Random;
import java.util.Scanner;


public class Map {
    
     /**
     * Miguel Jimenez 40022302
     * COMP249
     * Assignment 1
     * February 4th, 2017
     * 
     * Java class Map: Habilitates the positions for the map, and lets the ships
     * and grenades be placed on them by checking first if the position exists
     * and if it has not been called before.
     *
     * @param rows Number of rows the map has.
     * @param column Number of columns the map has.
     * @param numShips Number of ships per player.
     * @param numGrenades Number of grenades per player.
     * @param userShipsHit Number of ships that belong to the user that have
     * been hit.
     * @param compShipsHit Number of ships that belong to the computer that have
     * been hit.
     * @param uMissedTurns Number of turns that the user has missed due to
     * grenades.
     * @param cMissedTurns Number of turns that the computer has missed due to
     * grenades.
     * @param userTurn If true, the user can play.
     * @param computerTurn If true, the computer can play.
     * @param positions This 2-D array holds a Position object in each space,
     * giving format to the map.
     */
    
    private int rows;
    private int columns;
    
    private int numShips;
    private int numGrenades;
    
    private int userShipsHit;
    private int compShipsHit;

    private int uMissedTurns;
    private int cMissedTurns;
    
    private boolean userTurn;
    private boolean computerTurn;
    
    private Position[][] positions;
    
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    public Map(int newRows, int newColumns, int ships, int grenades) {

        rows = newRows;
        columns = newColumns;
        
        numShips = ships;
        numGrenades = grenades;
        
        compShipsHit = 0;
        userShipsHit = 0;
        
        uMissedTurns = 0;
        cMissedTurns = 0;
        
        userTurn = true;
        computerTurn = true;
        
        positions = new Position[rows][columns];

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < columns; j++) {
                positions[i][j] = new Position(i, j);
            }

        }

    }

    /**
     * @return The number of rows
     */
    public int getNumRows() {

        return rows;
    }

    /**
     * @return The number of columns
     */
    public int getNumColumns() {

        return columns;
    }
    
    /**
     * @return The number of ships
     */
    public int getNumShips() {
        
        return numShips;
    }
    
    /**
     * @return The number of grenades
     */
    public int getNumGrenades() {
        
        return numGrenades;
    }
    
    /**
     * @return The number of ships hit that belong to the user
     */
    public int getUserShipsHit() {
        
        return compShipsHit;
    }
    
    /**
     * @return The number of ships hit that belong to the computer
     */
    public int getCompShipsHit() {
        
        return compShipsHit;
    }
    
    /**
     * @return The number of missed turns by the user
     */
    public int getUMissedTurns() {
        
        return uMissedTurns;
    }
    
    /**
     * @return The number of missed turns by the computer
     */
    public int getCMissedTurns() {
        
        return cMissedTurns;
    }
    
    /**
     * @return If the user is allowed to play
     */
    public boolean getUserTurn() {
        
        return userTurn;
    }
    
    /**
     * @return If the computer is allowed to play
     */
    public boolean getComputerTurn() {
        
        return computerTurn;
    }
    
    /**
     * Sets the user's ability to play to either true or false.
     * 
     * @param turn Determines if the user is allowed to play or not
     */
    public void setUserTurn(boolean turn) {
        
        userTurn = turn;
    }
    
     /**
     * Sets the computer's ability to play to either true or false.
     * 
     * @param turn Determines if the user is allowed to play or not
     */
    public void setComputerTurn(boolean turn) {
        
        computerTurn = turn;
    }
    
     /**
     * Registers a position called by the user. Makes sure the position exists
     * and it is not being occupied by another object.
     * 
     * @param position Position selected by the user
     * @param id Ship's or grenade's id number
     * @param item Type of object to be registered in the desired position
     */
    public void registerPosition(String position, int id, String item) {

        boolean correct1, correct2;

        do {

            correct1 = false;
            correct2 = false;

            if (!positionExists(position)) {

                System.out.println("sorry, coordinates outside the grid. try again.");
                System.out.print("Enter the coordinates of your " + item + " #" + id + ": ");
                position = scanner.next();
                correct1 = true;

            } else if (positionOccupied(convertRow(position), convertColumn(position)) && positionExists(position)) {

                System.out.println("sorry, coordinates already used. try again.");
                System.out.print("Enter the coordinates of your " + item + " #" + id + ": ");
                position = scanner.next();
                correct2 = true;
            }

        } while (correct1 || correct2);

        positions[convertRow(position)][convertColumn(position)].setValue(item.charAt(0));

    }

     /**
     * Registers a position called by the computer. Makes sure the position exists
     * and it is not being occupied by another object.
     * 
     * @param row Row number randomly selected by the computer
     * @param column Column number randomly selected by the computer
     * @param item Type of object to be registered in the desired position
     */
    public void registerPosition(int row, int column, String item) {

        while (positionOccupied(row, column)) {

            row = random.nextInt(8);
            column = random.nextInt(8);
        }

        positions[row][column].setValue(item.charAt(0));
    }

     /**
     * Allows the user to shoot at a desired position. Checks if the position is
     * valid. If it is not it prompts the user to input a new position. This
     * method also prints a response depending on the effect of the user's
     * achievement with his play.
     */
    public void shoot() {

        boolean correct;
        String position;

        System.out.print("position of your rocket: ");
        
        position = scanner.next();

        do {

            correct = false;

            if (!positionExists(position)) {

                System.out.println("sorry, coordinates outside the grid. try again.");
                System.out.print("Enter new coordinates: ");
                position = scanner.next();
                correct = true;

            }

        } while (correct);

        switch (positions[convertRow(position)][convertColumn(position)].getType()) {
 
            case 'S':
                positions[convertRow(position)][convertColumn(position)].setPrint('S');
                positions[convertRow(position)][convertColumn(position)].setValue('*');
                System.out.print("ship hit.");
                compShipsHit++;
                break;
            case 'G':
                positions[convertRow(position)][convertColumn(position)].setPrint('G');
                positions[convertRow(position)][convertColumn(position)].setValue('*');
                System.out.print("boom! grenade.");
                uMissedTurns++;
                userTurn = !userTurn;
                break;
            case 's':
                positions[convertRow(position)][convertColumn(position)].setPrint('s');
                positions[convertRow(position)][convertColumn(position)].setValue('*');
                System.out.print("I must be confused, just sunk my own ship");
                break;
            case 'g':
                positions[convertRow(position)][convertColumn(position)].setPrint('g');
                positions[convertRow(position)][convertColumn(position)].setValue('*');
                System.out.print("boom! grenade. It's your own, though.");
                cMissedTurns++;
                computerTurn = !computerTurn;    
            case '_':
                positions[convertRow(position)][convertColumn(position)].setPrint('*');
                positions[convertRow(position)][convertColumn(position)].setValue('*');
                System.out.print("nothing.");
                break;
            case '*':
                System.out.print("position already called.");
                break;

        }

        printMap();

    }

    /**
     * Allows the computer to shoot at a randomly generated position. This method
     * checks if there is a ship or a grenade that belongs to the computer, so it
     * does not sink one of its own assets. Also, it prints a message depending
     * on the effect of the play.
     * 
     * @param row Row at which the rocket will be directed
     * @param column Column at which the rocket will be directed
     */
    public void shoot(int row, int column) {

        while(positions[row][column].getType() == 'S' || positions[row][column].getType() == 'G') {
            
            row = random.nextInt(8);
            column = random.nextInt(8);
        }
        
        System.out.println("position of my rocket: " + convertColumn(column) + convertRow(row));
        
        switch (positions[row][column].getType()) {

            case 'S':
                positions[row][column].setPrint('S');
                positions[row][column].setValue('*');
                System.out.print("I must be confused, just sunk my own ship");
                break;
            case 'G':
                positions[row][column].setPrint('G');
                positions[row][column].setValue('*');
                System.out.print("boom! grenade. It's mine, though.");
                cMissedTurns++;
                computerTurn = !computerTurn;
            case 's':
                positions[row][column].setPrint('s');
                positions[row][column].setValue('*');
                System.out.print("ship hit.");
                userShipsHit++;
                break;
            case 'g':
                positions[row][column].setPrint('g');
                positions[row][column].setValue('*');
                System.out.print("boom! grenade.");
                cMissedTurns++;
                computerTurn = !computerTurn;
                break;
            case '_':
                positions[row][column].setPrint('*');
                positions[row][column].setValue('*');
                System.out.print("nothing.");
                break;
            case '*':
                System.out.print("position already called.");
                break;

        }

        printMap();

    }

    /**
     * Displays a message declaring the winner.
     * 
     * @return A message declaring who the winner is and how many turns the
     * computer and the user lost because of grenades
     */
    public String declareWinner() {
        
            if(getCompShipsHit() == getNumShips())
                return "\nYou win!\n\nYou missed " + getUMissedTurns() + 
                        " turns and I missed " + getCMissedTurns() + " because "
                        + "of grenades";
            
            if(getUserShipsHit() == getNumShips())
                return "\nI win!\n\nYou missed " + getUMissedTurns() + 
                        " turns and I missed " + getCMissedTurns() + " because "
                        + "of grenades";
                
        return "none";    
    }
    
    /**
     * Prints the map of the game showing the positions that have been shot at
     * and the ships and grenades that have been hit.
     */
    private void printMap() {
        
        System.out.println();
        
        if("none".equals(declareWinner())) {
        
            for (int i = 0; i < rows; i++) {

                System.out.print("\t");

                for (int j = 0; j < columns; j++) 
                    System.out.print(positions[i][j].getPrint() + " ");  

                System.out.println();

            }
            
        }
        
        else {
        
            for (int i = 0; i < rows; i++) {

                System.out.print("\t");

                for (int j = 0; j < columns; j++) 
                    System.out.print(positions[i][j].getPrint() + " ");  

                System.out.println();

            }
            
        }
        

    }

    /**
     * Returns true or false depending on whether the position is occupied or not
     * 
     * @param row Row that will be checked
     * @param column Column that will be checked
     * @return True or false depending on whether the position is occupied or not
     */
    private boolean positionOccupied(int row, int column) {

        return positions[row][column].getType() != '_';
    }

   /**
    * Returns true or false depending on whether the position exists or not
    * 
    * @param position Position that will be checked
    * @return True or false depending on whether the position exists or not
    */
    private boolean positionExists(String position) {

        return ((position.charAt(0) == 'A' || position.charAt(0) == 'a'
                || position.charAt(0) == 'B' || position.charAt(0) == 'b'
                || position.charAt(0) == 'C' || position.charAt(0) == 'c'
                || position.charAt(0) == 'D' || position.charAt(0) == 'd'
                || position.charAt(0) == 'E' || position.charAt(0) == 'e'
                || position.charAt(0) == 'F' || position.charAt(0) == 'f'
                || position.charAt(0) == 'G' || position.charAt(0) == 'g'
                || position.charAt(0) == 'H' || position.charAt(0) == 'h')
                && (position.charAt(1) == '1' || position.charAt(1) == '2'
                || position.charAt(1) == '3' || position.charAt(1) == '4'
                || position.charAt(1) == '5' || position.charAt(1) == '6'
                || position.charAt(1) == '7' || position.charAt(1) == '8'));

    }

    /**
     * Converts the row of a string position (A1, H3,...) to a numeric value.
     * 
     * @param position Position that will be converted
     * @return The row of the position received converted to a numeric value
     */
    private int convertRow(String position) {

        switch (position.charAt(1)) {

            case '1':
                return 0;
            case '2':
                return 1;
            case '3':
                return 2;
            case '4':
                return 3;
            case '5':
                return 4;
            case '6':
                return 5;
            case '7':
                return 6;
            case '8':
                return 7;

        }

        return 8;

    }

    /**
     * Converts the column of a string position (A1, H3,...) to an
     * acceptable numeric value.
     * 
     * @param position Position that will be converted
     * @return The column of the position received converted to an
     * acceptable numeric value
     */
    private int convertColumn(String position) {

        switch (position.charAt(0)) {

            case 'A':
            case 'a':
                return 0;
            case 'B':
            case 'b':
                return 1;
            case 'C':
            case 'c':
                return 2;
            case 'D':
            case 'd':
                return 3;
            case 'E':
            case 'e':
                return 4;
            case 'F':
            case 'f':
                return 5;
            case 'G':
            case 'g':
                return 6;
            case 'H':
            case 'h':
                return 7;

        }

        return 8;

    }

    /**
     * Converts row number to string format
     * 
     * @param row Row to be converted
     * @return Row received by the method in string format
     */
    private String convertRow(int row) {
        
        switch (row) {

            case 0:
                return "1";
            case 1:
                return "2";
            case 2:
                return "3";
            case 3:
                return "4";
            case 4:
                return "5";
            case 5:
                return "6";
            case 6:
                return "7";
            case 7:
                return "8";

        }

        return "9";
        
    }
    
     /**
     * Converts column number to string format
     * 
     * @param column Column to be converted
     * @return Column received by the method in string format
     */
    private String convertColumn(int column) {
        
         switch (column) {

            case 0:
                return "A";
            case 1:
                return "B";
            case 2:
                return "C";
            case 3:
                return "D";
            case 4:
                return "E";
            case 5:
                return "F";
            case 6:
                return "G";
            case 7:
                return "H";

        }

        return "Z";
        
    }
    
}
