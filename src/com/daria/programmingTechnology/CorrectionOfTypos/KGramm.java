package com.daria.programmingTechnology.CorrectionOfTypos;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Daria on 22.12.2017.
 */
public class KGramm {
    public static Set<String> makeKGramm(String input, int k){
        Set<String> result = new HashSet<>();
        int n = input.length();
        if(n <= k){
            result.add(input);
            return result;
        }
        for(int i = 0; i < n - k + 1; i++){
            String tmp = input.substring(i, i + k);
            result.add(tmp);
        }
        return result;
    }
}
