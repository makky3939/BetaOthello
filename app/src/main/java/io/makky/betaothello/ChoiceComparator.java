package io.makky.betaothello;

import java.util.Comparator;

public class ChoiceComparator implements Comparator<Integer[]> {
    @Override public int compare(Integer[] p1, Integer[] p2) {
        return p1[2] < p2[2] ? -1 : 1;
    }
}