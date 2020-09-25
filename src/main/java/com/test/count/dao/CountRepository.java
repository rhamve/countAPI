package com.test.count.dao;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//Read the file

@Repository
public class CountRepository {

    public Map<String, Integer> readLinesFromFile() throws IOException
    {
        String filePath = "//var//tmp//file.txt";
        //String filePath = "SamplePara.txt";

        Resource resource = new ClassPathResource(filePath);

        Map<String, Integer> frequencyMap =  new HashMap<String, Integer>();

        List<String> listWords = null;
        Stream<String> stream = Files.lines( Paths.get(((ClassPathResource) resource).getPath()), StandardCharsets.UTF_8);
        listWords = stream
                     .flatMap(line -> Arrays.stream(line.split(" ")))
                     .flatMap(line2 -> Arrays.stream(new String[] {line2.replace(".", "")}))
                     .flatMap(line3 -> Arrays.stream(new String[] {line3.replace(",", "")}))
                     .flatMap(line4 -> Arrays.stream(new String[] {line4.toLowerCase()}))
                     .collect(Collectors.toList());

        frequencyMap = listWords.stream().parallel()
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(val -> 1)));

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
            return frequencyMap;
    }
}
