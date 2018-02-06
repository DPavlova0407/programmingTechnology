package com.daria.programmingTechnology;

import com.daria.programmingTechnology.Lucene.LuceneConstants;
import com.daria.programmingTechnology.Lucene.Searcher;
import org.apache.lucene.queryparser.classic.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by Daria on 14.12.2017.
 */
public class Main {
    private static int ErrorCount = 0;
    static int counter = 1;
    private static String URL = "https://www.regard.ru/catalog/group24000/page";

    private static String indexDirectoryPath = "C:\\Users\\Daria\\programmingTechnology\\index";
    private static final ArrayList<Good> list = new ArrayList<Good>();
    private static final Set<String> brandsList = new HashSet<>();
    private static final Set<String> typesList = new HashSet<>();
    private static Set<String> setOfGoods = new HashSet<String>();

    public static void main(String[] args) throws IOException, ParseException {
        list.clear();
        //brandsList.clear();
        //typesList.clear();
        //---------- выгрузка с сайта
        //Load();
        //---------- запись в файл
        //Save();
        //---------- чтение данных из файла
        dataFromFile("goods.txt");
        System.out.println(list.size() + " элементов");
        //---------- Индексация
        //Indexer indexer = new Indexer(indexDirectoryPath);
        //indexer.createIndex(list);

        /*System.out.println("LIST...");
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }*/
        //----------- Поиск
        Searcher searcher = new Searcher();
/*        ArrayList<Good> searchres = searcher.search(LuceneConstants.DESCRIPTION, "Full HD", indexDirectoryPath, "");
        ArrayList<Good> searchResult1 = searcher.search(LuceneConstants.BRAND, "Dell", indexDirectoryPath, "");
        ArrayList<Good> searchResult2 = searcher.search(LuceneConstants.BRAND, "Asus", indexDirectoryPath, "");
        ArrayList<Good> searchResult3 = searcher.search(LuceneConstants.BRAND, "Acer", indexDirectoryPath, "");
        ArrayList<Good> searchResult4 = searcher.search(LuceneConstants.BRAND, "Braun", indexDirectoryPath, "");

        ArrayList<Good> searchResult5 = searcher.search(LuceneConstants.TYPE, "чехол", indexDirectoryPath, "");
        ArrayList<Good> searchResult6 = searcher.search(LuceneConstants.TYPE, "клавиатура", indexDirectoryPath, "");
        ArrayList<Good> searchResult7 = searcher.search(LuceneConstants.TYPE, "DVD-плеер", indexDirectoryPath, "");
        ArrayList<Good> searchResult8 = searcher.search(LuceneConstants.TYPE, "ноутбук", indexDirectoryPath, "");
/**/
 /*       //---------- Синонимы
        //"минисистема", "мидисистема", "микросистема", "музыкальныйцентр"
        ArrayList<Good> searchResult9 = searcher.search(LuceneConstants.TYPE, "мидисистема", indexDirectoryPath, "");
        ArrayList<Good> searchResult10 = searcher.search(LuceneConstants.TYPE, "минисистема", indexDirectoryPath, "");
        ArrayList<Good> searchResult11 = searcher.search(LuceneConstants.TYPE, "микросистема", indexDirectoryPath, "");
        ArrayList<Good> searchResult12 = searcher.search(LuceneConstants.TYPE, "музыкальныйцентр", indexDirectoryPath, "");

        System.out.println("Синонимы....");

        ArrayList<Good> searchResult13 = searcher.search(LuceneConstants.TYPE, "мидисистема", indexDirectoryPath, "Synonym");
        ArrayList<Good> searchResult14 = searcher.search(LuceneConstants.TYPE, "минисистема", indexDirectoryPath, "Synonym");
        ArrayList<Good> searchResult15 = searcher.search(LuceneConstants.TYPE, "микросистема", indexDirectoryPath, "Synonym");
        ArrayList<Good> searchResult16 = searcher.search(LuceneConstants.TYPE, "музыкальныйцентр", indexDirectoryPath, "Synonym");

        System.out.println("-------------------------------");

        //"SSD", "кэширующий накопитель", "HDD", "гибридный", "жесткийдиск"
        ArrayList<Good> searchResult17 = searcher.search(LuceneConstants.TYPE, "SSD", indexDirectoryPath, "");
        ArrayList<Good> searchResult18 = searcher.search(LuceneConstants.TYPE, "гибридный", indexDirectoryPath, "");
        ArrayList<Good> searchResult19 = searcher.search(LuceneConstants.TYPE, "HDD", indexDirectoryPath, "");
        ArrayList<Good> searchResult20 = searcher.search(LuceneConstants.TYPE, "жесткийдиск", indexDirectoryPath, "");

        System.out.println("Синонимы...");

        ArrayList<Good> searchResult21 = searcher.search(LuceneConstants.TYPE, "SSD", indexDirectoryPath, "Synonym");
        ArrayList<Good> searchResult22 = searcher.search(LuceneConstants.TYPE, "гибридный", indexDirectoryPath, "Synonym");
        ArrayList<Good> searchResult23 = searcher.search(LuceneConstants.TYPE, "HDD", indexDirectoryPath, "Synonym");
        ArrayList<Good> searchResult24 = searcher.search(LuceneConstants.TYPE, "жесткийдиск", indexDirectoryPath, "Synonym");
        /**/
        //------------ NDCG
        /**/
        //---------------------------------- Search1
        ArrayList<Good> result1 = searcher.search2(LuceneConstants.BRAND, "Acer",
                LuceneConstants.TYPE, "ноутбук",
                LuceneConstants.DESCRIPTION, "Intel Core i5",
                indexDirectoryPath, "");
        FileWriter fileWriter = new FileWriter("result1.txt");
        for(Good g : result1){
            fileWriter.write(g.toSimpleString());
        }
        fileWriter.close();

        ArrayList<Good> res1 = new ArrayList<>();
        for(int i = 0; i < 15; i++){
            res1.add(result1.get(i));
        }
        Map<Good, Integer> respMap1 = setResponse(new Response("Acer", "ноутбук", "Intel Core i5"), res1);
        Map<Good, Integer> myRespMap1 = new HashMap<>();
        myRespMap1.put(res1.get(0), 2);
        myRespMap1.put(res1.get(1), 2);
        myRespMap1.put(res1.get(2), 2);
        myRespMap1.put(res1.get(3), 3);
        myRespMap1.put(res1.get(4), 3);
        myRespMap1.put(res1.get(5), 3);
        myRespMap1.put(res1.get(6), 3);
        myRespMap1.put(res1.get(7), 3);
        myRespMap1.put(res1.get(8), 2);
        myRespMap1.put(res1.get(9), 2);
        myRespMap1.put(res1.get(10), 3);
        myRespMap1.put(res1.get(11), 3);
        myRespMap1.put(res1.get(12), 3);
        myRespMap1.put(res1.get(13), 3);
        myRespMap1.put(res1.get(14), 3);

        System.out.println("NDCG_1 = " + Ranking.getNDCG(respMap1));
        System.out.println("NDCG_my = " + Ranking.getNDCG(myRespMap1));

        /*ArrayList<Double> weight11 = tf_idf("Acer", result1);
        //ArrayList<Double> weight12 = tf_idf("ноутбук", result1);
        ArrayList<Double> weight13 = tf_idf("Intel Core i5", result1);
        ArrayList<Double> resWeight1 = new ArrayList<>();
        for (int i = 0; i < weight11.size(); i++){
            resWeight1.add(weight11.get(i) + weight12.get(i) + weight13.get(i));
        }
        ArrayList<Good> sortedList1 = sort(resWeight1, res1);
        FileWriter fw1 = new FileWriter("sorted1.txt");
        for (Good g : sortedList1) {
            fw1.write(g.toSimpleString());
        }
        fw1.close();
        Map<Good, Integer> respMap1_new = setResponse(new Response("Acer", "ноутбук", "Intel Core i5"), sortedList1);
        System.out.println("new NDCG = " + Ranking.getNDCG(respMap1_new));
        /**/
        //---------------------------------- Search2
        ArrayList<Good> result2 = searcher.search2(LuceneConstants.BRAND, "Benro",
                LuceneConstants.TYPE, "сумка для фотокамеры",
                LuceneConstants.DESCRIPTION, "место для дополнительного объектива",
                indexDirectoryPath, "");
        FileWriter fileWriter2 = new FileWriter("result2.txt");
        for(Good g : result2){
            fileWriter2.write(g.toSimpleString());
        }
        fileWriter2.close();

        ArrayList<Good> res2 = new ArrayList<>();
        for(int i = 0; i < 15; i++){
            res2.add(result2.get(i));
        }

        Map<Good, Integer> respMap2 = setResponse(new Response("Benro", "сумка для фотокамеры", "место для дополнительного объектива"), res2);
        Map<Good, Integer> myRespMap2 = new HashMap<>();
        myRespMap2.put(res2.get(0), 2);
        myRespMap2.put(res2.get(0), 3);
        myRespMap2.put(res2.get(0), 3);
        myRespMap2.put(res2.get(0), 2);
        myRespMap2.put(res2.get(0), 2);
        myRespMap2.put(res2.get(0), 2);
        myRespMap2.put(res2.get(0), 2);
        myRespMap2.put(res2.get(0), 3);
        myRespMap2.put(res2.get(0), 3);
        myRespMap2.put(res2.get(0), 3);
        myRespMap2.put(res2.get(0), 3);
        myRespMap2.put(res2.get(0), 3);
        myRespMap2.put(res2.get(0), 3);
        myRespMap2.put(res2.get(0), 3);
        myRespMap2.put(res2.get(0), 2);
        System.out.println("NDCG_2 = " + Ranking.getNDCG(respMap2));
        System.out.println("NDCG_my = " + Ranking.getNDCG(myRespMap2));

       /* ArrayList<Double> weight1 = tf_idf("Benro", result2);
        ArrayList<Double> weight2 = tf_idf("сумка для фотокамеры", result2);
        ArrayList<Double> weight3 = tf_idf("место для дополнительного объектива", result2);
        ArrayList<Double> resWeight = new ArrayList<>();
        for (int i = 0; i < res2.size(); i++){
            resWeight.add(weight1.get(i) + weight2.get(i) + weight3.get(i));
        }
       ArrayList<Good> sortedList = sort(resWeight, result2);

        FileWriter fw = new FileWriter("sorted2.txt");
        for (Good g : sortedList) {
            fw.write(g.toSimpleString());
        }
        fw.close();
        Map<Good, Integer> respMap2_new = setResponse(new Response("Benro", "сумка для фотокамеры", "место для дополнительного объектива"), sortedList);
        System.out.println("new NDCG = " + Ranking.getNDCG(respMap2_new));

        //System.out.println(Ranking.getNDCG(respMap1));
        //System.out.println(Ranking.getNDCG(respMap2));
        /**/
        //Map<Good, Integer> respMap1 = setResponse(new Response("Acer", "ноутбук", "Intel"));

        /*ArrayList<Good> result2 = searcher.search2(LuceneConstants.BRAND, "Sven", LuceneConstants.TYPE, "мышь", LuceneConstants.DESCRIPTION, "проводная", indexDirectoryPath, "");
        Map<Good, Integer> respMap2 = setResponse(new Response("Sven", "мышь", "проводная"));

        ArrayList<Good> result3 = searcher.search2(LuceneConstants.BRAND, "Western Digital", LuceneConstants.TYPE, "HDD", LuceneConstants.DESCRIPTION, "120 Гб", indexDirectoryPath, "");
        Map<Good, Integer> respMap3 = setResponse(new Response("Western Digital", "HDD", "120 Гб"));

        double sum = Ranking.getNDCG(respMap1) + Ranking.getNDCG(respMap2) + Ranking.getNDCG(respMap3);

        System.out.println(Ranking.getNDCG(respMap1));
        System.out.println(Ranking.getNDCG(respMap2));
        System.out.println(Ranking.getNDCG(respMap3));
        System.out.println("The mean value of NDCG: " + sum / 3);
        /**/
    }

