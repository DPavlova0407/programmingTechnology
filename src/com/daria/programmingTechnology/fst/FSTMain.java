package com.daria.programmingTechnology.fst;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Daria on 22.12.2017.
 */
public class FSTMain {
    public static void main(String[] args) throws IOException {
        MyFST myFST = new MyFST();
        String dictFile = "C:\\Users\\Daria\\programmingTechnology\\dict.txt";
        List<String> words = myFST.readDictionary(dictFile);

        String fstSavePath = "C:\\Users\\Daria\\programmingTechnology\\output\\outFst.bin";
        myFST.saveFST(myFST.wordsToFST(words), Paths.get(fstSavePath));

        //--- проверка
        /*List<String> words1 = myFST.FSTToWords(myFST.readFSTFromFile(Paths.get(fstSavePath)));
        for (int i = 0; i < words1.size(); i++){
            System.out.println(words1.get(i));
        }*/
    }
}
