package tictactoe;

public class Main {
    public static void main(String[] args) {
        char turn = 'X';
        String input = "         ";
        Board.setGame(input);
        Board.nextMove(turn);
    }
}