package tictactoe;

public class Main {
    /**
     * This is start of the program. The game field is blank, X make first move.
     */
    public static void main(String[] args) {
        char turn = 'X';
        String input = "         ";
        Board.setGame(input);
        Board.nextMove(turn);
    }
}