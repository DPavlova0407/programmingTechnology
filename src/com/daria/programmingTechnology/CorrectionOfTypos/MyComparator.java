package com.daria.programmingTechnology.CorrectionOfTypos;

import java.util.Comparator;

/**
 * Created by Daria on 22.12.2017.
 */
public class MyComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        String first = (String) o1;
        String second = (String) o2;
        Levenshtein lev = new Levenshtein(100);
        return lev.getDistace(first, second, second.length());
    }
}
