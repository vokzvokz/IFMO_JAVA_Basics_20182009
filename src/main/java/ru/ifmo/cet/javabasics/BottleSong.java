package ru.ifmo.cet.javabasics;

/**
 * Нужно реализовать констурктор и метод, возвращающий слова песни про бутылки на стене.
 * <p>
 * Слова следующие:
 * <p>
 * 99 bottles of beer on the wall, 99 bottles of beer
 * Take one down, pass it around, 98 bottles of beer
 * 98 bottles of beer on the wall, 98 bottles of beer
 * Take one down, pass it around, 97 bottles of beer
 * 97 bottles of beer on the wall, 97 bottles of beer
 * Take one down, pass it around, 96 bottles of beer
 * 96 bottles of beer on the wall, 96 bottles of beer
 * Take one down, pass it around, 95 bottles of beer
 * 95 bottles of beer on the wall, 95 bottles of beer
 * ...
 * <p>
 * 3 bottles of beer on the wall, 3 bottles of beer
 * Take one down, pass it around, 2 bottles of beer
 * 2 bottles of beer on the wall, 2 bottles of beer
 * Take one down, pass it around, 1 bottles of beer
 * 1 bottle of beer on the wall, 1 bottle of beer
 * Take one down, pass it around, no more bottles of beer on the wall
 * No more bottles of beer on the wall, no more bottles of beer
 * Go to the store and buy some more, 99 bottles of beer on the wall
 * <p>
 * Дело усложняется тем, что текст песни переменный:
 * За раз может быть взято несколько бутылок.
 * Значение передается в качестве параметра конструктора
 * Нужно ограничить возможность взятия бутылок натуральным число не более 99 бутылок за раз.
 */
public class BottleSong {

    private int bottleTakenAtOnce;
    private static String[] ones = {"", " one", " two", " three", " four", " five", " six",
            " seven", " eight", " nine"};
    private static String[] teens = {" eleven", " twelve", " thirteen", " fourteen", " fifteen",
            " sixteen", " seventeen", " eighteen", " nineteen"};
    private static String[] tens = {"", " ten", " twenty", " thirty", " forty", " fifty", " sixty",
            " seventy", " eighty", " ninety"};

    public BottleSong(int bottleTakenAtOnce) {
        this.bottleTakenAtOnce = bottleTakenAtOnce;
    }

    private static String intToWords(int a){
        if(a>10 && a <20) return teens[a-11];
        else{
            return tens[a/10]+ones[a%10];
        }
    }

    private static String numberOfBottles(int a){
        return Integer.toString(a)+((a==1)?" bottle":" bottles");
    }
    public String getBottleSongLyrics() {
        if(this.bottleTakenAtOnce>99 || this.bottleTakenAtOnce<1)
            throw new java.lang.IllegalArgumentException();
        int bottles = 99;
        String lyrics = "";
        while(bottles > this.bottleTakenAtOnce){
            lyrics = lyrics + numberOfBottles(bottles)+" of beer on the wall, "+numberOfBottles(bottles)+" of beer."
                    +"\nTake"+intToWords(this.bottleTakenAtOnce)+" down and pass around, "
                    +numberOfBottles(bottles-bottleTakenAtOnce)+" of beer on the wall.\n";
            bottles-=this.bottleTakenAtOnce;
        }
        lyrics = lyrics + numberOfBottles(bottles)+" of beer on the wall, "+numberOfBottles(bottles)+" of beer."
                +"\nTake"+intToWords(bottles)+" down and pass around," +
                " no more bottles of beer on the wall.\n" +
                "No more bottles of beer on the wall, no more bottles of beer.\n" +
                "Go to the store and buy some more, 99 bottles of beer on the wall.\n";
        //
        return lyrics;

    }
}
