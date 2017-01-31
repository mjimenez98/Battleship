package battleship;

/**
 * @author Miguel Jimenez
 * @since 1.0
 */
public class Position {

    /**
     *  * Miguel Jimenez 40022302
     * COMP249
     * Assignment 1
     * February 4th, 2017
     * 
     * Java class Position: The objects from this class are each one of the
     * positions of the grid.
     *
     * @param row This is the row coordinate of the position.
     * @param column This is the column coordinate of the position.
     * @param type This is the value of what is placed in this position (i.e.
     * 's' if it's a ship that belongs to the user).
     * @param print This is the character that will be printed on the map.
     */
    
    private int row;
    private int column;
    private char type;
    private char print;

    /**
     * Constructor that initializes the position to a specific coordinate (row
     * and column) and gives the stock character '_' to the type and print value
     *
     * @param newRow Value to which row will be set
     * @param newColumn Value to which column will be set
     */
    public Position(int newRow, int newColumn) {

        row = newRow;
        column = newColumn;
        type = '_';
        print = '_';
    }

    /**
     * Sets the row to a new value
     * 
     * @param newRow Value to which row will be set
     */
    public void setRow(int newRow) {

        row = newRow;
    }

    /**
     * Sets the column to a new value
     * 
     * @param newColumn Value to which column will be set
     */
    public void setColumn(int newColumn) {

        column = newColumn;
    }

    /**
     * Sets the positions's type to a new one
     * 
     * @param newType New type to which the position will be set
     */
    public void setValue(char newType) {

        type = newType;
    }

    /**
     * Sets the position's print character to a new one
     * 
     * @param newPrint Print value to which print will be set
     */
    public void setPrint(char newPrint) {

        print = newPrint;
    }

    /**
     * @return The value set for this position (the type of object in the
     * position).
     */
    public char getType() {

        return type;
    }

    /**
     * @return The print value of this position
     */
    public char getPrint() {

        return print;
    }

    /**
     * @return The row in which this position is located
     */
    public int getRow() {

        return row;
    }

    /**
     * @return The column in which this position is located
     */
    public int getColumn() {

        return column;
    }

}
