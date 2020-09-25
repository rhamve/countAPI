package com.test.count.service;

import com.test.count.dao.CountRepository;
import org.springframework.beans.Mergeable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

@Service
public class CountWordsImpl implements CountWords {

    @Autowired
    CountRepository countRepository;

    @Override
    public Map<String,Integer> getSearch(Object object) throws IOException {
        List<String> stringList = (List<String>) ((Map)object).get("searchText");
//        Map map = new HashMap();
//        if (object instanceof Map)
//            map = ((Map)object);
//        Object obj = map.get("searchText");
//        List<String> stringList = null;
//        if (obj instanceof ArrayList<?>)
//            stringList = (List<String>) obj;
        Map<String,Integer> initialMap = new HashMap<String,Integer>();
        stringList.forEach(e -> {initialMap.put(e, 0);});
        System.out.println("initialMap: "+initialMap);
        Map<String,Integer> frequencyMap = countRepository.readLinesFromFile();

        Map<String,Integer> mergedMap = new HashMap<String, Integer>();
        initialMap.keySet().forEach(key -> mergedMap.put(key, frequencyMap.get(key.toLowerCase())==null ? 0 : frequencyMap.get(key.toLowerCase())));

        System.out.println(mergedMap);
        return mergedMap;
    }

    @Override
    public Map<String,Integer> getTopMostCount(String ListNumbers) throws IOException {

        System.out.println("ListNumbers"+ListNumbers);

        Map<String,Integer> frequencyMap = countRepository.readLinesFromFile();

        LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<String, Integer>();

        frequencyMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

        System.out.println("Reverse Sorted Map   : " + reverseSortedMap);

        LinkedHashMap<String,Integer> finalMap = new LinkedHashMap<String, Integer>();

        reverseSortedMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(Integer.parseInt(ListNumbers))
                .forEachOrdered(x -> finalMap.put(x.getKey(), x.getValue()));

        System.out.println("finalMap : " +finalMap);
        return finalMap;

    }
}