    private static ArrayList<Good> sort1(ArrayList<Good> searchResult){
        Collections.sort(searchResult, new Comparator<Good>() {
            @Override
            public int compare(Good o1, Good o2) {
                    if (o1.getBrand().equals(o2.getBrand()))
                        return o1.getDescription().compareTo(o2.getDescription());
                    else
                        return o1.getBrand().compareTo(o2.getBrand());
                //if (o1.getBrand().equals(o2.getBrand())){
                    //if (o1.getType().equals(o2.getType())){
                     //   return o1.getDescription().compareTo(o2.getDescription());
                   // }else{
                    //    return o1.getType().compareTo(o2.getType());
                  //  }
                //} else{
                 //   return o1.getBrand().compareTo(o2.getBrand());
                //}
            }
        });
        Collections.reverse(searchResult);
        return searchResult;
    }/**/

    private static ArrayList<Good> sort( ArrayList<Double> resWeight, ArrayList<Good> searchResult) throws IOException {
        Map<Double, Set<Good>> tf_idf_map = new HashMap<>();
        for (int i = 0; i < resWeight.size(); i++){
            if (tf_idf_map.containsKey(resWeight.get(i))){
                tf_idf_map.get(resWeight.get(i)).add(searchResult.get(i));
            }else {
                Set<Good> l1 = new HashSet<>();
                l1.add(searchResult.get(i));
                tf_idf_map.put(resWeight.get(i), l1);
            }
        }
        ArrayList<Good> sortedList = new ArrayList<>();
        Collections.sort(resWeight);
        Set<Double> weights = new HashSet<>(resWeight);
        for (Double i : weights){
            for (Good g : tf_idf_map.get(i)){
                sortedList.add(g);
            }
        }
      //  Collections.reverse(sortedList);
        return sortedList;
    }

