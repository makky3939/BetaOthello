package io.makky.betaothello;

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
}
