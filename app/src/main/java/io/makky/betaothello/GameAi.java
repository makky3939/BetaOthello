package io.makky.betaothello;

import java.util.ArrayList;
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

        if (choice.size() != 0) {
            result[0] = choice.get(0)[0];
            result[1] = choice.get(0)[1];
        }

        return result;
    }
}
