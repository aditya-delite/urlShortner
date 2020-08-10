package com.aditya.utilities;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ShortnerUtility {
    private Map<Character, Integer> charToIntMap = new HashMap<>();
    private List<Character> intToCharList = new ArrayList<>();

    public ShortnerUtility() {
        createList();
        createMap();
    }

    public void createMap() {
        for (int i = 0; i < 26; i++) {
            char a = 'a';
            a = (char) (a + i);
            charToIntMap.put(a, i);
        }
        for (int i = 26; i < 52; i++) {
            char a = (char) (65 + i - 26);
            charToIntMap.put(a, i);
        }
        for (int i = 52; i < 62; i++) {
            char a = '0';
            a = (char) (a + i - 52);
            charToIntMap.put(a, i);
        }
    }

    public void createList() {
        for (int i = 0; i < 26; i++) {
            intToCharList.add((char) (97 + i));
        }
        for (int i = 26; i < 52; i++) {
            intToCharList.add((char) (65 + i - 26));
        }
        for (int i = 52; i < 62; i++) {
            intToCharList.add((char) ('0' + i - 52));
        }
    }

    public String convertToBase62(int number) {
        StringBuilder builder = new StringBuilder();
        while (number > 0) {
            int reminder = number % 62;
            builder.append(reminder);
            number = number / 62;
        }
        return builder.toString();
    }

    public String getUniqueShortUrl(int number) {
        String s = convertToBase62(number);
        StringBuilder builder = new StringBuilder();
        for (int i = s.toCharArray().length - 1; i >= 0; i--) {
            builder.append(intToCharList.get(i + 1));
        }
        return builder.toString();
    }

//    public static void main(String[] args) {
//        int num = 125;
//        createList();
//        String n = convertToBase62(num);
//        StringBuilder builder = new StringBuilder();
//        for (int i = n.toCharArray().length - 1; i >= 0; i--) {
//            builder.append(intToCharList.get(i + 1));
//        }
//
//        System.out.println(builder.toString());
//    }
}
