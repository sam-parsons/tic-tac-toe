import java.util.Scanner;

/**
 * OPEN TICKETS
 * - Account for prematurely ending the ninth move using similar strategy to eighth move
 * - Coerce user input for coordinates
 * - Need to fix end game scenario
 * - Move 7 - There is a scenario right after Move 7 where gameLoop is turned false, but does not have the
 * -          criteria to trigger the end game sorting sequence ** 
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
            System.out.println("\t    Welcome to Tic-Tac-Toe");
            System.out.println();
            System.out.println();


            boolean gameLoop = true;

            while (gameLoop) {

                board.printBoard();
                System.out.println();
                if (board.getCount() != 7) {
                    int[] moveArrX = board.nextMove(1);
                    System.out.println();
                    System.out.println("\t   Suggested Next Move: " + (moveArrX[0] + 1) + ", " + (moveArrX[1] + 1));

                    System.out.println();
                    System.out.println();
                    System.out.print("    Player 'X' - Enter the row to be marked: ");
                    addXRow = scan.nextInt();
                    System.out.print("    Player 'X' - Enter the column to be marked: ");
                    addXCol = scan.nextInt();
                    System.out.println();

                    board.addElement(addXRow-1, addXCol-1, 1);
                    for (int i = 0; i < 38; i++) System.out.println();

                } else {
                    if (board.canBeWon()) {
                        int[] moveArrX = board.nextMove(1);
                        System.out.println("\t   Suggested Next Move: " + (moveArrX[0] + 1) + ", " + (moveArrX[1] + 1));

                        System.out.println();
                        System.out.print("    Player 'X' - Enter the row to be marked: ");
                        addXRow = scan.nextInt();
                        System.out.print("    Player 'X' - Enter the column to be marked: ");
                        addXCol = scan.nextInt();
                        System.out.println();

                        board.addElement(addXRow-1, addXCol-1, 1);
                        for (int i = 0; i < 38; i++) System.out.println();
                    } else {
                        gameLoop = false;
                    }
                } // expand to a check on Move 8
                
                if (board.isWon() == -1 && !board.isFull() && board.getCount() != 7) {

                    board.printBoard();
                    System.out.println();
                    int[] moveArrO = board.nextMove(0);
                    System.out.println("\t   Suggested Next Move: " + (moveArrO[0] + 1) + ", " + (moveArrO[1] + 1));

                    System.out.println();
                    System.out.print("    Player 'O' - Enter the row to be marked: ");
                    addORow = scan.nextInt();
                    System.out.print("    Player 'O' - Enter the column to be marked: ");
                    addOCol = scan.nextInt();
                    System.out.println();

                    board.addElement(addORow-1, addOCol-1, 0);
                    for (int i = 0; i < 38; i++) System.out.println();

                } else if (board.isWon() == -1 && !board.isFull() && board.getCount() == 7) {
                    // System.out.println("Eight pieces on board");
                    if (board.canBeWon()) {
                        board.printBoard();
                        System.out.println();
                        int[] moveArrO = board.nextMove(0);
                        System.out.println("\t   Suggested Next Move: " + (moveArrO[0] + 1) + ", " + (moveArrO[1] + 1));

                        System.out.println();
                        System.out.print("    Player 'O' - Enter the row to be marked: ");
                        addORow = scan.nextInt();
                        System.out.print("    Player 'O' - Enter the column to be marked: ");
                        addOCol = scan.nextInt();
                        System.out.println();

                        board.addElement(addORow-1, addOCol-1, 0);
                        for (int i = 0; i < 38; i++) System.out.println();
                    } else {
                        gameLoop = false;
                    }
                } // expand to a check on Move 8

                if (board.isWon() == 1 || board.isWon() == 0 || board.isFull() || (board.getCount() == 8 && !board.canBeWon())) {
                    int whoWon = board.isWon();
                    board.printBoard();
                    System.out.println("\t\tGame Over");
                    System.out.println();
                    if (whoWon == -1) {
                        System.out.println("\t\tDRAW");
                    } else if (whoWon == 0) {
                        System.out.println("\t\tO WINS");
                    } else if (whoWon == 1) {
                        System.out.println("\t\tX WINS");
                    }
                    System.out.println();
                    gameLoop = false;
                }

            }

            do {
                System.out.print("\t\tPlay again? (Y/n): ");
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