package com.daria.programmingTechnology;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.standard.ClassicTokenizer;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.synonym.SynonymGraphFilter;
import org.apache.lucene.analysis.synonym.SynonymMap;
import org.apache.lucene.util.CharsRef;

import java.io.IOException;

/**
 * Created by Daria on 20.11.2017.
 */
public class AnalyzerSynonyms extends Analyzer {

    @Override
    protected TokenStreamComponents createComponents(String s) {
        /*минисистема
мидисистема
микросистема

музыкальный центр
----------------------
ЖК-панель (без тюнера)

телевизор
---------------------
SSD
кэширующий накопитель
HDD
гибридный

жесткий диск*/

        SynonymMap.Builder synonimMapBuilder = new SynonymMap.Builder(true);
        addSynonyms(synonimMapBuilder, new String [] {"минисистема", "мидисистема", "микросистема", "музыкальныйцентр"});
        addSynonyms(synonimMapBuilder, new String[] {"ssd", "hdd", "гибридный", "жесткийдиск"});
        SynonymMap synonyms = null;
        try {
            synonyms = synonimMapBuilder.build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Tokenizer sourse = new ClassicTokenizer();
        TokenStream filter = new StandardFilter(sourse);
        filter = new LowerCaseFilter(filter);

        filter = new SynonymGraphFilter(filter, synonyms, true);
       // filter = new SynonymFilter(filter, synonyms, true);

        return new TokenStreamComponents(sourse, filter);
    }

    private static void addSynonyms(SynonymMap.Builder synonimMapBuilder,String[] synonyms){
        for (String a : synonyms){
            for (String b : synonyms){
                synonimMapBuilder.add(new CharsRef(a), new CharsRef(b), true);
            }
        }
    }
}
