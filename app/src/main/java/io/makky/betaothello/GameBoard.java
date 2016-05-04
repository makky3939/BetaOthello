package io.makky.betaothello;

import java.util.HashMap;
import java.util.Map;

public class GameBoard {
    Boolean turn = true;
    int step = 0;

    int[][] board = new int[8][8];

    GameBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = 0;
            }
        }

        this.board[3][3] = this.board[4][4] = 1;
        this.board[3][4] = this.board[4][3] = 2;
    }


    public boolean getTurn() {
        return turn;
    }

    public int getStep() {return step; }

    public int[][] getBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 3) {
                    board[i][j] = 0;
                }

                if (board[i][j] == 0 && this.checkReversibleCells(i, j) > 0) {
                    board[i][j] = 3;
                }
            }
        }
        return board;
    }

    public Map<String, Integer> countCellsStat() {
        Map<String, Integer> stat = new HashMap();
        int black = 0;
        int white = 0;
        int free = 0;

        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                if (this.board[i][j] == 1) {
                    white += 1;
                } else if (this.board[i][j] == 2) {
                    black += 1;
                } else {
                    free += 1;
                }
            }
        }

        stat.put("BLACK", black);
        stat.put("WHITE", white);
        stat.put("FREE", free);
        return stat;
    }

    public void selectCell(int i, int j) {
        scanLine(i, j, 1, 0, true);
        scanLine(i, j, 0, 1, true);
        scanLine(i, j, -1, 0, true);
        scanLine(i, j, 0, -1, true);

        scanLine(i, j, 1, 1, true);
        scanLine(i, j, -1, -1, true);
        scanLine(i, j, 1, -1, true);
        scanLine(i, j, -1, 1, true);
        board[i][j] = turn ? 2 : 1;
        turn = !turn;
        step += 1;
    }

    public boolean isSelectableCell(int i, int j) {
        return board[i][j] == 3;
    }

    private int checkReversibleCells(int i, int j) {
        int total = 0;

        total += scanLine(i, j, 1, 0, false);
        total += scanLine(i, j, 0, 1, false);
        total += scanLine(i, j, -1, 0, false);
        total += scanLine(i, j, 0, -1, false);

        total += scanLine(i, j, 1, 1, false);
        total += scanLine(i, j, -1, -1, false);
        total += scanLine(i, j, 1, -1, false);
        total += scanLine(i, j, -1, 1, false);
        return total;
    }

    private int scanLine(int y, int x, int dy, int dx, boolean force) {
        int total = 0;
        int currentTurnType = turn ? 2 : 1;

        if (force && this.scanLine(y, x, dy, dx, false) == 0) {
            return 0;
        }

        x += dx;
        y += dy;

        if (x <= 0 || x > 7 || y <= 0 || y > 7) {
            return 0;
        }


        if (this.board[y][x] != (turn ? 1 : 2)) {
            return 0;
        }

        while (x >= 0 && x < 8 && y >= 0 && y < 8) {
            if (this.board[y][x] == 0 || this.board[y][x] == 3) {
                return 0;
            }
            if (this.board[y][x] == currentTurnType) {
                return total;
            }
            if (force) {this.board[y][x] = turn ? 2 : 1;}
            total += 1;

            x += dx;
            y += dy;
        }
        return 0;
    }

}