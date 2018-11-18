import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.*;

class Pair {
    private int row, col;
    private boolean isWhite;

    public Pair() {
        this.row = -1;
        this.col = -1;
        this.isWhite = false;
    }
    public Pair(char color) {
        this.row = -1;
        this.col = -1;
        if (color == 'b' || color == 'B') {
            this.isWhite = false;
        } else {
            this.isWhite = true;
        }
    }

    public Pair(int row, int col, char color) {
        this.row = row;
        this.col = col;
        if (color == 'b' || color == 'B') {
            this.isWhite = false;
        } else {
            this.isWhite = true;
        }
    }

    public int getRow() {
        return this.row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return this.col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean getIsWhite() {
        return this.isWhite;
    }

    public void setIsWhite(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public void setLocation(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public boolean isSet() {
        if (this.row == -1 || this.col == -1) {
            return false;
        }
        return true;
    }

    private boolean isToTheRightOf(int col) {
        if (this.col > col) {
            return true;
        }
        return false;
    }

    private boolean isAbove(int row) {
        if (this.row < row) {
            return true;
        }
        return false;
    }

    private boolean checkRow(int currentCol, char[][] board) {
        boolean pathExists = true;

        if (this.isToTheRightOf(currentCol)) {
            // Check for path (to the right)
            int step = currentCol + 1;
            while (step < this.col && pathExists) {
                if (board[this.row][step] != '.') {
                    // no path
                    pathExists = false;
                } else {
                    pathExists = true;
                }
                step++;
            }
            if (pathExists) {
                return true;
            }
        } else {
            // Check for path (to the left)
            int step = currentCol - 1;
            while (step > this.col && pathExists) {
                if (board[this.row][step] != '.') {
                    // no path
                    pathExists = false;
                } else {
                    pathExists = true;
                }
                step--;
            }
            if (pathExists) {
                return true;
            }

        }
        return false;
    }

    private boolean checkCol(int currentRow, char[][] board) {
        boolean pathExists = true;

        if (this.isAbove(currentRow)) {
            // Check for path (above)
            int step = currentRow - 1;
            while (step > this.row && pathExists) {
                if(board[step][this.col] != '.') {
                    // no path
                    pathExists = false;
                } else {
                    pathExists = true;
                }
                step--;
            }
            if (pathExists) {
                return true;
            }
        } else {
            // Check for path (below)
            int step = currentRow + 1;
            while (step < this.row && pathExists) {
                if (board[step][this.col] != '.') {
                    // no path
                    pathExists = false;
                } else {
                    pathExists = true;
                }
                step--;
            }
            if (pathExists) {
                return true;
            }
        }
        return false;
    }

    public boolean inCheck(ArrayList<Pair> rooks, ArrayList<Pair> bishops, ArrayList<Pair> pawns, ArrayList<Pair> knights, Pair queen, Pair king, char[][] board) {
        boolean rookExists = !rooks.isEmpty();
        boolean bishopExists = !bishops.isEmpty();
        boolean pawnExists = !pawns.isEmpty();
        boolean knightExists = !knights.isEmpty();
        boolean queenExists = queen.isSet();

        boolean blockedLR, blockedLL, blockedUR, blockedUL;
        blockedLR = blockedLL = blockedUR = blockedUL = false;

        if (this.isWhite) {
            // White check
            if (queenExists) {
                // Check if Rook/Queen exists on Row/Col of King
                for (int i = 0; i < 8; i++) {
                    if (board[this.row][i] == 'r' || board[this.row][i] == 'q') {
                        if(this.checkRow(i, board)) {
                            return true;
                        }
                    }
                    if (board[i][this.col] == 'r' || board[i][this.col] == 'q') {
                        if(this.checkCol(i, board)) {
                            return true;
                        }
                    }
                    int step = i + 1;
                    if (this.row + step < 8 && this.col + step < 8 && !blockedLR) {
                        // check lower right diag
                        char lowerRight = board[this.row + step][this.col + step];
                        if (lowerRight != '.') {
                            if (lowerRight == 'b' || lowerRight == 'q') {
                                return true;
                            } else {
                                blockedLR = true;
                            }
                        }
                    }
                    if (this.row + step < 8 && this.col - step >= 0 && !blockedLL) {
                        // check lower left diag
                        char lowerLeft = board[this.row + step][this.col - step];
                        if (lowerLeft != '.') {
                            if (lowerLeft == 'b' || lowerLeft == 'q') {
                                return true;
                            } else {
                                blockedLL = true;
                            }
                        }
                    }
                    if (this.row - step >= 0 && this.col + step < 8 && !blockedUR) {
                        // check upper right diag
                        char upperRight = board[this.row - step][this.col + step];
                        if (upperRight != '.') {
                            if (upperRight == 'b' || upperRight == 'q') {
                                return true;
                            } else {
                                blockedUR = true;
                            }
                        }
                    }
                    if (this.row - step >= 0 && this.col - step >= 0 && !blockedUL) {
                        // check upper left diag
                        char upperLeft = board[this.row - step][this.col - step];
                        if (upperLeft != '.') {
                            if (upperLeft == 'b' || upperLeft == 'q') {
                                return true;
                            } else {
                                blockedUL = true;
                            }
                        }
                    }
                }
                // Check if Bishop/Queen exists on Diag of King
            } else {
                if (rookExists) {
                    for (int i = 0; i < 8; i++) {
                        if (board[this.row][i] == 'r') {
                            if(this.checkRow(i, board)) {
                                return true;
                            }
                        }
                        if (board[i][this.col] == 'r') {
                            if(this.checkCol(i, board)) {
                                return true;
                            }
                        }
                    }
                }
                if (bishopExists) {
                    for (int i = 1; i < 8; i++) {
                        if (this.row + i < 8 && this.col + i < 8 && !blockedLR) {
                            // check lower right diag
                            char lowerRight = board[this.row + i][this.col + i];
                            if (lowerRight != '.') {
                                if (lowerRight == 'b') {
                                    return true;
                                } else {
                                    blockedLR = true;
                                }
                            }
                        }
                        if (this.row + i < 8 && this.col - i >= 0 && !blockedLL) {
                            // check lower left diag
                            char lowerLeft = board[this.row + i][this.col - i];
                            if (lowerLeft != '.') {
                                if (lowerLeft == 'b') {
                                    return true;
                                } else {
                                    blockedLL = true;
                                }
                            }
                        }
                        if (this.row - i >= 0 && this.col + i < 8 && !blockedUR) {
                            // check upper right diag
                            char upperRight = board[this.row - i][this.col + i];
                            if (upperRight != '.') {
                                if (upperRight == 'b') {
                                    return true;
                                } else {
                                    blockedUR = true;
                                }
                            }
                        }
                        if (this.row - i >= 0 && this.col - i >= 0 && !blockedUL) {
                            // check upper left diag
                            char upperLeft = board[this.row - i][this.col - i];
                            if (upperLeft != '.') {
                                if (upperLeft == 'b') {
                                    return true;
                                } else {
                                    blockedUL = true;
                                }
                            }
                        }
                    }
                }
            }
            if (knightExists) {
                // Check Upper options
                if (this.row - 2 >= 0) {
                    if (this.col - 1 >= 0 && board[this.row - 2][this.col - 1] == 'n') {
                        return true;
                    }
                    if (this.col + 1 < 8 && board[this.row - 2][this.col + 1] == 'n') {
                        return true;
                    }
                }
                // Check Upper-Mid options
                if (this.row - 1 >= 0) {
                    if (this.col - 2 >= 0 && board[this.row - 1][this.col - 2] == 'n') {
                        return true;
                    }
                    if (this.col + 2 < 8 && board[this.row - 1][this.col + 2] == 'n') {
                        return true;
                    }
                }
                // Check Lower-Mid options
                if (this.row + 1 < 8) {
                    if (this.col - 2 >= 0 && board[this.row + 1][this.col - 2] == 'n') {
                        return true;
                    }
                    if (this.col + 2 < 8 && board[this.row + 1][this.col + 2] == 'n') {
                        return true;
                    }
                }
                // Check Lower options
                if (this.row + 2 < 8) {
                    if (this.col - 1 >= 0 && board[this.row + 2][this.col - 1] == 'n') {
                        return true;
                    }
                    if (this.col + 1 < 8 && board[this.row + 2][this.col + 1] == 'n') {
                        return true;
                    }
                }
            }
            // Check if other king has you in check or pawn while we're at it
            // Check Bottom
            if (this.row + 1 < 8) {
                // Check Bottom-Mid
                if (board[this.row + 1][this.col] == 'k') {
                    return true;
                }
                // Check Bottom-Left
                if (this.col - 1 >= 0 && board[this.row + 1][this.col - 1] == 'k') {
                    return true;
                }
                // Check Bottom-Right
                if (this.col + 1 < 8 && board[this.row + 1][this.col + 1] == 'k') {
                    return true;
                }
            }
            // Check Top
            if (this.row - 1 >= 0) {
                // Check Top-Mid
                if (board[this.row - 1][this.col] == 'k') {
                    return true;
                }
                // Check Top-Left
                if (this.col - 1 >= 0 && (board[this.row - 1][this.col - 1] == 'k' || board[this.row - 1][this.col - 1] == 'p')) {
                    return true;
                }
                // Check Top-Right
                if (this.col + 1 < 8 && (board[this.row - 1][this.col + 1] == 'k' || board[this.row - 1][this.col + 1] == 'p')) {
                    return true;
                }
            }
            // Check Left/Right
            if (this.col + 1 < 8 && board[this.row][this.col + 1] == 'k') {
                return true;
            }
            if (this.col - 1 >= 0 && board[this.row][this.col - 1] == 'k') {
                return true;
            }

            return false;

        } else {
            // Black check
            if (queenExists) {
                // Check if Rook/Queen exists on Row/Col of King
                for (int i = 0; i < 8; i++) {
                    if (board[this.row][i] == 'R' || board[this.row][i] == 'Q') {
                        if(this.checkRow(i, board)) {
                            return true;
                        }
                    }
                    if (board[i][this.col] == 'R' || board[i][this.col] == 'Q') {
                        if(this.checkCol(i, board)) {
                            return true;
                        }
                    }
                    if (this.row + i < 8 && this.col + i < 8 && !blockedLR) {
                        // check lower right diag
                        char lowerRight = board[this.row + i][this.col + i];
                        if (lowerRight != '.') {
                            if (lowerRight == 'B' || lowerRight == 'Q') {
                                return true;
                            } else {
                                blockedLR = true;
                            }
                        }
                    }
                    int step = i + 1;
                    if (this.row + step < 8 && this.col - step >= 0 && !blockedLL) {
                        // check lower left diag
                        char lowerLeft = board[this.row + step][this.col - step];
                        if (lowerLeft != '.') {
                            if (lowerLeft == 'B' || lowerLeft == 'Q') {
                                return true;
                            } else {
                                blockedLL = true;
                            }
                        }
                    }
                    if (this.row - step >= 0 && this.col + step < 8 && !blockedUR) {
                        // check upper right diag
                        char upperRight = board[this.row - step][this.col + step];
                        if (upperRight != '.') {
                            if (upperRight == 'B' || upperRight == 'Q') {
                                return true;
                            } else {
                                blockedUR = true;
                            }
                        }
                    }
                    if (this.row - step >= 0 && this.col - step >= 0 && !blockedUL) {
                        // check upper left diag
                        char upperLeft = board[this.row - step][this.col - step];
                        if (upperLeft != '.') {
                            if (upperLeft == 'B' || upperLeft == 'Q') {
                                return true;
                            } else {
                                blockedUL = true;
                            }
                        }
                    }
                }
                // Check if Bishop/Queen exists on Diag of King
            } else {
                if (rookExists) {
                    for (int i = 0; i < 8; i++) {
                        if (board[this.row][i] == 'R') {
                            if(this.checkRow(i, board)) {
                                return true;
                            }
                        }
                        if (board[i][this.col] == 'R') {
                            if(this.checkCol(i, board)) {
                                return true;
                            }
                        }
                    }
                }
                if (bishopExists) {
                    for (int i = 1; i < 8; i++) {
                        if (this.row + i < 8 && this.col + i < 8 && !blockedLR) {
                            // check lower right diag
                            char lowerRight = board[this.row + i][this.col + i];
                            if (lowerRight != '.') {
                                if (lowerRight == 'B') {
                                    return true;
                                } else {
                                    blockedLR = true;
                                }
                            }
                        }
                        if (this.row + i < 8 && this.col - i >= 0 && !blockedLL) {
                            // check lower left diag
                            char lowerLeft = board[this.row + i][this.col - i];
                            if (lowerLeft != '.') {
                                if (lowerLeft == 'B') {
                                    return true;
                                } else {
                                    blockedLL = true;
                                }
                            }
                        }
                        if (this.row - i >= 0 && this.col + i < 8 && !blockedUR) {
                            // check upper right diag
                            char upperRight = board[this.row - i][this.col + i];
                            if (upperRight != '.') {
                                if (upperRight == 'B') {
                                    return true;
                                } else {
                                    blockedUR = true;
                                }
                            }
                        }
                        if (this.row - i >= 0 && this.col - i >= 0 && !blockedUL) {
                            // check upper left diag
                            char upperLeft = board[this.row - i][this.col - i];
                            if (upperLeft != '.') {
                                if (upperLeft == 'B') {
                                    return true;
                                } else {
                                    blockedUL = true;
                                }
                            }
                        }
                    }
                }
            }
            if (knightExists) {
                // Check Upper options
                if (this.row - 2 >= 0) {
                    if (this.col - 1 >= 0 && board[this.row - 2][this.col - 1] == 'N') {
                        return true;
                    }
                    if (this.col + 1 < 8 && board[this.row - 2][this.col + 1] == 'N') {
                        return true;
                    }
                }
                // Check Upper-Mid options
                if (this.row - 1 >= 0) {
                    if (this.col - 2 >= 0 && board[this.row - 1][this.col - 2] == 'N') {
                        return true;
                    }
                    if (this.col + 2 < 8 && board[this.row - 1][this.col + 2] == 'N') {
                        return true;
                    }
                }
                // Check Lower-Mid options
                if (this.row + 1 < 8) {
                    if (this.col - 2 >= 0 && board[this.row + 1][this.col - 2] == 'N') {
                        return true;
                    }
                    if (this.col + 2 < 8 && board[this.row + 1][this.col + 2] == 'N') {
                        return true;
                    }
                }
                // Check Lower options
                if (this.row + 2 < 8) {
                    if (this.col - 1 >= 0 && board[this.row + 2][this.col - 1] == 'N') {
                        return true;
                    }
                    if (this.col + 1 < 8 && board[this.row + 2][this.col + 1] == 'N') {
                        return true;
                    }
                }
            }
            // Check if other king has you in check or pawn while we're at it
            // Check Bottom
            if (this.row + 1 < 8) {
                // Check Bottom-Mid
                if (board[this.row + 1][this.col] == 'K') {
                    return true;
                }
                // Check Bottom-Left
                if (this.col - 1 >= 0 && (board[this.row + 1][this.col - 1] == 'K' || board[this.row + 1][this.col - 1] == 'P')) {
                    return true;
                }
                // Check Bottom-Right
                if (this.col + 1 < 8 && (board[this.row + 1][this.col + 1] == 'K' || board[this.row + 1][this.col + 1] == 'P')) {
                    return true;
                }
            }
            // Check Top
            if (this.row - 1 >= 0) {
                // Check Top-Mid
                if (board[this.row - 1][this.col] == 'K') {
                    return true;
                }
                // Check Top-Left
                if (this.col - 1 >= 0 && board[this.row - 1][this.col - 1] == 'K') {
                    return true;
                }
                // Check Top-Right
                if (this.col + 1 < 8 && board[this.row - 1][this.col + 1] == 'K') {
                    return true;
                }
            }
            // Check Left/Right
            if (this.col + 1 < 8 && board[this.row][this.col + 1] == 'K') {
                return true;
            }
            if (this.col - 1 >= 0 && board[this.row][this.col - 1] == 'K') {
                return true;
            }

            return false;
        }
    }

    public String toString() {
        return "Row: " + this.row + ", Col: " + this.col;
    }
}

class Check {
    public static void main(String[] args) throws IOException {
        // final char QUEEN = "q";
        // final char KING = 'k';
        // final char BISHOP = 'b';
        // final char ROOK = 'r';
        // final char KNIGHT = 'n';
        // final char PAWN = 'p';
        
        // Read board
        // Scanner in = new Scanner(System.in);
        Scanner in = new Scanner(new FileInputStream(args[0]));
        int gameNum = 1;
        boolean emptyBoard = false;
        while (!emptyBoard) {
            emptyBoard = true;
            // init board state
            char[][] board = new char[8][8];
            ArrayList<Pair> whiteRooks = new ArrayList<Pair>();
            ArrayList<Pair> whiteBishops = new ArrayList<Pair>();
            ArrayList<Pair> whitePawns = new ArrayList<Pair>();
            ArrayList<Pair> whiteKnights = new ArrayList<Pair>();
            Pair whiteKing = new Pair('w');
            Pair whiteQueen = new Pair('w');
            
            ArrayList<Pair> blackRooks = new ArrayList<Pair>();
            ArrayList<Pair> blackBishops = new ArrayList<Pair>();
            ArrayList<Pair> blackPawns = new ArrayList<Pair>();
            ArrayList<Pair> blackKnights = new ArrayList<Pair>();
            Pair blackKing = new Pair('b');
            Pair blackQueen = new Pair('b');

            // read board
            for (int i = 0; i < 8; i++) {
                String row = in.nextLine();
                for (int j = 0; j < 8; j++) {
                    char current = row.charAt(j);
                    board[i][j] = current;
                    if (emptyBoard && current != '.') {
                        emptyBoard = false;
                    }
                    // Keep an array of what each player has
                    if (current >= 'a' && current <= 'z') {
                        // Lowercase (black piece)
                        if (current == 'q') {
                            blackQueen.setLocation(i, j);
                        } else if (current == 'r') {
                            blackRooks.add(new Pair(i, j, 'b'));
                        } else if (current == 'b') {
                            blackBishops.add(new Pair(i, j, 'b'));
                        } else if (current == 'n') {
                            blackKnights.add(new Pair(i, j, 'b'));
                        } else if (current == 'p') {
                            blackPawns.add(new Pair(i, j, 'b'));
                        } else if (current == 'k') {
                            blackKing.setLocation(i, j);
                        }
                    } else if (current >= 'A' && current <= 'Z') {
                        // Uppercase (white piece)
                        if (current == 'Q') {
                            whiteQueen.setLocation(i, j);
                        } else if (current == 'R') {
                            whiteRooks.add(new Pair(i, j, 'w'));
                        } else if (current == 'B') {
                            whiteBishops.add(new Pair(i, j, 'w'));
                        } else if (current == 'N') {
                            whiteKnights.add(new Pair(i, j, 'w'));
                        } else if (current == 'P') {
                            whitePawns.add(new Pair(i, j, 'w'));
                        } else if (current == 'K') {
                            whiteKing.setLocation(i, j);
                        }
                    }
                }
            }

            // // Print black pieces
            // System.out.println("Black Pieces:");
            // System.out.println("\tRooks:");
            // for (Pair location: blackRooks) {
            //     System.out.println("\t\t" + location);
            // }
            // System.out.println("\tBishops:");
            // for (Pair location: blackBishops) {
            //     System.out.println("\t\t" + location);
            // }
            // System.out.println("\tKnights:");
            // for (Pair location: blackKnights) {
            //     System.out.println("\t\t" + location);
            // }
            // System.out.println("\tPawns:");
            // for (Pair location: blackPawns) {
            //     System.out.println("\t\t" + location);
            // }
            // System.out.println("\tQueen:");
            // if (blackQueen.isSet()) {
            //     System.out.println("\t\t" + blackQueen);
            // }
            // System.out.println("\tKing:");
            // if (blackKing.isSet()) {
            //     System.out.println("\t\t" + blackKing);   
            // }

            // // Print white pieces 
            // System.out.println("White Pieces:");
            // System.out.println("\tRooks:");
            // for (Pair location: whiteRooks) {
            //     System.out.println("\t\t" + location);
            // }
            // System.out.println("\tBishops:");
            // for (Pair location: whiteBishops) {
            //     System.out.println("\t\t" + location);
            // }
            // System.out.println("\tKnights:");
            // for (Pair location: whiteKnights) {
            //     System.out.println("\t\t" + location);
            // }
            // System.out.println("\tPawns:");
            // for (Pair location: whitePawns) {
            //     System.out.println("\t\t" + location);
            // }
            // System.out.println("\tQueen:");
            // if (whiteQueen.isSet()) {
            //     System.out.println("\t\t" + whiteQueen);
            // }
            // System.out.println("\tKing:");
            // if (whiteKing.isSet()) {
            //     System.out.println("\t\t" + whiteKing);
            // }


            // Process Board
            if (!emptyBoard) {
                System.out.print("Game #" + gameNum + ": ");
                if (blackKing.inCheck(whiteRooks, whiteBishops, whitePawns, whiteKnights, whiteQueen, whiteKing, board)) {
                    System.out.println("black king is in check.");
                } else if (whiteKing.inCheck(blackRooks, blackBishops, blackPawns, blackKnights, blackQueen, blackKing, board)) {
                    System.out.println("white king is in check.");
                } else {
                    System.out.println("no king is in check.");
                }
                gameNum++;
                in.nextLine();
            } else {
                System.exit(0);
            }
        }
        System.exit(0);
    }
}