import java.util.Random;

public class Board {

    int[][] gameBoard;

    public void generateBoard() {
        gameBoard = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameBoard[i][j] = -1;
            }
        }
    }

    public void addElement(int row, int column, int symbol) {
        if (symbol == 0) {
            gameBoard[row][column] = 0;
        } else if (symbol == 1) {
            gameBoard[row][column] = 1;
        }
    }

    public boolean isEmpty() {
        boolean isEmpty = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameBoard[i][j] >= 0) {
                    isEmpty = false;
                }
            }
        }
        return isEmpty;
    }

    public boolean isFull() {
        boolean isFull = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameBoard[i][j] < 0) {
                    isFull = false;
                }
            }
        }
        return isFull;
    }

    // returns -1 for a game that is not one, or returns 0 or 1 for a won game
    public int isWon() {
        int isWon = -1;
        for (int i = 0; i < 3; i++) {
            if ((gameBoard[i][0] == gameBoard[i][1]) && (gameBoard[i][0] == gameBoard[i][2]) && isWon < 0) {
                isWon = gameBoard[i][0];
            } else if ((gameBoard[0][i] == gameBoard[1][i]) && (gameBoard[0][i] == gameBoard[2][i]) && isWon < 0) {
                isWon = gameBoard[0][i];
            } else if (i == 0 && (gameBoard[i][i] == gameBoard[i+1][i+1]) && (gameBoard[i][i] == gameBoard[i+2][i+2]) && isWon < 0) {
                isWon = gameBoard[i][i];
            } else if (i == 2 && (gameBoard[i][i-2] == gameBoard[i-1][i-1]) && (gameBoard[i][i-2] == gameBoard[i-2][i]) && isWon < 0) {
                isWon = gameBoard[i][i-2];
            }
        }
        return isWon;
    }

    public int getCount() {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameBoard[i][j] >= 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public void printBoard() {

        System.out.print(" ");
        for (int i = 0; i < 11; i++) {
            if (i == 2 || i == 6 || i == 10) {
                System.out.printf("%d", (int)Math.floor((i-2)/3) + 1);
            } else {
                System.out.print(" ");
            }
        }

        for (int i = 0; i < 9; i++) {
            System.out.println();
            if (i == 1 || i == 4 || i == 7) {
                System.out.printf("%d", (int)Math.floor((i-1)/3) + 1);
            } else {
                System.out.print(" ");
            }
            System.out.print(" ");
            for (int j = 0; j < 11; j++) {
                if (i == 0 || i == 3 || i == 6 || i == 8) {
                    if (j == 3 || j == 7) {
                        System.out.print("|");
                    } else {
                        System.out.print(" ");
                    }
                } else if (i == 2 || i == 5) {
                    if (j == 3 || j == 7) {
                        System.out.print("|");
                    } else {
                        System.out.print("_");
                    }
                } else if (i == 1 || i == 4 || i ==7) {
                    if (j == 3 || j == 7) {
                        System.out.print("|");
                    } else if (j == 1 || j == 5 || j ==9) {
                        if (gameBoard[(int)Math.floor(i / 3)][(int)Math.floor((j-1) / 3)] == 0) {
                            System.out.print("O");
                        } else if (gameBoard[(int)Math.floor(i / 3)][(int)Math.floor((j-1) / 3)] == 1) {
                            System.out.print("X");
                        } else {
                            System.out.print("_");
                        }
                    } else {
                        System.out.print(" ");
                    }
                }
            }
        }
        System.out.println();
    }

    public int[] nextMove(int symbol) {
        int move = this.getCount();
        System.out.println("Move: " + (move + 1));
        int[] finalMove = new int[2];
        if (move == 0) {
            finalMove[0] = 1;
            finalMove[1] = 1;
            return finalMove;
        } else if (move == 1) {
            if (gameBoard[1][1] >= 0) {
                // pick any open corner
                return finalMove;
            } else {
                finalMove[0] = 1;
                finalMove[1] = 1;
                return finalMove;
            }
        } else if (move == 2) { // make adjacent move
            if (gameBoard[1][1] == symbol) {
                int opposite;
                if (gameBoard[1][1] == 0) {
                    opposite = 1;
                } else {
                    opposite = 0;
                }
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (gameBoard[i][j] == opposite) {

                            if (i == 0 && j == 0) {
                                if (gameBoard[i][j+1] == -1) {
                                    finalMove[0] = i;
                                    finalMove[1] = j+1;
                                    return finalMove;
                                } else {
                                    finalMove[0] = i+1;
                                    finalMove[1] = j;
                                    return finalMove;
                                }
                            } else if (i == 0 && j == 1) {
                                if (gameBoard[i][j-1] == -1) {
                                    finalMove[0] = i;
                                    finalMove[1] = j-1;
                                    return finalMove;
                                } else {
                                    finalMove[0] = i;
                                    finalMove[1] = j+1;
                                    return finalMove;
                                }
                            } else if (i == 0 && j == 2) {
                                if (gameBoard[i][j-1] == -1) {
                                    finalMove[0] = i;
                                    finalMove[1] = j-1;
                                    return finalMove;
                                } else {
                                    finalMove[0] = i+1;
                                    finalMove[1] = j;
                                    return finalMove;
                                }
                            } if (i == 1 && j == 0) {
                                if (gameBoard[i-1][j] == -1) {
                                    finalMove[0] = i-1;
                                    finalMove[1] = j;
                                    return finalMove;
                                } else {
                                    finalMove[0] = i+1;
                                    finalMove[1] = j;
                                    return finalMove;
                                }
                            } else if (i == 1 && j == 2) {
                                if (gameBoard[i-1][j] == -1) {
                                    finalMove[0] = i-1;
                                    finalMove[1] = j;
                                    return finalMove;
                                } else {
                                    finalMove[0] = i+1;
                                    finalMove[1] = j;
                                    return finalMove;
                                }
                            } if (i == 2 && j == 0) {
                                if (gameBoard[i-1][j] == -1) {
                                    finalMove[0] = i-1;
                                    finalMove[1] = j;
                                    return finalMove;
                                } else {
                                    finalMove[0] = i;
                                    finalMove[1] = j+1;
                                    return finalMove;
                                }
                            } else if (i == 2 && j == 1) {
                                if (gameBoard[i][j-1] == -1) {
                                    finalMove[0] = i;
                                    finalMove[1] = j-1;
                                    return finalMove;
                                } else {
                                    finalMove[0] = i;
                                    finalMove[1] = j+1;
                                    return finalMove;
                                }
                            } else if (i == 2 && j == 2) {
                                if (gameBoard[i][j-1] == -1) {
                                    finalMove[0] = i;
                                    finalMove[1] = j-1;
                                    return finalMove;
                                } else {
                                    finalMove[0] = i-1;
                                    finalMove[1] = j;
                                    return finalMove;
                                }
                            }
                        }
                    }
                }
            } else if (gameBoard[1][1] == -1) {
                finalMove[0] = 1;
                finalMove[1] = 1;
                return finalMove;
            } else {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        
                        if (i == 0 && j == 0) {
                            if (gameBoard[i][j+1] == -1) {
                                finalMove[0] = i;
                                finalMove[1] = j+1;
                                return finalMove;
                            } else {
                                finalMove[0] = i+1;
                                finalMove[1] = j;
                                return finalMove;
                            }
                        } else if (i == 0 && j == 1) {
                            if (gameBoard[i][j-1] == -1) {
                                finalMove[0] = i;
                                finalMove[1] = j-1;
                                return finalMove;
                            } else {
                                finalMove[0] = i;
                                finalMove[1] = j+1;
                                return finalMove;
                            }
                        } else if (i == 0 && j == 2) {
                            if (gameBoard[i][j-1] == -1) {
                                finalMove[0] = i;
                                finalMove[1] = j-1;
                                return finalMove;
                            } else {
                                finalMove[0] = i+1;
                                finalMove[1] = j;
                                return finalMove;
                            }
                        } if (i == 1 && j == 0) {
                            if (gameBoard[i-1][j] == -1) {
                                finalMove[0] = i-1;
                                finalMove[1] = j;
                                return finalMove;
                            } else {
                                finalMove[0] = i+1;
                                finalMove[1] = j;
                                return finalMove;
                            }
                        } else if (i == 1 && j == 2) {
                            if (gameBoard[i-1][j] == -1) {
                                finalMove[0] = i-1;
                                finalMove[1] = j;
                                return finalMove;
                            } else {
                                finalMove[0] = i+1;
                                finalMove[1] = j;
                                return finalMove;
                            }
                        } if (i == 2 && j == 0) {
                            if (gameBoard[i-1][j] == -1) {
                                finalMove[0] = i-1;
                                finalMove[1] = j;
                                return finalMove;
                            } else {
                                finalMove[0] = i;
                                finalMove[1] = j+1;
                                return finalMove;
                            }
                        } else if (i == 2 && j == 1) {
                            if (gameBoard[i][j-1] == -1) {
                                finalMove[0] = i;
                                finalMove[1] = j-1;
                                return finalMove;
                            } else {
                                finalMove[0] = i;
                                finalMove[1] = j+1;
                                return finalMove;
                            }
                        } else if (i == 2 && j == 2) {
                            if (gameBoard[i][j-1] == -1) {
                                finalMove[0] = i;
                                finalMove[1] = j-1;
                                return finalMove;
                            } else {
                                finalMove[0] = i-1;
                                finalMove[1] = j;
                                return finalMove;
                            }
                        }
                    }
                }
            }
        } else if (move == 3) {
            System.out.println("Move 3");
            if (gameBoard[1][1] != -1) {
                int opposite;
                if (symbol == 0) {
                    opposite = 1;
                } else {
                    opposite = 0;
                }

                int[] moveArr = new int[4];
                for (int i = 0; i < 4; i++) moveArr[i] = -1;
                int[] first = new int[2];
                int[] second = new int[2];

                first[0] = -1;
                first[1] = -1;
                second[0] = -1;
                second[1] = -1;

                for (int i = 0; i < 3; i ++) {
                    for (int j = 0; j < 3; j++) {
                        if (gameBoard[i][j] == opposite) {
                            if (first[0] == -1) {
                                first[0] = i;
                                first[1] = j;
                            }
                        }
                    }
                }

                System.out.println("Opposite coordinates: " + first[0] + ", " + first[1]);

                if (this.isAdjacent(first[0], first[1], opposite)) {

                    int[] adjArr = new int[2];
                    adjArr = getAdjacent(first[0], first[1]);
                    for (int i = 0; i < 2; i++) {
                        System.out.println(adjArr[i]);
                    }
                    moveArr[0] = first[0];
                    moveArr[1] = first[1];
                    moveArr[2] = adjArr[0];
                    moveArr[3] = adjArr[1];
                    System.out.println("Adjacent coordinates: " + adjArr[0] + ", " + adjArr[1]);

                }

                int[] tempArr = new int[2];
                    for (int i = 0; i < 2; i++) tempArr[i] = -1;
                    for (int i = 0; i < 3; i ++) {
                        for (int j = 0; j < 3; j++) {
                            if (gameBoard[i][j] == symbol) {
                                if (tempArr[0] == -1) {
                                    tempArr[0] = i;
                                    tempArr[1] = j;
                                }
                            }
                        }
                    }

                if (moveArr[0] != -1) {

                    System.out.println("Same symbol coordinates: ");
                    for (int i = 0; i < 2; i++) System.out.println(tempArr[i]);

                    if (this.isPowerBloc(moveArr)) {
                        System.out.println("PowerBloc");

                        int[] powerPoints = new int[2];
                        powerPoints = this.getPowerPoint(moveArr);
                        finalMove[0] = powerPoints[0];
                        finalMove[1] = powerPoints[1];
                        return finalMove;

                    } else {

                        int temp1 = -1;
                        int temp2 = -1;
                        Random rand = new Random();

                        do {
                            temp1 = rand.nextInt(3);
                            temp2 = rand.nextInt(3);
                            
                        } while (gameBoard[temp1][temp2] == -1 && !this.isPairAdjacent(tempArr[0], tempArr[1], temp1, temp2));

                        finalMove[0] = temp1;
                        finalMove[1] = temp2;
                        return finalMove;  

                    }

                } else {

                    int temp1 = -1;
                    int temp2 = -1;
                    Random rand = new Random();

                    do {
                        temp1 = rand.nextInt(3);
                        temp2 = rand.nextInt(3);
                        
                    } while (gameBoard[temp1][temp2] == -1 && !this.isPairAdjacent(tempArr[0], tempArr[1], temp1, temp2));

                    finalMove[0] = temp1;
                    finalMove[1] = temp2;
                    return finalMove; 
                }

            } else {
                finalMove[0] = 1;
                finalMove[1] = 1;
                System.out.println("Center not taken");
                return finalMove; 
            }   

        } else if (move == 4) {

        } else { // unfinished
            finalMove[0] = 1;
            finalMove[1] = 1;
            return finalMove;
        }
        return finalMove;
    }

    public boolean isAdjacent(int row, int col, int symbol) {
        boolean isAdjacent = false;

        if (row == 0 && col == 0) {
            if (gameBoard[0][1] == symbol) {
                isAdjacent = true;
            } else if (gameBoard[1][0] == symbol) {
                isAdjacent = true;
            } else if (gameBoard[1][1] == symbol) {
                isAdjacent = true;
            }
        } else if (row == 0 && col == 1) {
            if (gameBoard[0][0] == symbol) {
                isAdjacent = true;
            } else if (gameBoard[0][2] == symbol) {
                isAdjacent = true;
            } else if (gameBoard[1][1] == symbol) {
                isAdjacent = true;
            }
        } else if (row == 0 && col == 2) {
            if (gameBoard[0][1] == symbol) {
                isAdjacent = true;
            } else if (gameBoard[1][2] == symbol) {
                isAdjacent = true;
            } else if (gameBoard[1][1] == symbol) {
                isAdjacent = true;
            }
        } else if (row == 1 && col == 0) {
            if (gameBoard[0][0] == symbol) {
                isAdjacent = true;
            } else if (gameBoard[2][0] == symbol) {
                isAdjacent = true;
            } else if (gameBoard[1][1] == symbol) {
                isAdjacent = true;
            }
        } else if (row == 1 && col == 1) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (gameBoard[i][j] == symbol && (i != 1 && j != 1)) {
                        isAdjacent = true;
                    }
                }
            }
        } else if (row == 1 && col == 2) {
            if (gameBoard[0][2] == symbol) {
                isAdjacent = true;
            } else if (gameBoard[2][2] == symbol) {
                isAdjacent = true;
            } else if (gameBoard[1][1] == symbol) {
                isAdjacent = true;
            }
        } else if (row == 2 && col == 0) {
            if (gameBoard[1][0] == symbol) {
                isAdjacent = true;
            } else if (gameBoard[2][1] == symbol) {
                isAdjacent = true;
            } else if (gameBoard[1][1] == symbol) {
                isAdjacent = true;
            }
        } else if (row == 2 && col == 1) {
            if (gameBoard[2][0] == symbol) {
                isAdjacent = true;
            } else if (gameBoard[2][2] == symbol) {
                isAdjacent = true;
            } else if (gameBoard[1][1] == symbol) {
                isAdjacent = true;
            }
        } else if (row == 2 && col == 2) {
            if (gameBoard[2][1] == symbol) {
                isAdjacent = true;
            } else if (gameBoard[1][2] == symbol) {
                isAdjacent = true;
            } else if (gameBoard[1][1] == symbol) {
                isAdjacent = true;
            }
        }

        return isAdjacent;
    }

    public boolean isPairAdjacent(int row1, int col1, int row2, int col2) {
        boolean isPairAdjacent = false;

        if (row1 == 0 && col1 == 0) {
            if (row2 == 0 && col2 == 1 && (gameBoard[row1][col1] == gameBoard[row2][col2])) {
                isPairAdjacent = true;
            } else if (row2 == 1 && col2 == 0 && (gameBoard[row1][col1] == gameBoard[row2][col2])) {
                isPairAdjacent = true;
            } else if (row2 == 1 && col2 == 1 && (gameBoard[row1][col1] == gameBoard[row2][col2])) {
                isPairAdjacent = true;
            }
        } else if (row1 == 0 && col1 == 1) {
            if (row2 == 0 && col2 == 0 && (gameBoard[row1][col1] == gameBoard[row2][col2])) {
                isPairAdjacent = true;
            } else if (row2 == 0 && col2 == 2 && (gameBoard[row1][col1] == gameBoard[row2][col2])) {
                isPairAdjacent = true;
            } else if (row2 == 1 && col2 == 1 && (gameBoard[row1][col1] == gameBoard[row2][col2])) {
                isPairAdjacent = true;
            }
        } else if (row1 == 0 && col1 == 2) {
            if (row2 == 0 && col2 == 1 && (gameBoard[row1][col1] == gameBoard[row2][col2])) {
                isPairAdjacent = true;
            } else if (row2 == 1 && col2 == 2 && (gameBoard[row1][col1] == gameBoard[row2][col2])) {
                isPairAdjacent = true;
            } else if (row2 == 1 && col2 == 1 && (gameBoard[row1][col1] == gameBoard[row2][col2])) {
                isPairAdjacent = true;
            }
        } else if (row1 == 1 && col1 == 0) {
            if (row2 == 0 && col2 == 0 && (gameBoard[row1][col1] == gameBoard[row2][col2])) {
                isPairAdjacent = true;
            } else if (row2 == 2 && col2 == 0 && (gameBoard[row1][col1] == gameBoard[row2][col2])) {
                isPairAdjacent = true;
            } else if (row2 == 1 && col2 == 1 && (gameBoard[row1][col1] == gameBoard[row2][col2])) {
                isPairAdjacent = true;
            }
        } else if (row1 == 1 && col1 == 1) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (row2 == i && col2 == j && (gameBoard[row1][col1] == gameBoard[row2][col2])) {
                        isPairAdjacent = true;
                    }
                }
            }
        } else if (row1 == 1 && col1 == 2) {
            if (row2 == 0 && col2 == 2 && (gameBoard[row1][col1] == gameBoard[row2][col2])) {
                isPairAdjacent = true;
            } else if (row2 == 2 && col2 == 2 && (gameBoard[row1][col1] == gameBoard[row2][col2])) {
                isPairAdjacent = true;
            } else if (row2 == 1 && col2 == 1 && (gameBoard[row1][col1] == gameBoard[row2][col2])) {
                isPairAdjacent = true;
            }
        } else if (row1 == 2 && col1 == 0) {
            if (row2 == 1 && col2 == 0 && (gameBoard[row1][col1] == gameBoard[row2][col2])) {
                isPairAdjacent = true;
            } else if (row2 == 2 && col2 == 1 && (gameBoard[row1][col1] == gameBoard[row2][col2])) {
                isPairAdjacent = true;
            } else if (row2 == 1 && col2 == 1 && (gameBoard[row1][col1] == gameBoard[row2][col2])) {
                isPairAdjacent = true;
            }
        } else if (row1 == 2 && col1 == 1) {
            if (row2 == 2 && col2 == 0 && (gameBoard[row1][col1] == gameBoard[row2][col2])) {
                isPairAdjacent = true;
            } else if (row2 == 2 && col2 == 2 && (gameBoard[row1][col1] == gameBoard[row2][col2])) {
                isPairAdjacent = true;
            } else if (row2 == 1 && col2 == 1 && (gameBoard[row1][col1] == gameBoard[row2][col2])) {
                isPairAdjacent = true;
            }
        } else if (row1 == 2 && col1 == 2) {
            if (row2 == 2 && col2 == 1 && (gameBoard[row1][col1] == gameBoard[row2][col2])) {
                isPairAdjacent = true;
            } else if (row2 == 1 && col2 == 2 && (gameBoard[row1][col1] == gameBoard[row2][col2])) {
                isPairAdjacent = true;
            } else if (row2 == 1 && col2 == 1 && (gameBoard[row1][col1] == gameBoard[row2][col2])) {
                isPairAdjacent = true;
            }
        }

        return isPairAdjacent;
    }

    public int[] getAdjacent(int row, int col) {
        int[] adjacent = new int[2];

        adjacent[0] = -1;
        adjacent[1] = -1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ((i != row || j != col) && this.isPairAdjacent(row,col,i,j)) {
                    if (adjacent[0] == -1) {
                        adjacent[0] = i;
                        adjacent[1] = j;    
                    }
                }
            }
        }

        return adjacent;
    }

    // checks if two adjacent spaces have a corresponding open space that would
    // be able to form an inline block of three, possible winning solution
    public boolean isPowerBloc(int[] pointArr) {
        boolean isPowerBloc = false;

        int row1 = pointArr[0];
        int col1 = pointArr[1];
        int row2 = pointArr[2];
        int col2 = pointArr[3];

        if (row1 == 0 && col1 == 0) {
            if (row2 == 1 && col2 == 0) {
                if (gameBoard[2][0] == -1) {
                    isPowerBloc = true;
                }
            } else if (row2 == 0 && col2 == 1) {
                if (gameBoard[0][2] == -1) {
                    isPowerBloc = true;
                }
            } else if (row2 == 1 && col2 == 1) {
                if (gameBoard[2][2] == -1) {
                    isPowerBloc = true;
                }
            }
        } else if (row1 == 0 && col1 == 1) {
            if (row2 == 0 && col2 == 0) {
                if (gameBoard[0][2] == -1) {
                    isPowerBloc = true;
                }
            } else if (row2 == 0 && col2 == 2) {
                if (gameBoard[0][0] == -1) {
                    isPowerBloc = true;
                }
            } else if (row2 == 1 && col2 == 1) {
                if (gameBoard[2][1] == -1) {
                    isPowerBloc = true;
                }
            }
        } else if (row1 == 0 && col1 == 2) {
            if (row2 == 0 && col2 == 1) {
                if (gameBoard[0][0] == -1) {
                    isPowerBloc = true;
                }
            } else if (row2 == 1 && col2 == 2) {
                if (gameBoard[2][2] == -1) {
                    isPowerBloc = true;
                }
            } else if (row2 == 1 && col2 == 1) {
                if (gameBoard[2][0] == -1) {
                    isPowerBloc = true;
                }
            }
        } else if (row1 == 1 && col1 == 0) {
            if (row2 == 0 && col2 == 0) {
                if (gameBoard[2][0] == -1) {
                    isPowerBloc = true;
                }
            } else if (row2 == 2 && col2 == 0) {
                if (gameBoard[0][0] == -1) {
                    isPowerBloc = true;
                }
            } else if (row2 == 1 && col2 == 1) {
                if (gameBoard[1][2] == -1) {
                    isPowerBloc = true;
                }
            }
        } else if (row1 == 1 && col1 == 1) {
            if (row2 == 0 && col2 == 0) {
                if (gameBoard[2][2] == -1) {
                    isPowerBloc = true;
                }
            } else if (row2 == 0 && col2 == 1) {
                if (gameBoard[2][1] == -1) {
                    isPowerBloc = true;
                }
            } else if (row2 == 0 && col2 == 2) {
                if (gameBoard[2][0] == -1) {
                    isPowerBloc = true;
                }
            } else if (row2 == 1 && col2 == 0) {
                if (gameBoard[1][2] == -1) {
                    isPowerBloc = true;
                }
            } else if (row2 == 1 && col2 == 2) {
                if (gameBoard[1][0] == -1) {
                    isPowerBloc = true;
                }
            } else if (row2 == 2 && col2 == 0) {
                if (gameBoard[0][2] == -1) {
                    isPowerBloc = true;
                }
            } else if (row2 == 2 && col2 == 1) {
                if (gameBoard[0][1] == -1) {
                    isPowerBloc = true;
                }
            } else if (row2 == 2 && col2 == 2) {
                if (gameBoard[0][0] == -1) {
                    isPowerBloc = true;
                }
            }
        } else if (row1 == 1 && col1 == 2) {
            if (row2 == 0 && col2 == 2) {
                if (gameBoard[2][2] == -1) {
                    isPowerBloc = true;
                }
            } else if (row2 == 2 && col2 == 2) {
                if (gameBoard[0][2] == -1) {
                    isPowerBloc = true;
                }
            } else if (row2 == 1 && col2 == 1) {
                if (gameBoard[1][0] == -1) {
                    isPowerBloc = true;
                }
            }
        } else if (row1 == 2 && col1 == 0) {
            if (row2 == 1 && col2 == 0) {
                if (gameBoard[0][0] == -1) {
                    isPowerBloc = true;
                }
            } else if (row2 == 2 && col2 == 1) {
                if (gameBoard[2][2] == -1) {
                    isPowerBloc = true;
                }
            } else if (row2 == 1 && col2 == 1) {
                if (gameBoard[0][2] == -1) {
                    isPowerBloc = true;
                }
            }
        } else if (row1 == 2 && col1 == 1) {
            if (row2 == 2 && col2 == 0) {
                if (gameBoard[2][2] == -1) {
                    isPowerBloc = true;
                }
            } else if (row2 == 2 && col2 == 2) {
                if (gameBoard[2][0] == -1) {
                    isPowerBloc = true;
                }
            } else if (row2 == 1 && col2 == 1) {
                if (gameBoard[0][1] == -1) {
                    isPowerBloc = true;
                }
            }
        } else if (row1 == 2 && col1 == 2) {
            if (row2 == 2 && col2 == 1) {
                if (gameBoard[2][0] == -1) {
                    isPowerBloc = true;
                }
            } else if (row2 == 1 && col2 == 2) {
                if (gameBoard[0][2] == -1) {
                    isPowerBloc = true;
                }
            } else if (row2 == 1 && col2 == 1) {
                if (gameBoard[0][0] == -1) {
                    isPowerBloc = true;
                }
            }
        }

        return isPowerBloc;
    }

    public int[] getPowerPoint(int[] moveArr) {
        int[] powerPoint = new int[2];

        powerPoint[0] = -1;
        powerPoint[1] = -1;

        int row1 = moveArr[0];
        int col1 = moveArr[1];
        int row2 = moveArr[2];
        int col2 = moveArr[3];

        if (row1 == 0 && col1 == 0) {
            if (row2 == 1 && col2 == 0) {
                if (gameBoard[2][0] == -1) {
                    powerPoint[0] = 2;
                    powerPoint[1] = 0;
                }
            } else if (row2 == 0 && col2 == 1) {
                if (gameBoard[0][2] == -1) {
                    powerPoint[0] = 0;
                    powerPoint[1] = 2;
                }
            } else if (row2 == 1 && col2 == 1) {
                if (gameBoard[2][2] == -1) {
                    powerPoint[0] = 2;
                    powerPoint[1] = 2;
                }
            }
        } else if (row1 == 0 && col1 == 1) {
            if (row2 == 0 && col2 == 0) {
                if (gameBoard[0][2] == -1) {
                    powerPoint[0] = 0;
                    powerPoint[1] = 2;
                }
            } else if (row2 == 0 && col2 == 2) {
                if (gameBoard[0][0] == -1) {
                    powerPoint[0] = 0;
                    powerPoint[1] = 0;
                }
            } else if (row2 == 1 && col2 == 1) {
                if (gameBoard[2][1] == -1) {
                    powerPoint[0] = 2;
                    powerPoint[1] = 1;
                }
            }
        } else if (row1 == 0 && col1 == 2) {
            if (row2 == 0 && col2 == 1) {
                if (gameBoard[0][0] == -1) {
                    powerPoint[0] = 0;
                    powerPoint[1] = 0;
                }
            } else if (row2 == 1 && col2 == 2) {
                if (gameBoard[2][2] == -1) {
                    powerPoint[0] = 2;
                    powerPoint[1] = 2;
                }
            } else if (row2 == 1 && col2 == 1) {
                if (gameBoard[2][0] == -1) {
                    powerPoint[0] = 2;
                    powerPoint[1] = 0;
                }
            }
        } else if (row1 == 1 && col1 == 0) {
            if (row2 == 0 && col2 == 0) {
                if (gameBoard[2][0] == -1) {
                    powerPoint[0] = 2;
                    powerPoint[1] = 0;
                }
            } else if (row2 == 2 && col2 == 0) {
                if (gameBoard[0][0] == -1) {
                    powerPoint[0] = 0;
                    powerPoint[1] = 0;
                }
            } else if (row2 == 1 && col2 == 1) {
                if (gameBoard[1][2] == -1) {
                    powerPoint[0] = 1;
                    powerPoint[1] = 2;
                }
            }
        } else if (row1 == 1 && col1 == 1) {
            if (row2 == 0 && col2 == 0) {
                if (gameBoard[2][2] == -1) {
                    powerPoint[0] = 2;
                    powerPoint[1] = 2;
                }
            } else if (row2 == 0 && col2 == 1) {
                if (gameBoard[2][1] == -1) {
                    powerPoint[0] = 2;
                    powerPoint[1] = 1;
                }
            } else if (row2 == 0 && col2 == 2) {
                if (gameBoard[2][0] == -1) {
                    powerPoint[0] = 2;
                    powerPoint[1] = 0;
                }
            } else if (row2 == 1 && col2 == 0) {
                if (gameBoard[1][2] == -1) {
                    powerPoint[0] = 1;
                    powerPoint[1] = 2;
                }
            } else if (row2 == 1 && col2 == 2) {
                if (gameBoard[1][0] == -1) {
                    powerPoint[0] = 1;
                    powerPoint[1] = 0;
                }
            } else if (row2 == 2 && col2 == 0) {
                if (gameBoard[0][2] == -1) {
                    powerPoint[0] = 0;
                    powerPoint[1] = 2;
                }
            } else if (row2 == 2 && col2 == 1) {
                if (gameBoard[0][1] == -1) {
                    powerPoint[0] = 0;
                    powerPoint[1] = 1;
                }
            } else if (row2 == 2 && col2 == 2) {
                if (gameBoard[0][0] == -1) {
                    powerPoint[0] = 0;
                    powerPoint[1] = 0;
                }
            }
        } else if (row1 == 1 && col1 == 2) {
            if (row2 == 0 && col2 == 2) {
                if (gameBoard[2][2] == -1) {
                    powerPoint[0] = 2;
                    powerPoint[1] = 2;
                }
            } else if (row2 == 2 && col2 == 2) {
                if (gameBoard[0][2] == -1) {
                    powerPoint[0] = 0;
                    powerPoint[1] = 2;
                }
            } else if (row2 == 1 && col2 == 1) {
                if (gameBoard[1][0] == -1) {
                    powerPoint[0] = 1;
                    powerPoint[1] = 0;
                }
            }
        } else if (row1 == 2 && col1 == 0) {
            if (row2 == 1 && col2 == 0) {
                if (gameBoard[0][0] == -1) {
                    powerPoint[0] = 0;
                    powerPoint[1] = 0;
                }
            } else if (row2 == 2 && col2 == 1) {
                if (gameBoard[2][2] == -1) {
                    powerPoint[0] = 2;
                    powerPoint[1] = 2;
                }
            } else if (row2 == 1 && col2 == 1) {
                if (gameBoard[0][2] == -1) {
                    powerPoint[0] = 0;
                    powerPoint[1] = 2;
                }
            }
        } else if (row1 == 2 && col1 == 1) {
            if (row2 == 2 && col2 == 0) {
                if (gameBoard[2][2] == -1) {
                    powerPoint[0] = 2;
                    powerPoint[1] = 2;
                }
            } else if (row2 == 2 && col2 == 2) {
                if (gameBoard[2][0] == -1) {
                    powerPoint[0] = 2;
                    powerPoint[1] = 0;
                }
            } else if (row2 == 1 && col2 == 1) {
                if (gameBoard[0][1] == -1) {
                    powerPoint[0] = 0;
                    powerPoint[1] = 1;
                }
            }
        } else if (row1 == 2 && col1 == 2) {
            if (row2 == 2 && col2 == 1) {
                if (gameBoard[2][0] == -1) {
                    powerPoint[0] = 2;
                    powerPoint[1] = 0;
                }
            } else if (row2 == 1 && col2 == 2) {
                if (gameBoard[0][2] == -1) {
                    powerPoint[0] = 0;
                    powerPoint[1] = 2;
                }
            } else if (row2 == 1 && col2 == 1) {
                if (gameBoard[0][0] == -1) {
                    powerPoint[0] = 0;
                    powerPoint[1] = 0;
                }
            }
        }

        return powerPoint;
    }

    public int[] getRandomOpenSpace() { // not really random
        int[] openSpace = new int[2];
        Random rand = new Random();
        int oneOrZero = rand.nextInt(2);

        openSpace[0] = -1;
        openSpace[1] = -1;

        if (oneOrZero == 1) {

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (gameBoard[i][j] == -1) {
                        openSpace[0] = i;
                        openSpace[1] = j;
                    }
                }
            }

        } else if (oneOrZero == 0) {

            for (int i = 2; i >= 0; i--) {
                for (int j = 2; j >= 0; j--) {
                    if (gameBoard[i][j] == -1) {
                        openSpace[0] = i;
                        openSpace[1] = j;
                    }
                }
            }

        }

        return openSpace;
    }

    public int[] getRandomOpenCorner() { // faux random open corners
        int[] openCorner = new int[2];
        Random rand = new Random();
        int randNum = rand.nextInt(4);

        openCorner[0] = -1;
        openCorner[1] = -1;

        if (randNum == 0) {
            if (gameBoard[0][0] == -1) {
                openCorner[0] = 0;
                openCorner[1] = 0;
            } else if (gameBoard[2][0] == -1) {
                openCorner[0] = 2;
                openCorner[1] = 0;
            } else if (gameBoard[2][2] == -1) {
                openCorner[0] = 2;
                openCorner[1] = 2;
            } else if (gameBoard[0][2] == -1) {
                openCorner[0] = 0;
                openCorner[1] = 2;
            }
        } else if (randNum == 1) {
            if (gameBoard[2][2] == -1) {
                openCorner[0] = 2;
                openCorner[1] = 2;
            } else if (gameBoard[0][0] == -1) {
                openCorner[0] = 0;
                openCorner[1] = 0;
            } else if (gameBoard[0][2] == -1) {
                openCorner[0] = 0;
                openCorner[1] = 2;
            } else if (gameBoard[2][0] == -1) {
                openCorner[0] = 2;
                openCorner[1] = 0;
            }
        } else if (randNum == 2) {
            if (gameBoard[0][2] == -1) {
                openCorner[0] = 0;
                openCorner[1] = 2;
            } else if (gameBoard[2][0] == -1) {
                openCorner[0] = 2;
                openCorner[1] = 0;
            } else if (gameBoard[2][2] == -1) {
                openCorner[0] = 2;
                openCorner[1] = 2;
            } else if (gameBoard[0][0] == -1) {
                openCorner[0] = 0;
                openCorner[1] = 0;
            }
        } else if (randNum == 3) {
            if (gameBoard[2][0] == -1) {
                openCorner[0] = 2;
                openCorner[1] = 0;
            } else if (gameBoard[0][0] == -1) {
                openCorner[0] = 0;
                openCorner[1] = 0;
            } else if (gameBoard[2][2] == -1) {
                openCorner[0] = 2;
                openCorner[1] = 2;
            } else if (gameBoard[0][2] == -1) {
                openCorner[0] = 0;
                openCorner[1] = 2;
            }
        }

        return openCorner;
    }

    public int[] findOpposite(int opposite) { // find a marker of a symbol (specified opposite)
        int[] oppArr = new int[2];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameBoard[i][j] == opposite) {
                    oppArr[0] = i;
                    oppArr[1] = j;
                    return oppArr;
                }
            }
        }
        return oppArr;
    }
}