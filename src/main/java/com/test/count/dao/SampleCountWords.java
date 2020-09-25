package com.test.count.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SampleCountWords {

    public static void main(String args[]) {


/*        String s = "5 a section of five words 3 three words\n"
                + "section 2 short section 7 this section contains a lot\n"
                + "of words";
        Reader reader = new StringReader(s);
//        Stream<List<String>> stream = records(StreamEx.ofLines(reader)
//                .flatMap(Pattern.compile("\\s+")::splitAsStream));
//        stream.forEach(System.out::println);*/

/*        JSONObject jsonRequestObject = new JSONObject();

        List<String> wordslist = new ArrayList<>();
        wordslist.add("Duis");
        wordslist.add("Sed");
        wordslist.add("Donec");
        wordslist.add("Augue");
        wordslist.add("Pellentesque");
        wordslist.add("test");

        JSONArray ja = new JSONArray(wordslist);

        try {
            jsonRequestObject.put("searchText"","ja);
        } catch (JSONException je) {

        }
        JSONObject jsonObject = new JSONObject();

        CountRepository countRepository = new CountRepository();
        List<String> listWords = null;
        try {
            listWords = Arrays.asList(new ObjectMapper().readValue(jsonObject.toString()","String[].class));
        } catch(JsonProcessingException jpe ) {
            jpe.printStackTrace();
        }

        System.out.println("listWords: "+ listWords);
        System.out.println(countRepository.readLinesFromFile(listWords));*/


        String filePath = "/var/tmp/file.txt";
        List<String> inputListWords = (ArrayList<String>) Arrays.stream( new String[] {"Duis", "Sed", "Donec", "Augue",
                "Pellentesque",
                "123" });

        List<String> listWords = null;
        Map<String, Integer> frequencyMap = null;
        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8)) {

            listWords = stream
                    .flatMap(line -> Arrays.stream(line.split(" ")))
                    .flatMap(line2 -> Arrays.stream(new String[] {line2.replace(".", "")}))
                    .flatMap(line3 -> Arrays.stream(new String[] {line3.replace(",", "")}))
                    .collect(Collectors.toList());
            System.out.println("listWords " + listWords);


            //public List<String> names = {"Sam", "James", "Selena", "James", "Joe", "Sam", "James"};
            frequencyMap = listWords.stream().parallel()
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(val -> 1)));

            System.out.println("test: " + frequencyMap);


//            Map<Integer, String> result =
//                    frequencyMap.keySet().stream().filter(k -> inputListWords.forEach((k)));


            Map<String, Integer> testMap = new HashMap<String, Integer>();
            for(int i=0; i<inputListWords.size();i++){
                if(!frequencyMap.containsKey(inputListWords.get(i))) {
                    testMap.put(inputListWords.get(i), 0);
                } else {
                    testMap.put(inputListWords.get(i), frequencyMap.get(inputListWords.get(i)));
                }
            }

            System.out.println("testMap : "+testMap);
//
//            Map<Integer, String> result = frequencyMap.entrySet()
//                    .stream()
//                    .filter(f -> f.getKey().equalsIgnoreCase(inputListWords.forEach(() -> words))) //filter by key
//                    .filter(map -> map.getValue().startsWith("A")) //filter by value
//                    .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));
//
//
//
//            Map<String, Integer> responseMap = frequencyMap.entrySet().stream().map(m -> m.getKey());
//            System.out.println("test: "+frequencyMap);
//
//            System.out.println("inputLiistWords :: "+inputLiistWords);
//
//            Map<String, Integer> responseMap = new HashMap<String, Integer>();
//            inputLiistWords.forEach(e -> {responseMap.put(e, 0);});
//
//            finalMap = new HashMap<String,Integer>(frequencyMap);
//
//            responseMap.forEach(
//                    (key, value) -> finalMap.merge(key, value, (v1, v2) -> ));
//
//            responseMap.forEach( (key, value) -> finalMap.merge(key, value) );
//
//            responseMap.forEach((k, v) -> finalMap.merge(k, v, String::concat));
//
//
//            finalMap.merge(
//
//                    Objects::nonNull
//
//            responseMap.forEach((kk,vv) -> (kk, frequencyMap.forEach());

//            responseMap.entrySet().stream().flatMap(k -> {
//                new HashMap<k,v>()
//            } );
//            System.out.println("re::"+responseMap);

/*            inputLiistWords.stream().forEach((Collectors.toMap(inputLiistWords, frequencyMap.get(inputLiistWords))));
            inputLiistWords.forEach(f -> {responseMap.put(f, {frequencyMap.get(f)});});

            Map<String,Integer> finalMap = areEqualKeyValues(responseMap,frequencyMap);
            return responseMap.entrySet().stream()
                    .collect(Collectors.toMap(e -> e.getKey(),
                            frequencyMap.get(e.getKey())));

            inputLiistWords.forEach(f -> {responseMap.put(f, frequencyMap.get());});

            System.out.println("responseMap : "+responseMap);*/

//            for(int i=0; i<inputLiistWords.size();i++){
//                responseMap.put(inputLiistWords.get(i), 0);
//                if(frequencyMap.containsKey(inputLiistWords.get(i).toLowerCase())) {
//                    System.out.println("listWords.get(i) : "+inputLiistWords.get(i));
////                    System.out.println("frequencyMap.get(inputLiistWords.get(i)) : "+frequencyMap.get(inputLiistWords.get(i).toLowerCase()));
//                    responseMap.put(inputLiistWords.get(i), frequencyMap.get(inputLiistWords.get(i).toLowerCase()));
//                }
//            }

        } catch (IOException ioe) {

        }
    }
}