    /*private static Map<Double,Good> sort( ArrayList<Double> resWeight, ArrayList<Good> searchResult){
        Map<Double, List<Good>> tf_idf_map = new HashMap<>();
        for (int i = 0; i < resWeight.size(); i++){
            if (tf_idf_map.containsKey(resWeight.get(i))){

                tf_idf_map.get(resWeight.get(i)).add(searchResult.get(i));
            }else {
                List<Good> l1 = new ArrayList<Good>();
                l1.add(searchResult.get(i));
                tf_idf_map.put(resWeight.get(i), l1);
            }
        }
        List<Map.Entry<Double, Good>> lst = new ArrayList<>(tf_idf_map.entrySet());
        Collections.sort(lst, new Comparator<Map.Entry<Double, Good>>(){
            public int compare(Map.Entry<Double, Good> o1, Map.Entry<Double, Good> o2){
                return o2.getKey().compareTo(o1.getKey());
            }
        });
        Map<Double,Good> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<Double, Good> entry : lst){
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }*/

     private static ArrayList<Double> tf_idf(String t, ArrayList<Good>searchResult){
         ArrayList<Double> result = new ArrayList<>();
         for (int i = 0; i < searchResult.size(); i++){
             double tfi = 1 + Math.log(tf(t, searchResult.get(i)));
             double idfi = idf(t,searchResult);

             result.add(tfi * idfi);
         }
         return result;
    }

