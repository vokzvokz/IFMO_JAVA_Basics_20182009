package ru.ifmo.cet.javabasics;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WarAndPeaceExercise {

    public static String warAndPeace() throws IOException{
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");
        final Charset charset = Charset.forName("windows-1251");
        List<String> strings = Files.readAllLines(tome12Path, charset);
        strings.addAll(Files.readAllLines(tome34Path, charset));
        Map<String, Integer> dictionary = new TreeMap<String, Integer>();

        String text = strings.toString().replaceAll("[^a-zA-Zа-яА-Я]", " ").toLowerCase();
        Stream<String> reading = Stream.of(text.split(" "));
        reading.filter((word) -> word.length() >= 4).forEach((word) -> {
            dictionary.merge(word, 1, (oldWal, newWal) -> oldWal+newWal);
        });
        List<Map.Entry<String, Integer>> forSorting =
                new LinkedList(dictionary.entrySet());
        forSorting.sort(Map.Entry.comparingByKey());
        forSorting.sort(Map.Entry.<String, Integer>comparingByValue().reversed());
        StringBuffer result = new StringBuffer();
        forSorting.stream().filter(counter -> counter.getValue()>=10)
            .forEach(counter ->{
                result.append(counter.getKey()+" - "+counter.getValue()+"\n");
            });
        return result.toString().substring(0, result.length()-1);
        //throw new UnsupportedOperationException();
    }
}