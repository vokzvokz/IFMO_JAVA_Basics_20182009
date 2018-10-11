package ru.ifmo.cet.javabasics;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class WarAndPeaceExercise {
    private static Map<String, Integer> dictionary = new TreeMap<String, Integer>();
    private static String alphabet = " qwertyuiopasdfghjklzxcvbnmMNBVCXZLKJHGFDSAPOIUYTREWQйцукенгшщзхъфывапролджэячсмитьбюёЁЮБЬТИМСЧЯЭЖДЛОРПАВЫФЪХЗЩШГНЕКУЦЙ";
    private static String stringToFormat(String word){
        String formatedString = "";
        for(int i = 0; i<word.length(); i++){
            if(alphabet.contains(Character.toString(word.charAt(i)))){
                formatedString=formatedString+Character.toString(word.charAt(i));
            }
        }
        return formatedString;
    }
    public static String warAndPeace()  throws IOException {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");
        final Charset charset = Charset.forName("windows-1251");
        List<String> strings = Files.readAllLines(tome12Path, charset);
        strings.addAll(Files.readAllLines(tome34Path, charset));
        for(String string : strings){
            string = string.replaceAll("[^a-zA-Zа-яА-Я]", " ");
            for(String word : string.split(" ")){
                if(word.length()>=4){
                    word = word.toLowerCase();
                    if(dictionary.containsKey(word))
                        dictionary.put(word, dictionary.get(word)+1);
                    else dictionary.put(word, 1);
                }
            }
        }
        // full dictionary
        // sorting:
        List <Map.Entry<String, Integer>> list =
                new ArrayList<Map.Entry<String, Integer>>(dictionary.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                int res = a.getValue() - b.getValue();
                if(res == 0) res = -a.getKey().compareTo(b.getKey());
                return res;

            }
        });
        String wordsList = "";
        int i=list.size()-1;
        while(list.get(i).getValue()>=10){

            wordsList=wordsList+list.get(i).getKey()+" - "+list.get(i).getValue()+
                    ((list.get(i-1).getValue()>=10)?"\n":"");
            i--;
        }
        // TODO map lowercased words to its amount in text and concatenate its entries.
        // TODO If word "котик" occurred in text 23 times then its entry would be "котик - 23\n".
        // TODO Entries in final String should be also sorted by amount and then in alphabetical order if needed.
        // TODO Also omit any word with lengths less than 4 and frequency less than 10
        return wordsList;
        //throw new UnsupportedOperationException();
    }
}