    private static double idf(String t, ArrayList<Good> searchResult){
        int N = searchResult.size();
        int sd = df(t,searchResult);
        return Math.log(N/sd);
    }
    private static int df(String t, ArrayList<Good> searchResult){
        int res = 0;
        for (int i = 0; i < searchResult.size(); i++){
            if (searchResult.get(i).toSimpleString().contains(t))
                res++;
        }
        return res;
    }

    private static int tf(String t, Good d){
        int res = 0;
        String doc = d.toSimpleString();
        res = (doc + "\0").split(t).length - 1;
        return res;
    }

    private static void Save() throws IOException {
        dataToFile();
        brandsToFile();
        typesToFile();
    }

    private static void Load(){
        System.out.println("Data is loading, please wait."); //1
        long timestart = System.currentTimeMillis();
        LoadData(1, 63, URL);

        URL = "https://www.regard.ru/catalog/group52000/page"; //2
        LoadData(1, 12, URL);//2982

        URL = "https://www.regard.ru/catalog/group45000/page"; //моноблоки
        LoadData(1, 15, URL);//3573

        URL = "https://www.regard.ru/catalog/group36000/page"; //4
        LoadData(1, 13, URL);//4078

        URL = "https://www.regard.ru/catalog/group29000/page";
        LoadData(1, 20, URL);//5006

        //URL = "https://www.regard.ru/catalog/group53000/page";
        URL = "https://www.regard.ru/catalog/group53001/page";
        LoadData(1, 1, URL);//6252
        URL = "https://www.regard.ru/catalog/group57244/page"; //антенны
        LoadData(1, 2, URL);
        URL = "https://www.regard.ru/catalog/group56089/page"; // аудиомагнитола
        LoadData(1, 2, URL);
        URL = "https://www.regard.ru/catalog/group53028/page"; //домашний кинотеатр
        LoadData(1, 1, URL);
        URL = "https://www.regard.ru/catalog/group53049/page"; // кронштейн
        LoadData(1, 13, URL);
        URL = "https://www.regard.ru/catalog/group53040/page";
        LoadData(1, 1, URL);
        URL = "https://www.regard.ru/catalog/group53004/page";
        LoadData(1, 2, URL);
        URL ="https://www.regard.ru/catalog/group46000/page"; // портативный плеер
        LoadData(1, 2, URL);
        URL = "https://www.regard.ru/catalog/group16185/page";
        LoadData(1, 3, URL);
        URL = "https://www.regard.ru/catalog/group41000/page";
        LoadData(1, 5, URL);


        URL = "https://www.regard.ru/catalog/group43000/page"; // блок питания
        LoadData(1, 12, URL);

        URL = "https://www.regard.ru/catalog/group64000/page"; // бытовая техника   +-
        LoadData(1, 52, URL);//8326

        URL = "https://www.regard.ru/catalog/group5000/page";
        LoadData(1, 27, URL);//9391

        URL = "https://www.regard.ru/catalog/group13000/page";
        LoadData(1, 12, URL);//9841

        URL = "https://www.regard.ru/catalog/group14000/page";
        LoadData(1, 31, URL);//11058

        long timeend = System.currentTimeMillis();
        System.out.println("Времени на загрузку с сайта затрачено: "+ ((timeend - timestart) / 1000) + " секунд"); //2613
        System.out.println("Errors: " + ErrorCount);
    }

