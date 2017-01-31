package battleship;

import java.util.Random;
import java.util.Scanner;

/**
 * Miguel Jimenez 40022302
 * COMP249
 * Assignment 1
 * February 4th, 2017
 * 
 * User vs computer Battleship game on an 8x8 grid. The user and the computer
 * have 6 ships and 4 grenades each, and the goal is to sink the other player's
 * ships. If a grenade is hit, the player who shot it looses a turn, so by
 * consequence, the other player plays twice in a row.
 *
 * @author Miguel Jimenez
 * @version 1.0 - January 2017
 * @since 1.0
 */

public class Battleship {

    /**
     * Java class Battleship: This class starts the game by creating a Map
     * object. Then, it asks the player to place his/her ships and grenades.
     * After that, the computer places its own randomly. After that, the user is
     * prompted to shoot at a position. Next, the computer shoots at a random
     * position, and the game keeps going until - as stated above - one of the
     * players has sunk in all of the other player's ships.
     * 
     * @param map Map class object that creates the game's grid with the
     * positions.
     */

    private Map map;

    public Battleship(int rows, int columns, int ships, int grenades) {

        map = new Map(rows, columns, ships, grenades);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        //Creates new Battleship game
        Battleship game = new Battleship(8, 8, 6, 4);

        System.out.println("Hi, let’s play Battleship!");

        //Asks the user to place his ships
        for (int i = 1; i <= game.map.getNumShips(); i++) {

            System.out.print("Enter the coordinates of your ship #" + i + ": ");
            game.map.registerPosition(scanner.next(), i, "ship");
        }

        System.out.println();

        //Asks the user to place his grenades
        for (int i = 1; i <= game.map.getNumGrenades(); i++) {

            System.out.print("Enter the coordinates of your grenade #" + i + ": ");
            game.map.registerPosition(scanner.next(), i, "grenade");
        }

        //Places the computer's ships
        for (int i = 1; i <= game.map.getNumShips(); i++) {
            game.map.registerPosition(random.nextInt(8), random.nextInt(8), "Ship");
        }

        //Places the comuter's grenades
        for (int i = 1; i <= game.map.getNumGrenades(); i++) {
            game.map.registerPosition(random.nextInt(8), random.nextInt(8), "Grenade");
        }

        System.out.println("\nOK, the computer placed its ships and grenades at random. Let’s play.\n");

        //While none of the players have sunk in the other player's ships
        while (game.map.getNumShips() != game.map.getUserShipsHit()
                && game.map.getNumShips() != game.map.getUserShipsHit()) {

            if (game.map.getUserTurn()) {
                game.map.shoot();
            } else {
                game.map.setUserTurn(true);
            }

            if (game.map.getNumShips() == game.map.getUserShipsHit()
                    && game.map.getNumShips() == game.map.getUserShipsHit()) {
                break;
            }

            if (game.map.getComputerTurn()) {
                game.map.shoot(random.nextInt(8), random.nextInt(8));
            } else {
                game.map.setComputerTurn(true);
            }

        }

        System.out.println(game.map.declareWinner());

    }

}
