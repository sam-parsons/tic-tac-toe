import java.util.Scanner;

/**
 * OPEN TICKETS
 * - Account for prematurely ending the ninth move using similar strategy to eighth move
 * 
 * USEFUL DIAGNOSTICS
 * - print out game log to file
 * - use 1,2,3 and A,B,C as markers for keeping track of game history
 */

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        Board board = new Board();
       
        boolean playGame = true;

        int addXRow;
        int addXCol;
        int addORow;
        int addOCol;

        String endGame = "";

        while (playGame) {
            board.generateBoard();

            for (int i = 0; i < 38; i++) System.out.println();
            System.out.println("Welcome to Tic-Tac-Toe");

            boolean gameLoop = true;

            while (gameLoop) {

                board.printBoard();
                if (board.getCount() != 7) {
                    int[] moveArrX = board.nextMove(1);
                    System.out.println("Next Move: " + (moveArrX[0] + 1) + ", " + (moveArrX[1] + 1));

                    System.out.println();
                    System.out.print("Player 'X' - Enter the row to be marked: ");
                    addXRow = scan.nextInt();
                    System.out.print("Player 'X' - Enter the column to be marked: ");
                    addXCol = scan.nextInt();

                    board.addElement(addXRow-1, addXCol-1, 1);

                } else {
                    if (board.canBeWon()) {
                        int[] moveArrX = board.nextMove(1);
                        System.out.println("Next Move: " + (moveArrX[0] + 1) + ", " + (moveArrX[1] + 1));

                        System.out.println();
                        System.out.print("Player 'X' - Enter the row to be marked: ");
                        addXRow = scan.nextInt();
                        System.out.print("Player 'X' - Enter the column to be marked: ");
                        addXCol = scan.nextInt();

                        board.addElement(addXRow-1, addXCol-1, 1);
                    } else {

                    }
                }
                
                if (board.isWon() == -1 && !board.isFull() && board.getCount() != 7) {

                    for (int i = 0; i < 40; i++) System.out.println();
                    board.printBoard();
                    int[] moveArrO = board.nextMove(0);
                    System.out.println("Next Move: " + (moveArrO[0] + 1) + ", " + (moveArrO[1] + 1));

                    System.out.println();
                    System.out.print("Player 'O' - Enter the row to be marked: ");
                    addORow = scan.nextInt();
                    System.out.print("Player 'O' - Enter the column to be marked: ");
                    addOCol = scan.nextInt();

                    board.addElement(addORow-1, addOCol-1, 0);

                } else if (board.isWon() == -1 && !board.isFull() && board.getCount() == 7) {
                    if (board.canBeWon()) {
                        for (int i = 0; i < 40; i++) System.out.println();
                        board.printBoard();
                        int[] moveArrO = board.nextMove(0);
                        System.out.println("Next Move: " + (moveArrO[0] + 1) + ", " + (moveArrO[1] + 1));

                        System.out.println();
                        System.out.print("Player 'O' - Enter the row to be marked: ");
                        addORow = scan.nextInt();
                        System.out.print("Player 'O' - Enter the column to be marked: ");
                        addOCol = scan.nextInt();

                        board.addElement(addORow-1, addOCol-1, 0);
                    } else {

                    }
                }

                if (board.isWon() == 1 || board.isWon() == 0 || board.isFull() || (board.getCount() == 8 && board.canBeWon())) {
                    System.out.println("Game Over");
                    gameLoop = false;
                }

            }

            do {
                System.out.print("Play again? (Y/n): ");
                endGame = scan.next();
                if (endGame.equals("N") || endGame.equals("n")) {
                    for (int i = 0; i < 50; i++) System.out.println();
                    playGame = false;
                }
            } while (!endGame.equals("N") && !endGame.equals("n") && !endGame.equals("Y") && !endGame.equals("y"));

        }
        
        board.printBoard();
        System.out.println();
        System.out.println("GAME OVER");
        // print a winning scenario, or make method for it

    }

}