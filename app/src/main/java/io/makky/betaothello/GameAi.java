package io.makky.betaothello;

import java.util.ArrayList;
import java.util.HashMap;
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
}
