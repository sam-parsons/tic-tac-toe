import java.util.Random;

/**
 * OPEN TICKETS
 * - getOuterPowerPoint
 * - Move 5 - when checking for an adjacent same symbol, check if it will form a PowerBloc!!
 * - Move 5 - something keeps picking a corner that will fill a diag with the different symbols
 * - Move 3 - should somehow consider corners first
 * - Move 7 - make a method that checks if potential of three in a row diagonally are of different symbols
 * 
 * USEFUL METHOD
 * - method to check second to last move produces stalemate and doesn't continue to last move
 */

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
        } else if (symbol == -1) {
            gameBoard[row][column] = -1;
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
                return isWon;
            } else if ((gameBoard[0][i] == gameBoard[1][i]) && (gameBoard[0][i] == gameBoard[2][i]) && isWon < 0) {
                isWon = gameBoard[0][i];
                return isWon;
            } else if (i == 0 && (gameBoard[i][i] == gameBoard[i+1][i+1]) && (gameBoard[i][i] == gameBoard[i+2][i+2]) && isWon < 0) {
                isWon = gameBoard[i][i];
                return isWon;
            } else if (i == 2 && (gameBoard[i][i-2] == gameBoard[i-1][i-1]) && (gameBoard[i][i-2] == gameBoard[i-2][i]) && isWon < 0) {
                isWon = gameBoard[i][i-2];
                return isWon;
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

        // char[] charArr = {'A', 'B', 'C', 'D', 'E'};
        // int countChar = 0;

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
                            System.out.print("X"); // System.out.print((int)3/(j-1));
                        } else if (gameBoard[(int)Math.floor(i / 3)][(int)Math.floor((j-1) / 3)] == 1) {
                            // countChar++;
                            System.out.print("O"); // System.out.print();
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
        if (move == 0) { // First move
            finalMove[0] = 1;
            finalMove[1] = 1;
            return finalMove;
        } else if (move == 1) { // Second move
            if (gameBoard[1][1] >= 0) { // pick any corner
                int tempM1Row = -1;
                int tempM1Col = -1;
                Random randM1 = new Random();
                do {
                    tempM1Row = randM1.nextInt(2);
                    tempM1Col = randM1.nextInt(2);
                    if (tempM1Row == 1) {
                        tempM1Row = 2;
                    } else if (tempM1Col == 1) {
                        tempM1Col = 2;
                    }
                } while (tempM1Row != 1 && tempM1Col != 1 && gameBoard[tempM1Row][tempM1Col] != -1);

                finalMove[0] = tempM1Row;
                finalMove[1] = tempM1Col;
                return finalMove;
            } else {
                finalMove[0] = 1;
                finalMove[1] = 1;
                return finalMove;
            }
        } else if (move == 2) { // Third move, make adjacent move
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
        } else if (move == 3) { // Fourth move
            System.out.println("Move 3");
            if (gameBoard[1][1] != -1) {
                int opposite;
                if (symbol == 0) {
                    opposite = 1;
                } else {
                    opposite = 0;
                }

                // moveArr is a speculation on possible matches
                int[] moveArr = new int[4];
                for (int i = 0; i < 4; i++) moveArr[i] = -1;
                int[] first = new int[2];
                int[] second = new int[2];

                first[0] = -1;
                first[1] = -1;
                second[0] = -1;
                second[1] = -1;

                // finding a position of an opposite symbol on the board
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

                // check if there are adjacent opposite symbols
                if (this.isAdjacent(first[0], first[1], opposite)) {

                    int[] adjArr = new int[2];
                    // get the coordinates for the adjacent opposite symbol
                    adjArr = this.getAdjacent(first[0], first[1]);
                    System.out.print("Adjacent opposite symbol ");
                    for (int i = 0; i < 2; i++) {
                        System.out.print(adjArr[i] + " ");
                    }
                    System.out.println();
                    moveArr[0] = first[0];
                    moveArr[1] = first[1];
                    moveArr[2] = adjArr[0];
                    moveArr[3] = adjArr[1];

                // if there are outer matching symbols, for the corners  
                } else if (this.isOuterMatch(first[0], first[1], opposite)) {

                    int[] outerArr = new int[2];
                    outerArr = this.getOuterMatch(first[0], first[1], opposite);
                    System.out.println("outer opposite coordinates");
                    for (int i = 0; i < 2; i++) {
                        System.out.println(outerArr[i]);
                    }
                    moveArr[0] = first[0];
                    moveArr[1] = first[1];
                    moveArr[2] = outerArr[0];
                    moveArr[3] = outerArr[1];

                }

                // find the position of a similar symbol
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

                // checks if there at least one adjacent or outer match for the opposite symbol
                if (moveArr[0] != -1) {

                    System.out.println("Same symbol coordinates: ");
                    for (int i = 0; i < 2; i++) System.out.println(tempArr[i]);

                    // if the potential third block forms a winning sequence
                    if (this.isPowerBloc(moveArr)) {
                        System.out.println("PowerBloc");

                        int[] powerPoints = new int[2];
                        powerPoints = this.getPowerPoint(moveArr);
                        finalMove[0] = powerPoints[0];
                        finalMove[1] = powerPoints[1];
                        return finalMove;

                    // if moveArr doesn't form a winning solution, use random    
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

                // this means there are two opposing symbol that are in a disjunct position,
                // therefore use the above logic for the same symbol positions    
                } else {

                    // not random, offensive strategy

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

        } else if (move == 4) {  // Fifth move
            if (gameBoard[1][1] != -1) {
                int opposite;
                if (symbol == 0) {
                    opposite = 1;
                } else {
                    opposite = 0;
                }

                int[] moveArr = new int[4]; // try not to use this?
                for (int i = 0; i < 4; i++) moveArr[i] = -1;
                int[] first = new int[2]; // array for opposite symbol coordinates
                int[] second = new int[2]; // array for same symbol coordinates

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

                for (int i = 0; i < 3; i ++) {
                    for (int j = 0; j < 3; j++) {
                        if (gameBoard[i][j] == symbol) {
                            if (second[0] == -1) {
                                second[0] = i;
                                second[1] = j;
                            }
                        }
                    }
                }

                System.out.println("Opposite coordinates: " + first[0] + ", " + first[1]);

                if (this.isAdjacent(first[0], first[1], opposite)) {

                    int[] adjArr = new int[2];
                    int[] tempAdjArr = new int[4];
                    adjArr = getAdjacent(first[0], first[1]);
                    for (int i = 0; i < 2; i++) {
                        System.out.println(adjArr[i]);
                    }
                    tempAdjArr[0] = first[0];
                    tempAdjArr[1] = first[1];
                    tempAdjArr[2] = adjArr[0];
                    tempAdjArr[3] = adjArr[1];
                    System.out.println("Adjacent coordinates: " + adjArr[0] + ", " + adjArr[1]);

                    // do the two adjacent spaces form PowerBloc, if not then 

                    if (this.isPowerBloc(tempAdjArr)) {

                        finalMove[0] = tempAdjArr[2];
                        finalMove[1] = tempAdjArr[3];
                        return finalMove;

                    } else { // if opposite symbols are adjacent, but don't form PowerBloc

                        if (this.isOuterMatch(first[0], first[1], opposite)) {

                            // for when there are outer ring opposite symbols

                            // check if intermediate space is open

                            int[] tempGOM = this.getOuterMatch(first[0], first[1], opposite);
                            int[] tempGOPP = this.getOuterPowerPoint(first, opposite);

                            if (tempGOPP[0] != -1) { // if there is an open intermediate space

                                finalMove[0] = tempGOPP[0];
                                finalMove[1] = tempGOPP[1];
                                return finalMove;

                            } else { // if there is not an open intermediate space
                                // go to same symbol process
                                System.out.println("no open intermediate space - to same symbol process");
                            }
        
                        } 
                    }
                }
                
                if (this.isOuterMatch(first[0], first[1], opposite)) {

                    // for when there are outer ring opposite symbols

                    // might not need tempGOM
                    int[] tempGOM = this.getOuterMatch(first[0], first[1], opposite);
                    int[] tempGOPP = this.getOuterPowerPoint(first, opposite);

                    if (tempGOPP[0] != -1) { // if there is an open intermediate space

                        finalMove[0] = tempGOPP[0];
                        finalMove[1] = tempGOPP[1];
                        return finalMove;

                    } else { // if there is not an open intermediate space
                            // go to same symbol process
                            System.out.println("no open intermediate space - to same symbol process");
                    }

                } 

                // finished checking on opposite symbols, begin same symbol process
                System.out.println("Same symbol process");

                if (this.isAdjacent(second[0], second[1], symbol)) {

                    // if there is an adjacent same symbol
                    int[] tempIA = this.getAdjacent(second[0], second[1]);
                    int[] tempAO = new int[4];
                    tempAO[0] = second[0];
                    tempAO[1] = second[1];
                    tempAO[2] = tempIA[0];
                    tempAO[3] = tempIA[1];

                    if (this.isPowerBloc(tempAO)) { // if there is a same symbol PowerBloc

                        int[] tempPP = this.getPowerPoint(tempAO);
                        finalMove[0] = tempPP[0];
                        finalMove[1] = tempPP[1];
                        return finalMove;

                    } else { // if there are adjacent same symbols, but not PowerBloc

                        // go to finding outer match for same symbol
                        System.out.println("Adjacent same symbols, but not PowerBloc");

                    }

                } 
                
                if (this.isOuterMatch(second[0], second[1], symbol)) {

                    // for outer ring same symbols
                    System.out.println("Found outer match for same symbol");

                    int[] outerMatchArr = this.getOuterMatch(second[0], second[1], symbol);
                    int[] outerMatchPP = this.getOuterPowerPoint(second, symbol);
                    if (outerMatchPP[0] != -1) {

                        finalMove[0] = outerMatchPP[0];
                        finalMove[1] = outerMatchPP[1];
                        return finalMove;

                    } else { // there is not an intermediate free space between the outer matches
                        System.out.println("No free space between outer matches of same symbol");
                    }

                } 
                
                if (moveArr[0] == -1) { // check to see if moveArr[0] == -1

                    // if there are no adjacent or outer matches for opposite or
                    // same symbols, then randomly pick an open position that is adjacent
                    // to some same symbol

                    System.out.println("Finding random adjacent to same symbol open space");
                    Random randLast = new Random();
                    int tempLast1 = -1;
                    int tempLast2 = -1;

                    do {
                        
                        tempLast1 = randLast.nextInt(3);
                        tempLast2 = randLast.nextInt(3);

                    } while (!this.isAdjacent(tempLast1, tempLast2, symbol) || gameBoard[tempLast1][tempLast2] != -1);

                    finalMove[0] = tempLast1;
                    finalMove[1] = tempLast2;
                    return finalMove;

                }


            } else { // if center square is open
                finalMove[0] = 1;
                finalMove[1] = 1;
                return finalMove;
            }

            return finalMove;

        } else if (move == 5) { // Sixth move
            
            if (gameBoard[1][1] != -1) {

                int opposite;
                if (symbol == 0) {
                    opposite = 1;
                } else {
                    opposite = 0;
                }

                // setting up and retreiving data about all positions
                int[] firstOpen = new int[2];
                for (int i = 0; i < 2; i ++) firstOpen[i] = -1;
                int[] secondOpen = new int[2];
                for (int i = 0; i < 2; i ++) secondOpen[i] = -1;
                int[] thirdOpen = new int[2];
                for (int i = 0; i < 2; i ++) thirdOpen[i] = -1;
                int[] fourthOpen = new int[2];
                for (int i = 0; i < 2; i ++) fourthOpen[i] = -1;

                int[] firstSame = new int[2];
                for (int i = 0; i < 2; i ++) firstSame[i] = -1;
                int[] secondSame = new int[2];
                for (int i = 0; i < 2; i ++) secondSame[i] = -1;
                
                int[] firstOpp = new int[2];
                for (int i = 0; i < 2; i ++) firstOpp[i] = -1;
                int[] secondOpp = new int[2];
                for (int i = 0; i < 2; i ++) secondOpp[i] = -1;
                int[] thirdOpp = new int[2];
                for (int i = 0; i < 2; i ++) thirdOpp[i] = -1;

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {

                        if (gameBoard[i][j] == -1) {
                            if (firstOpen[0] == -1) {
                                firstOpen[0] = i;
                                firstOpen[1] = j;
                            } else if (secondOpen[0] == -1) {
                                secondOpen[0] = i;
                                secondOpen[1] = j;
                            } else if (thirdOpen[0] == -1) {
                                thirdOpen[0] = i;
                                thirdOpen[1] = j;
                            }

                        } else if (gameBoard[i][j] == symbol) {

                            if (firstSame[0] == -1) {
                                firstSame[0] = i;
                                firstSame[1] = j;
                            } else if (secondSame[0] == -1) {
                                secondSame[0] = i;
                                secondSame[1] = j;
                            }

                        } else if (gameBoard[i][j] == opposite) {

                            if (firstOpp[0] == -1) {
                                firstOpp[0] = i;
                                firstOpp[1] = j;
                            } else if (secondOpp[0] == -1) {
                                secondOpp[0] = i;
                                secondOpp[1] = j;
                            } else if (thirdOpp[0] == -1) {
                                thirdOpp[0] = i;
                                thirdOpp[1] = j;
                            }


                        }

                    }
                }

                int[] tempArr1 = new int[4];
                tempArr1[0] = firstOpp[0];
                tempArr1[1] = firstOpp[1];
                tempArr1[2] = secondOpp[0];
                tempArr1[3] = secondOpp[1];
                int[] tempArr2 = new int[4];
                tempArr2[0] = secondOpp[0];
                tempArr2[1] = secondOpp[1];
                tempArr2[2] = thirdOpp[0];
                tempArr2[3] = thirdOpp[1];
                int[] tempArr3 = new int[4];
                tempArr3[0] = firstOpp[0];
                tempArr3[1] = firstOpp[1];
                tempArr3[2] = thirdOpp[0];
                tempArr3[3] = thirdOpp[1];

                // test to see if any configuration of the three opposite symbols will form PowerBloc
                if (this.isPowerBloc(tempArr1)) {

                    int[] tempArrA = new int[2];
                    tempArrA = this.getPowerPoint(tempArr1);
                    finalMove[0] = tempArrA[0];
                    finalMove[1] = tempArrA[1];
                    return finalMove;

                } else if (this.isPowerBloc(tempArr2)) {

                    int[] tempArrB = new int[2];
                    tempArrB = this.getPowerPoint(tempArr2);
                    finalMove[0] = tempArrB[0];
                    finalMove[1] = tempArrB[1];
                    return finalMove;

                } else if (this.isPowerBloc(tempArr3)) {

                    int[] tempArrC = new int[2];
                    tempArrC = this.getPowerPoint(tempArr3);
                    finalMove[0] = tempArrC[0];
                    finalMove[1] = tempArrC[1];
                    return finalMove;

                }

                // look at combination of similar symbols
                int[] tempArrSame = new int[4];
                tempArrSame[0] = firstSame[0];
                tempArrSame[1] = firstSame[1];
                tempArrSame[2] = secondSame[0];
                tempArrSame[3] = secondSame[1];

                if (this.isPowerBloc(tempArrSame)) {

                    int[] tempArrSamePP = new int[2];
                    tempArrSamePP = this.getPowerPoint(tempArrSame);
                    finalMove[0] = tempArrSamePP[0];
                    finalMove[1] = tempArrSamePP[1];
                    return finalMove;

                }
                
                tempArrSame[2] = firstOpen[0];
                tempArrSame[3] = firstOpen[1];
                if (this.isPowerBloc(tempArrSame)) { // first same symbol and first open space

                    finalMove[0] = tempArrSame[2];
                    finalMove[1] = tempArrSame[3];
                    return finalMove;

                }
                
                tempArrSame[2] = secondOpen[0];
                tempArrSame[3] = secondOpen[1];
                if (this.isPowerBloc(tempArrSame)) { // first same symbol and second open space

                    finalMove[0] = tempArrSame[2];
                    finalMove[1] = tempArrSame[3];
                    return finalMove;

                }
                
                tempArrSame[2] = thirdOpen[0];
                tempArrSame[3] = thirdOpen[1];
                if (this.isPowerBloc(tempArrSame)) { // first same symbol and third open space

                    finalMove[0] = tempArrSame[2];
                    finalMove[1] = tempArrSame[3];
                    return finalMove;

                }
                
                tempArrSame[2] = fourthOpen[0];
                tempArrSame[3] = fourthOpen[1];
                if (this.isPowerBloc(tempArrSame)) { // first same symbol and fourth open space

                    finalMove[0] = tempArrSame[2];
                    finalMove[1] = tempArrSame[3];
                    return finalMove;

                }
                
                tempArrSame[0] = firstOpen[0];
                tempArrSame[1] = firstOpen[1];
                tempArrSame[2] = secondSame[0];
                tempArrSame[3] = secondSame[1];
                if (this.isPowerBloc(tempArrSame)) { // second same symbol and first open space

                    finalMove[0] = tempArrSame[0];
                    finalMove[1] = tempArrSame[1];
                    return finalMove;

                }
                
                tempArrSame[0] = secondOpen[0];
                tempArrSame[1] = secondOpen[1];
                if (this.isPowerBloc(tempArrSame)) { // second same symbol and second open space

                    finalMove[0] = tempArrSame[0];
                    finalMove[1] = tempArrSame[1];
                    return finalMove;
                    
                }

                tempArrSame[0] = secondOpen[0];
                tempArrSame[1] = secondOpen[1];
                if (this.isPowerBloc(tempArrSame)) { // second same symbol and third open space

                    finalMove[0] = tempArrSame[0];
                    finalMove[1] = tempArrSame[1];
                    return finalMove;

                }

                tempArrSame[0] = secondOpen[0];
                tempArrSame[1] = secondOpen[1];
                if (this.isPowerBloc(tempArrSame)) { // second same symbol and fourth open space

                    finalMove[0] = tempArrSame[0];
                    finalMove[1] = tempArrSame[1];
                    return finalMove;

                }

                // pick random spot that is adjacent to a similar symbol -- ?? is the last condition needed??

                Random rand6 = new Random();
                int rand6R = -1;
                int rand6C = -1;
                do {

                    rand6R = rand6.nextInt(3);
                    rand6C = rand6.nextInt(3);

                } while (gameBoard[rand6R][rand6C] != -1); // maybe should check for adjacency

                finalMove[0] = rand6R;
                finalMove[1] = rand6C;
                return finalMove;

            } else {
                finalMove[0] = 1;
                finalMove[1] = 1;
                return finalMove;
            }

        } else if (move == 6) { // Seventh move

            if (gameBoard[1][1] != -1) {
                int opposite;
                if (symbol == 0) {
                    opposite = 1;
                } else {
                    opposite = 0;
                }

                int[] moveArrOpen = new int[4]; // try not to use this?
                for (int i = 0; i < 4; i++) moveArrOpen[i] = -1;
                int[] firstOpen = new int[2]; // array for first open coordinate
                int[] secondOpen = new int[2]; // array for second open coordinate
                int[] thirdOpen = new int[2]; // array for third open coordinate

                firstOpen[0] = -1;
                firstOpen[1] = -1;
                secondOpen[0] = -1;
                secondOpen[1] = -1;
                thirdOpen[0] = -1;
                thirdOpen[1] = -1;

                for (int i = 0; i < 3; i ++) {
                    for (int j = 0; j < 3; j++) {
                        if (gameBoard[i][j] == -1) {
                            if (firstOpen[0] == -1) {
                                firstOpen[0] = i;
                                firstOpen[1] = j;
                            } else if (secondOpen[0] == -1) {
                                secondOpen[0] = i;
                                secondOpen[1] = j;
                            } else if (thirdOpen[0] == -1) {
                                thirdOpen[0] = i;
                                thirdOpen[1] = j;
                            }
                        }
                    }
                }

                // check is opposite symbol can win with either position
                if (this.checkWin(firstOpen[0], firstOpen[0], opposite)) {

                    finalMove[0] = firstOpen[0];
                    finalMove[1] = firstOpen[1];
                    return finalMove;

                } else if (this.checkWin(secondOpen[0], secondOpen[0], opposite)) {

                    finalMove[0] = secondOpen[0];
                    finalMove[1] = secondOpen[1];
                    return finalMove;

                } else if (this.checkWin(thirdOpen[0], thirdOpen[0], opposite)) {

                    finalMove[0] = thirdOpen[0];
                    finalMove[1] = thirdOpen[1];
                    return finalMove;

                }
                

                // check if same symbol can win with either position

                if (this.checkWin(firstOpen[0], firstOpen[0], symbol)) {

                    finalMove[0] = firstOpen[0];
                    finalMove[1] = firstOpen[1];
                    return finalMove;

                } else if (this.checkWin(secondOpen[0], secondOpen[0], symbol)) {

                    finalMove[0] = secondOpen[0];
                    finalMove[1] = secondOpen[1];
                    return finalMove;

                } else if (this.checkWin(thirdOpen[0], thirdOpen[0], symbol)) {

                    finalMove[0] = thirdOpen[0];
                    finalMove[1] = thirdOpen[1];
                    return finalMove;

                }

                // if not either of those, pick random

                int temp1Open;
                int temp2Open;
                Random rand = new Random();

                do {
                    temp1Open = rand.nextInt(3);
                    temp2Open = rand.nextInt(3);
                    
                } while (gameBoard[temp1Open][temp2Open] != -1);

                finalMove[0] = temp1Open;
                finalMove[1] = temp2Open;
                return finalMove;

            } else { // if center position is open
                finalMove[0] = 1;
                finalMove[1] = 1;
                return finalMove;
            }

        } else if (move == 7) { // Move 8

            if (gameBoard[1][1] != -1) {
                int opposite;
                if (symbol == 0) {
                    opposite = 1;
                } else {
                    opposite = 0;
                }

                int[] moveArrOpen = new int[4]; // try not to use this?
                for (int i = 0; i < 4; i++) moveArrOpen[i] = -1;
                int[] firstOpen = new int[2]; // array for first open coordinates
                int[] secondOpen = new int[2]; // array for second open coordinates

                firstOpen[0] = -1;
                firstOpen[1] = -1;
                secondOpen[0] = -1;
                secondOpen[1] = -1;

                for (int i = 0; i < 3; i ++) {
                    for (int j = 0; j < 3; j++) {
                        if (gameBoard[i][j] == -1) {
                            if (firstOpen[0] == -1) {
                                firstOpen[0] = i;
                                firstOpen[1] = j;
                            } else if (secondOpen[0] == -1) {
                                secondOpen[0] = i;
                                secondOpen[1] = j;
                            }
                        }
                    }
                }

                // check is opposite symbol can win with either position
                if (this.checkWin(firstOpen[0], firstOpen[0], opposite)) {

                    finalMove[0] = firstOpen[0];
                    finalMove[1] = firstOpen[1];
                    return finalMove;

                } else if (this.checkWin(secondOpen[0], secondOpen[0], opposite)) {

                    finalMove[0] = secondOpen[0];
                    finalMove[1] = secondOpen[1];
                    return finalMove;

                }
                

                // check if same symbol can win with either position

                if (this.checkWin(firstOpen[0], firstOpen[0], symbol)) {

                    finalMove[0] = firstOpen[0];
                    finalMove[1] = firstOpen[1];
                    return finalMove;

                } else if (this.checkWin(secondOpen[0], secondOpen[0], symbol)) {

                    finalMove[0] = secondOpen[0];
                    finalMove[1] = secondOpen[1];
                    return finalMove;

                }

                // if not either of those, pick random

                int temp1Open;
                int temp2Open;
                Random rand = new Random();

                do {
                    temp1Open = rand.nextInt(3);
                    temp2Open = rand.nextInt(3);
                    
                } while (gameBoard[temp1Open][temp2Open] != -1);

                finalMove[0] = temp1Open;
                finalMove[1] = temp2Open;
                return finalMove;

            } else { // if center position is open
                finalMove[0] = 1;
                finalMove[1] = 1;
                return finalMove;
            }
        
        } else if (move == 8) { // final move, find remaining open space
            
            int temp1;
            int temp2;
            Random rand = new Random();

            do {
                temp1 = rand.nextInt(3);
                temp2 = rand.nextInt(3);
                
            } while (gameBoard[temp1][temp2] != -1);

            finalMove[0] = temp1;
            finalMove[1] = temp2;
            return finalMove;

        }  else { // if move isn't >= 0 and/or <= 8
            finalMove[0] = -1;
            finalMove[1] = -1;
            return finalMove;
        }
        return finalMove;
    }

    public boolean isAdjacent(int row, int col, int symbolIA) {
        boolean isAdjacent = false;

        if (row == 0 && col == 0) {
            if (gameBoard[0][1] == symbolIA) {
                isAdjacent = true;
            } else if (gameBoard[1][0] == symbolIA) {
                isAdjacent = true;
            } else if (gameBoard[1][1] == symbolIA) {
                isAdjacent = true;
            }
        } else if (row == 0 && col == 1) {
            if (gameBoard[0][0] == symbolIA) {
                isAdjacent = true;
            } else if (gameBoard[0][2] == symbolIA) {
                isAdjacent = true;
            } else if (gameBoard[1][1] == symbolIA) {
                isAdjacent = true;
            }
        } else if (row == 0 && col == 2) {
            if (gameBoard[0][1] == symbolIA) {
                isAdjacent = true;
            } else if (gameBoard[1][2] == symbolIA) {
                isAdjacent = true;
            } else if (gameBoard[1][1] == symbolIA) {
                isAdjacent = true;
            }
        } else if (row == 1 && col == 0) {
            if (gameBoard[0][0] == symbolIA) {
                isAdjacent = true;
            } else if (gameBoard[2][0] == symbolIA) {
                isAdjacent = true;
            } else if (gameBoard[1][1] == symbolIA) {
                isAdjacent = true;
            }
        } else if (row == 1 && col == 1) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (gameBoard[i][j] == symbolIA && (i != 1 && j != 1)) {
                        isAdjacent = true;
                    }
                }
            }
        } else if (row == 1 && col == 2) {
            if (gameBoard[0][2] == symbolIA) {
                isAdjacent = true;
            } else if (gameBoard[2][2] == symbolIA) {
                isAdjacent = true;
            } else if (gameBoard[1][1] == symbolIA) {
                isAdjacent = true;
            }
        } else if (row == 2 && col == 0) {
            if (gameBoard[1][0] == symbolIA) {
                isAdjacent = true;
            } else if (gameBoard[2][1] == symbolIA) {
                isAdjacent = true;
            } else if (gameBoard[1][1] == symbolIA) {
                isAdjacent = true;
            }
        } else if (row == 2 && col == 1) {
            if (gameBoard[2][0] == symbolIA) {
                isAdjacent = true;
            } else if (gameBoard[2][2] == symbolIA) {
                isAdjacent = true;
            } else if (gameBoard[1][1] == symbolIA) {
                isAdjacent = true;
            }
        } else if (row == 2 && col == 2) {
            if (gameBoard[2][1] == symbolIA) {
                isAdjacent = true;
            } else if (gameBoard[1][2] == symbolIA) {
                isAdjacent = true;
            } else if (gameBoard[1][1] == symbolIA) {
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

    public int[] getAdjacent(int rowGA, int colGA) {
        int[] adjacent = new int[2];

        adjacent[0] = -1;
        adjacent[1] = -1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ((i != rowGA || j != colGA) && this.isPairAdjacent(rowGA,colGA,i,j)) {
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
            } else if (row2 == 2 && col2 == 2) {
                if (gameBoard[1][1] == -1) {
                    isPowerBloc = true;
                }
            } else if (row2 == 2 && col2 == 0) {
                if (gameBoard[1][0] == -1) {
                    isPowerBloc = true;
                }
            } else if (row2 == 0 && col2 == 2) {
                if (gameBoard[0][1] == -1) {
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
            } else if (row2 == 2 && col2 == 1) {
                if (gameBoard[1][1] == -1) {
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
            } else if (row2 == 2 && col2 == 0) {
                if (gameBoard[1][1] == -1) {
                    isPowerBloc = true;
                }
            } else if (row2 == 2 && col2 == 2) {
                if (gameBoard[1][2] == -1) {
                    isPowerBloc = true;
                }
            } else if (row2 == 0 && col2 == 0) {
                if (gameBoard[0][1] == -1) {
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
            } else if (row2 == 1 && col2 == 2) {
                if (gameBoard[1][1] == -1) {
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
            } else if (row2 == 1 && col2 == 0) {
                if (gameBoard[1][1] == -1) {
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
            } else if (row2 == 0 && col2 == 0) {
                if (gameBoard[1][0] == -1) {
                    isPowerBloc = true;
                }
            } else if (row2 == 2 && col2 == 2) {
                if (gameBoard[2][1] == -1) {
                    isPowerBloc = true;
                }
            } else if (row2 == 0 && col2 == 2) {
                if (gameBoard[1][1] == -1) {
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
            } else if (row2 == 0 && col2 == 1) {
                if (gameBoard[1][1] == -1) {
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
            } else if (row2 == 0 && col2 == 0) {
                if (gameBoard[1][1] == -1) {
                    isPowerBloc = true;
                }
            } else if (row2 == 0 && col2 == 2) {
                if (gameBoard[1][2] == -1) {
                    isPowerBloc = true;
                }
            } else if (row2 == 2 && col2 == 0) {
                if (gameBoard[2][1] == -1) {
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
            } else if (row2 == 2 && col2 == 2) {
                if (gameBoard[1][1] == -1) {
                    powerPoint[0] = 1;
                    powerPoint[1] = 1;
                }
            } else if (row2 == 0 && col2 == 2) {
                if (gameBoard[0][1] == -1) {
                    powerPoint[0] = 0;
                    powerPoint[1] = 1;
                }
            } else if (row2 == 2 && col2 == 0) {
                if (gameBoard[1][0] == -1) {
                    powerPoint[0] = 1;
                    powerPoint[1] = 0;
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
            } else if (row2 == 2 && col2 == 1) {
                if (gameBoard[1][1] == -1) {
                    powerPoint[0] = 1;
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
            } else if (row2 == 0 && col2 == 0) {
                if (gameBoard[0][1] == -1) {
                    powerPoint[0] = 0;
                    powerPoint[1] = 1;
                }
            } else if (row2 == 2 && col2 == 2) {
                if (gameBoard[1][2] == -1) {
                    powerPoint[0] = 1;
                    powerPoint[1] = 2;
                }
            } else if (row2 == 2 && col2 == 0) {
                if (gameBoard[1][1] == -1) {
                    powerPoint[0] = 1;
                    powerPoint[1] = 1;
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
            } else if (row2 == 1 && col2 == 2) {
                if (gameBoard[1][1] == -1) {
                    powerPoint[0] = 1;
                    powerPoint[1] = 1;
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
            } else if (row2 == 1 && col2 == 0) {
                if (gameBoard[1][1] == -1) {
                    powerPoint[0] = 1;
                    powerPoint[1] = 1;
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
            } else if (row2 == 2 && col2 == 2) {
                if (gameBoard[2][1] == -1) {
                    powerPoint[0] = 2;
                    powerPoint[1] = 1;
                }
            } else if (row2 == 0 && col2 == 2) {
                if (gameBoard[1][1] == -1) {
                    powerPoint[0] = 1;
                    powerPoint[1] = 1;
                }
            } else if (row2 == 0 && col2 == 0) {
                if (gameBoard[0][1] == -1) {
                    powerPoint[0] = 0;
                    powerPoint[1] = 1;
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
            } else if (row2 == 0 && col2 == 1) {
                if (gameBoard[1][1] == -1) {
                    powerPoint[0] = 1;
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
            } else if (row2 == 0 && col2 == 0) {
                if (gameBoard[1][1] == -1) {
                    powerPoint[0] = 1;
                    powerPoint[1] = 1;
                }
            } else if (row2 == 0 && col2 == 2) {
                if (gameBoard[1][2] == -1) {
                    powerPoint[0] = 1;
                    powerPoint[1] = 2;
                }
            } else if (row2 == 2 && col2 == 0) {
                if (gameBoard[2][1] == -1) {
                    powerPoint[0] = 2;
                    powerPoint[1] = 1;
                }
            }
        }

        return powerPoint;
    }

    public boolean isOuterMatch(int rowIOM, int colIOM, int symbolIMO) {
        boolean outerMatchTF = false;

        if (rowIOM == 0 && colIOM == 0) {
            if (gameBoard[2][2] == symbolIMO) {
                outerMatchTF = true;
            } else if (gameBoard[0][2] == symbolIMO) {
                outerMatchTF = true;
            } else if (gameBoard[2][0] == symbolIMO) {
                outerMatchTF = true;
            }
        } else if (rowIOM == 0 && colIOM == 1) {
            if (gameBoard[2][1] == symbolIMO) {
                outerMatchTF = true;
            }
        } else if (rowIOM == 0 && colIOM == 2) {
            if (gameBoard[0][0] == symbolIMO) {
                outerMatchTF = true;
            } else if (gameBoard[2][0] == symbolIMO) {
                outerMatchTF = true;
            } else if (gameBoard[2][2] == symbolIMO) {
                outerMatchTF = true;
            }
        } else if (rowIOM == 1 && colIOM == 0) {
            if (gameBoard[1][2] == symbolIMO) {
                outerMatchTF = true;
            }
        } else if (rowIOM == 1 && colIOM == 1) {
           
        } else if (rowIOM == 1 && colIOM == 2) {
            if (gameBoard[1][0] == symbolIMO) {
                outerMatchTF = true;
            }
        } else if (rowIOM == 2 && colIOM == 0) {
            if (gameBoard[0][2] == symbolIMO) {
                outerMatchTF = true;
            } else if (gameBoard[0][0] == symbolIMO) {
                outerMatchTF = true;
            } else if (gameBoard[2][2] == symbolIMO) {
                outerMatchTF = true;
            }
        } else if (rowIOM == 2 && colIOM == 1) {
            if (gameBoard[0][1] == symbolIMO) {
                outerMatchTF = true;
            }
        } else if (rowIOM == 2 && colIOM == 2) {
            if (gameBoard[0][0] == symbolIMO) {
                outerMatchTF = true;
            } else if (gameBoard[0][2] == symbolIMO) {
                outerMatchTF = true;
            } else if (gameBoard[2][0] == symbolIMO) {
                outerMatchTF = true;
            }
        }

        return outerMatchTF;
    }

    public int[] getOuterMatch(int rowOMI, int colOMI, int symbolGOM) {
        int[] outerMatch = new int[2];

        outerMatch[0] = -1;
        outerMatch[1] = -1;

        if (rowOMI == 0 && colOMI == 0) {
            if (gameBoard[2][2] == symbolGOM) {
                outerMatch[0] = 2;
                outerMatch[1] = 2;
            } else if (gameBoard[0][2] == symbolGOM) {
                outerMatch[0] = 0;
                outerMatch[1] = 2;
            } else if (gameBoard[2][0] == symbolGOM) {
                outerMatch[0] = 2;
                outerMatch[1] = 0;
            }
        } else if (rowOMI == 0 && colOMI == 1) {
            if (gameBoard[2][1] == symbolGOM) {
                outerMatch[0] = 2;
                outerMatch[1] = 1;
            }
        } else if (rowOMI == 0 && colOMI == 2) {
            if (gameBoard[2][0] == symbolGOM) {
                outerMatch[0] = 2;
                outerMatch[1] = 0;
            } else if (gameBoard[2][2] == symbolGOM) {
                outerMatch[0] = 2;
                outerMatch[1] = 2;
            } else if (gameBoard[0][0] == symbolGOM) {
                outerMatch[0] = 0;
                outerMatch[1] = 0;
            }
        } else if (rowOMI == 1 && colOMI == 0) {
            if (gameBoard[1][2] == symbolGOM) {
                outerMatch[0] = 1;
                outerMatch[1] = 2;
            }
        } else if (rowOMI == 1 && colOMI == 1) {
           System.out.println("WHY");
        } else if (rowOMI == 1 && colOMI == 2) {
            if (gameBoard[1][0] == symbolGOM) {
                outerMatch[0] = 1;
                outerMatch[1] = 0;
            }
        } else if (rowOMI == 2 && colOMI == 0) {
            if (gameBoard[0][2] == symbolGOM) {
                outerMatch[0] = 0;
                outerMatch[1] = 2;
            } else if (gameBoard[0][0] == symbolGOM) {
                outerMatch[0] = 0;
                outerMatch[1] = 0;
            } else if (gameBoard[2][2] == symbolGOM) {
                outerMatch[0] = 2;
                outerMatch[1] = 2;
            }
        } else if (rowOMI == 2 && colOMI == 1) {
            if (gameBoard[0][1] == symbolGOM) {
                outerMatch[0] = 0;
                outerMatch[1] = 1;
            }
        } else if (rowOMI == 2 && colOMI == 2) {
            if (gameBoard[0][0] == symbolGOM) {
                outerMatch[0] = 0;
                outerMatch[1] = 0;
            } else if (gameBoard[2][0] == symbolGOM) {
                outerMatch[0] = 2;
                outerMatch[1] = 0;
            } else if (gameBoard[0][2] == symbolGOM) {
                outerMatch[0] = 0;
                outerMatch[1] = 2;
            }
        }

        return outerMatch;
    }

    public int[] getOuterPowerPoint(int[] arr, int symbolOPP) { // PLEASE FIX THIS
        int[] outerPowerPoint = new int[2];

        int rowOPP = arr[0];
        int colOPP = arr[1];

        outerPowerPoint[0] = -1;
        outerPowerPoint[1] = -1;

        if (rowOPP == 0 && colOPP == 0) {
            if (gameBoard[2][2] == symbolOPP && gameBoard[1][1] == -1) {
                outerPowerPoint[0] = 1;
                outerPowerPoint[1] = 1;
            } else if (gameBoard[2][0] == symbolOPP && gameBoard[1][0] == -1) {
                outerPowerPoint[0] = 1;
                outerPowerPoint[1] = 0;
            } else if (gameBoard[0][2] == symbolOPP && gameBoard[0][1] == -1) {
                outerPowerPoint[0] = 0;
                outerPowerPoint[1] = 1;
            }
        } else if (rowOPP == 0 && colOPP == 1 && gameBoard[1][1] == -1) {
            if (gameBoard[2][1] == symbolOPP) {
                outerPowerPoint[0] = 1;
                outerPowerPoint[1] = 1;
            }
        } else if (rowOPP == 0 && colOPP == 2) {
            if (gameBoard[2][0] == symbolOPP && gameBoard[1][1] == -1) {
                outerPowerPoint[0] = 1;
                outerPowerPoint[1] = 1;
            } else if (gameBoard[0][0] == symbolOPP && gameBoard[0][1] == -1) {
                outerPowerPoint[0] = 0;
                outerPowerPoint[1] = 1;
            } else if (gameBoard[2][2] == symbolOPP && gameBoard[1][2] == -1) {
                outerPowerPoint[0] = 1;
                outerPowerPoint[1] = 2;
            }
        } else if (rowOPP == 1 && colOPP == 0 && gameBoard[1][1] == -1) {
            if (gameBoard[1][2] == symbolOPP) {
                outerPowerPoint[0] = 1;
                outerPowerPoint[1] = 1;
            }
        } else if (rowOPP == 1 && colOPP == 1) {
           
        } else if (rowOPP == 1 && colOPP == 2 && gameBoard[1][1] == -1) {
            if (gameBoard[1][0] == symbolOPP) {
                outerPowerPoint[0] = 1;
                outerPowerPoint[1] = 1;
            }
        } else if (rowOPP == 2 && colOPP == 0) {
            if (gameBoard[0][2] == symbolOPP && gameBoard[1][1] == -1) {
                outerPowerPoint[0] = 1;
                outerPowerPoint[1] = 1;
            } else if (gameBoard[2][2] == symbolOPP && gameBoard[2][1] == -1) {
                outerPowerPoint[0] = 2;
                outerPowerPoint[1] = 1;
            } else if (gameBoard[0][0] == symbolOPP && gameBoard[1][0] == -1) {
                outerPowerPoint[0] = 1;
                outerPowerPoint[1] = 0;
            }
        } else if (rowOPP == 2 && colOPP == 1 && gameBoard[1][1] == -1) {
            if (gameBoard[0][1] == symbolOPP) {
                outerPowerPoint[0] = 1;
                outerPowerPoint[1] = 1;
            }
        } else if (rowOPP == 2 && colOPP == 2) {
            if (gameBoard[0][0] == symbolOPP && gameBoard[1][1] == -1) {
                outerPowerPoint[0] = 1;
                outerPowerPoint[1] = 1;
            } else if (gameBoard[2][0] == symbolOPP && gameBoard[2][1] == -1) {
                outerPowerPoint[0] = 2;
                outerPowerPoint[1] = 1;
            } else if (gameBoard[0][2] == symbolOPP && gameBoard[1][2] == -1) {
                outerPowerPoint[0] = 1;
                outerPowerPoint[1] = 2;
            }
        }

        return outerPowerPoint;
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

    public boolean checkWin(int rowCW, int colCW, int symbolCW) {
        boolean checkWin = false;

        int tempCW = gameBoard[rowCW][colCW];

        this.addElement(rowCW, colCW, symbolCW);
        if (this.isWon() == -1) {
            
        } else if (this.isWon() == 0 || this.isWon() == 1) {
            checkWin = true;
        }
        this.addElement(rowCW, colCW, tempCW);

        return checkWin;
    }

    public boolean canBeWon() {
        boolean canBeWon = false;

        int[] firstOpenCBW = new int[2]; // array for first open coordinates
        int[] secondOpenCBW = new int[2]; // array for second open coordinates

        firstOpenCBW[0] = -1;
        firstOpenCBW[1] = -1;
        secondOpenCBW[0] = -1;
        secondOpenCBW[1] = -1;

        for (int i = 0; i < 3; i ++) {
            for (int j = 0; j < 3; j++) {
                if (gameBoard[i][j] == -1) {
                    if (firstOpenCBW[0] == -1) {
                        firstOpenCBW[0] = i;
                        firstOpenCBW[1] = j;
                    } else if (secondOpenCBW[0] == -1) {
                        secondOpenCBW[0] = i;
                        secondOpenCBW[1] = j;
                    }
                }
            }
        }


        if (this.checkWin(firstOpenCBW[0], firstOpenCBW[1], 0)) {
            canBeWon = true;
        } else if (this.checkWin(firstOpenCBW[0], firstOpenCBW[1], 1)) {
            canBeWon = true;
        } else if (this.checkWin(secondOpenCBW[0], secondOpenCBW[1], 0)) {
            canBeWon = true;
        } else if (this.checkWin(secondOpenCBW[0], secondOpenCBW[1], 1)) {
            canBeWon = true;
        }

        return canBeWon;
    }
}