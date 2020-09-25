//package com.test.count.model;
//
//import com.sun.istack.internal.NotNull;
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.function.Function;
//import java.util.regex.Pattern;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//
//public class Count {
//
//    private static String text;
//
//    private static void replaceTextWithEmptyString(String whatToReplace) {
//        text = text.replaceAll(whatToReplace, "");
//    }
//
//
//    public static void main(String args[]) throws IOException {
//        String filePath = "/var/tmp/file.txt";
//        Stream<String> fileStreamRead = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8);
//        List<String> listWords;
//        listWords = fileStreamRead.flatMap(line -> Arrays.stream(line.split(" "))).collect(Collectors.toList());
//
//
//
//
////
////        List<String> toRemove = Arrays.asList("1", "2");
////        String text = "Hello 1 2 3";
////        String delimiter = " ";
////
//////        text = Pattern.compile(delimiter).splitAsStream(text)
//////                .filter(s -> !toRemove.contains(s))
//////                .collect(Collectors.joining(delimiter));
////
////
////        toRemove.forEach(removeString -> replaceTextWithEmptyString(removeString));
////
////
////
////        System.out.println("\"" + text + "\"");
//
//        listWords = new ArrayList<String>(); // {"Sam", "James", "Selena", "James", "Joe", "Sam", "James"};
//        listWords.add("Sam");
//        listWords.add("James,");
//        listWords.add("Selena");
//        listWords.add("James");
//        listWords.add("Joe");
//        listWords.add("Sam.");
//        listWords.add("James");
//
//        //listWords =  {"Sam", "James", "Selena", "James", "Joe", "Sam", "James"};
////        listWords.stream().flatMap(line -> Arrays.stream(line.replace(".",""))).collect(Collectors.toList());
//        System.out.println("listWords : "+listWords);
//
//        List<String> test = listWords.stream()
//                //filter(s -> s.endsWith("."))
//                .flatMap(line2 -> Arrays.stream(new String[] {line2.replace(".", "")}))
//                .flatMap(line3 -> Arrays.stream(new String[] {line3.replace(",", "")}))
//                .collect(Collectors.toList());
//        System.out.println("test : "+test);
////        Map<String, Integer> frequencyMap = null;
////        frequencyMap = listWords.stream()
////                .map(String::replace(".",""))
////                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(val -> 1)));
//
////        System.out.println("test: "+frequencyMap);
//
//    }
//}