    private static void LoadData(int startPage, int numOfPages, String url) {
        //numOfPages = 1;
        for (int i = startPage; i < startPage + numOfPages; i++) {
            int page = i;
            Document doc = null;
            try {
                doc = Jsoup.connect(url + page + ".htm").userAgent("Yandex").get();
                Elements mainElements = doc.select("div.block");
                if (mainElements != null){
                    for (int j = 0; j < mainElements.size(); j++){
                        Element element = mainElements.get(j);
                        /*1-2-4*/
                        String good_url = Parser.findUrl(element.toString(), "<div class=\"block_img\"> \n" +
                                "   <a href=\"");
                        String good_id = Parser.findId(element.toString(), "<div class=\"code\">\n" +
                                "    ID: ");
                        String good_name = Parser.findValue(element.toString(), "class=\"header\">");
                        String good_price = Parser.findValue(element.toString(), "title=\"Добавить в корзину\">&nbsp;</a></span> \n" +
                                "   <span>");
                        String good_description = Parser.findDescription(element.toString(), "</a> \n" +
                                "  </div>");

                        String good_brand = "";
                        String good_type = "";
                        // переходим по ссылке на товар, чтобы считать характеристики (производитель, тип и т.д.)
                        Document doc1 = null;
                        good_brand = "-";
                        good_type = "-";
                        try {
                            doc1 = Jsoup.connect(good_url).userAgent("Yandex").get();
                            Elements goodElements = doc1.getElementsByTag("tr");
                            good_brand = Parser.findBrand(goodElements);
                            good_type = Parser.findType(goodElements);
                            if (url.contains("group13000")){  // клавиатуры (тип устройства вместо тип)
                                good_type = Parser.findTypeDev(goodElements);
                            }
                            //System.out.println(goodElements.size());
                        }catch (Exception e){
                            ErrorCount++;
                            e.printStackTrace();
                        }
                        if (good_brand == "")
                            good_brand = "-";
                        if (good_type == "")
                            good_type = "-";
                        /*моноблоки*/
                        if (url.contains("group45000")){
                            good_type = "моноблок";
                        }
                        /**/
                        /*антенны*/
                        if (url.contains("group57244")){
                            good_type = "антенна";
                        }
                        /**/
                        /*аудиомагнитола*/
                        if (url.contains("group56089")){
                            good_type = "аудиомагнитола";
                        }
                        /**/
                        /*домашний кинотеатр*/
                        if (url.contains("group53028")){
                            good_type = "домашний кинотеатр";
                        }
                        /**/
                        /*group53049*/
                        if (url.contains("group53049")){
                            good_type = "кронштейн";
                        }
                        /**/
                        /*блок питания*/
                        if (url.contains("group43000")){
                            good_type = "блок питания";
                        }
                        /**/
                        /*портативный плеер*/
                        if (url.contains("group46000")){
                            good_type = "портативный плеер";
                        }
                        /**/
                        /*мышь*/
                        if (url.contains("group14000")){
                            good_type = "мышь";
                        }
                        /**/
                        Good good = new Good(good_url, good_id, good_name, good_price, good_brand, good_type, good_description);
                        list.add(good);
                        brandsList.add(good_brand);
                        typesList.add(good_type);

                        System.out.println(counter);
                        counter++;
                    }
                }
            } catch (IOException e) {
                ErrorCount++;
                e.printStackTrace();
            }
        }
    }


