package io.makky.betaothello;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameAi {
    public int[] primitive(int[][] boardState) {
        int[] result = new int[2];

        result[0] = 8;
        result[1] = 8;

        for (int i = 0; i < boardState.length; i++) {
            for (int j = 0; j < boardState[i].length; j++) {
                if (boardState[i][j] == 3) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }

    public int[] random(int[][] boardState) {
        int[] result = new int[2];

        result[0] = 8;
        result[1] = 8;

        int[] c = new int[2];
        List<int[]> choice = new ArrayList<int[]>();

        for (int i = 0; i < boardState.length; i++) {
            for (int j = 0; j < boardState[i].length; j++) {
                if (boardState[i][j] == 3) {
                    c[0] = i;
                    c[1] = j;
                    choice.add(c);
                }
            }
        }

        return choice.get((new Random()).nextInt(choice.size()));
    }

    public int[] randomWeighted(int[][] boardState) {
        int[] result = new int[2];

        result[0] = 8;
        result[1] = 8;

        Integer[] c = new Integer[3];

        List<Integer[]> choice = new ArrayList<Integer[]>();

        int[][] weightMap  = {
                {30, -12, 0, -1, -1, 0, -12, 30},
                {-12, -15, -3, -3, -3, -3, -15, -12},
                {0, -3, 0, -1, -1, 0, -3, -1},
                {-1, -3, -1, -1, -1, -1, -3, -1},
                {-1, -3, -1, -1, -1, -1, -3, -1},
                {0, -3, 0, -1, -1, 0, -3, -1},
                {-12, -15, -3, -3, -3, -3, -15, -12},
                {30, -12, 0, -1, -1, 0, -12, 30},
        };

        for (int i = 0; i < boardState.length; i++) {
            for (int j = 0; j < boardState[i].length; j++) {
                if (boardState[i][j] == 3) {
                    c[0] = i;
                    c[1] = j;
                    c[2] = weightMap[i][j];
                    choice.add(c);
                }
            }
        }

        Collections.sort(choice, new ChoiceComparator());

        List<Integer[]> fchoice = new ArrayList<Integer[]>();

        for (Integer[] i : choice) {
            if (i[2] == choice.get(0)[2]) {
                fchoice.add(i);
            }
        }

        if (choice.size() != 0) {
            Integer[] ffchoice = fchoice.get((new Random()).nextInt(fchoice.size()));
            result[0] = ffchoice[0];
            result[1] = ffchoice[1];
        }

        return result;
    }

    public int[] minMax(GameBoard gameBoard) {
        int[] result = new int[2];

        result[0] = 8;
        result[1] = 8;

        Integer[] c = new Integer[3];

        List<Integer[]> choice = new ArrayList<Integer[]>();
        int[][] boardState = gameBoard.getBoard();


        for (int i = 0; i < boardState.length; i++) {
            for (int j = 0; j < boardState[i].length; j++) {
                if (boardState[i][j] == 3) {
                    GameBoard gb = new GameBoard(gameBoard.getBoard(), gameBoard.getTurn(), gameBoard.getStep());
                    c[0] = i;
                    c[1] = j;
                    c[2] = mm(gb, i, j, 1);
                    choice.add(c);
                }
            }
        }

        Collections.sort(choice, new ChoiceComparator());

        if (choice.size() != 0) {
            result[0] = choice.get(0)[0];
            result[1] = choice.get(0)[1];
        }

        return result;
    }

    public int mm(GameBoard gb_, int x, int y, int depth) {
        if (depth == 0) {
            return gb_.countCellsStat().get("WHITE");
        } else {
            depth =- 1;
        }

        GameBoard __gb = new GameBoard(gb_.getBoard(), gb_.getTurn(), gb_.getStep());

        __gb .selectCell(x, y);
        int[][] boardState = __gb .getBoard();

        for (int i = 0; i < boardState.length; i++) {
            for (int j = 0; j < boardState[i].length; j++) {
                if (boardState[i][j] == 3) {
                    GameBoard gbb = new GameBoard(__gb .getBoard(), __gb .getTurn(), __gb .getStep());
                    mm(gbb, i, j, depth);
                }
            }
        }

        return 0;
    }
}
