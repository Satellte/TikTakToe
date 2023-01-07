package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Board {
    private static char[] input;
    private static Boolean xWin = false;
    private static Boolean oWin = false;
    private static Boolean draw = false;
    private static char turn = 'X';

    /**
     * this method make starting position on game field
     * @param startInput
     */
    public static void setGame(String startInput) {
        input = startInput.toCharArray();
        getBoard();
    }

    /**
     * this method show current game field
     */
    public static void getBoard(){
        System.out.println("---------");
        System.out.println("| " + input[0] + " " + input[1] + " " + input[2] + " |");
        System.out.println("| " + input[3] + " " + input[4] + " " + input[5] + " |");
        System.out.println("| " + input[6] + " " + input[7] + " " + input[8] + " |");
        System.out.println("---------");
    }

    /**
     * This method take 2 integer variable. These variables are coordinates for next move.
     * Method checks the input variables to make sure they match the condition.
     * When exceptions occur, the method catch them and displays correct messages.
     * If all coordinates is correct, method call another method for check
     * @param turn the variable shows whose turn make a move
     * @throws InputMismatchException
     */
    public static void nextMove(char turn) throws InputMismatchException {
        Scanner scanner = new Scanner(System.in);
        int nextMoveA = 0;
        int nextMoveB = 0;
        try {
            nextMoveA = scanner.nextInt();
            nextMoveB = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("You should enter numbers!");
            nextMove(turn);
        }
        if ((nextMoveA < 1)||(nextMoveA > 3) || (nextMoveB < 1)||(nextMoveB > 3)){
            System.out.println("Coordinates should be from 1 to 3!");
            nextMove(turn);
        }
        try {
            switch (nextMoveA) {
                case 1 -> {
                    if (input[nextMoveB - 1] == ' '){
                        input[nextMoveB - 1] = turn;
                        getBoard();
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                        nextMove(turn);
                    }
                }
                case 2 -> {
                    if (input[nextMoveB + 2] == ' ') {
                        input[nextMoveB + 2] = turn;
                        getBoard();
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                        nextMove(turn);
                    }
                }
                case 3 -> {
                    if (input[nextMoveB + 5] == ' ') {
                        input[nextMoveB + 5] = turn;
                        getBoard();
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                        nextMove(turn);
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Out of border");
        }
        checkGame();
    }

    /**
     * This method checks the current state of the game.
     * If there is a winner or a draw, method will show message and the game will stop.
     * If there is no winner yet and there are free moves, the game continues.
     */
    public static void checkGame(){
        int x = 0;
        int o = 0;
        int freeMove = 0;
        for (int i = 0; i < 9; i++) {
            if (input[i] == 'X') {
                x++;
            } else if (input[i] == 'O') {
                o++;
            } else if (input[i] == ' ') {
                freeMove++;
            }
        }
        freeMove = 9 - x - o;
        if (freeMove == 0) {
            draw = true;
        }
        int difference = x - o;
        if (difference == 1) {
            xWin();
        } else if (difference == 0) {
            oWin();
        }
        }

    /**
     * This method checks the win conditions for X
     * If the conditions are not met, then the turn change to O
     */
    public static void xWin(){
        for (int i = 1; i < 8; i = i + 3) {
            if (input[i] == 'X'){
                if ((input[i - 1] == input[i]) && (input[i + 1] == input[i])) {
                    xWin = true;
                }
            }

            if (input[4] == 'X'){
                if ((input[0] == input[4]) && (input[8] == input[4]) || (input[2] == input[4]) &&
                        (input[2] == input[6])) {
                    xWin = true;
                }
            }

            for (int j = 0; j < 3; j = j + 1) {
                if (input[j] == 'X') {
                    if ((input[j] == input[j + 3]) && (input[j] == input[j + 6])) {
                        xWin = true;
                    }
                }
            }
        }
        if (xWin) {
            System.out.println("X wins");
            System.exit(0);
        } else if ((!xWin) && (draw)){
            System.out.println("Draw");
            System.exit(0);
        } else if ((!xWin) && (!draw)){
            turn = 'O';
            nextMove(turn);
        }
    }

    /**
     * This method checks the win conditions for O
     * If the conditions are not met, then the turn change to X
     */
    public static void oWin(){
        for (int i = 1; i < 8; i = i + 3) {
            if (input[i] == 'O'){
                if ((input[i - 1] == input[i]) && (input[i + 1] == input[i])) {
                    if (input[i] == 'O') {
                        oWin = true;
                    }
                }
            }
            if (input[4] == 'O'){
                if ((input[0] == input[4]) && (input[8] == input[4]) || (input[2] == input[4]) &&
                        (input[2] == input[6])) {
                    oWin = true;
                }
            }
            for (int j = 0; j < 3; j = j + 1) {
                if (input[j] == 'O') {
                    if ((input[j] == input[j + 3]) && (input[j] == input[j + 6])) {
                        oWin = true;
                    }
                }
            }
        }
        if (oWin) {
            System.out.println("O wins");
            System.exit(0);
        } else if ((!oWin) && (draw)){
            System.out.println("Draw");
            System.exit(0);
        } else if ((!oWin) && (!draw)){
            turn = 'X';
            nextMove(turn);
        }
    }
}