    private static void dataToFile() throws IOException {
        FileWriter fileWriter = new FileWriter("goods.txt");
        System.out.println("Элементов-------------------");
        System.out.println(list.size());
        for(Good g : list){
            fileWriter.write(g.toSimpleString());
        }
        fileWriter.close();
    }

    private static void brandsToFile() throws IOException {
        FileWriter fileWriter = new FileWriter("brands.txt");
        for (String brand : brandsList){
            fileWriter.write(brand);
            fileWriter.write('\n');
        }
        fileWriter.close();
    }

    private static void typesToFile() throws IOException {
        FileWriter fileWriter = new FileWriter("types.txt");
        for (String type : typesList){
            fileWriter.write(type);
            fileWriter.write('\n');
        }
        fileWriter.close();
    }

    private static String makeString(String input){
        String result = input;
        if (input.charAt(0) == ' '){
            result = input.substring(1, input.length());
        }
        if (input.charAt(input.length() - 1) == ' '){
            result = input.substring(0, input.length() - 1);
        }
        return result;
    }

    private static void dataFromFile(String fileName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String urlStr = bufferedReader.readLine();
        while (urlStr != null){
            //Good good = new Good(good_url, good_id, good_name, good_price, good_brand, good_type);
            String idStr = bufferedReader.readLine(),
                    nameStr = bufferedReader.readLine(),
                    priceStr = bufferedReader.readLine(),
                    brandStr = makeString(bufferedReader.readLine()),
                    typeStr = bufferedReader.readLine(),
                    descriptionStr = bufferedReader.readLine();
            list.add(new Good(urlStr.toString(), idStr, nameStr, priceStr, brandStr, typeStr, descriptionStr));
            brandsList.add(brandStr);
            typesList.add(typeStr);
            urlStr = bufferedReader.readLine();
        }
    }

    //------------------ Релевантность
    public static Map<Good, Integer> setResponse(Response response, ArrayList<Good> searchResult){
        Map<Good, Integer> responseMap = new HashMap<>();
        for (int i = 0; i < searchResult.size(); i++){
            if(searchResult.get(i).getBrand().toLowerCase().equals(response.brandName.toLowerCase())
                    && searchResult.get(i).getType().toLowerCase().equals(response.typeName.toLowerCase())){
                responseMap.put(searchResult.get(i), 3);
                } else {
                    if (searchResult.get(i).getType().toLowerCase().equals(response.typeName.toLowerCase())) {
                        responseMap.put(searchResult.get(i), 2);
                        } else {
                        if (searchResult.get(i).getBrand().toLowerCase().equals(response.brandName.toLowerCase())) {
                            responseMap.put(searchResult.get(i), 1);
                        }
                    }
            }
        }
        return responseMap;
    }/**/
}
