package com.test.count.service;


import com.test.count.dao.CountRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.when;


public class CountWordsImplTest {

    @Autowired
    private CountRepository countRepository = new CountRepository();

    @Mock
    CountRepository countRepository1 = Mockito.mock(CountRepository.class);

    @Autowired
    private CountWords countWords = new CountWordsImpl();

    private static JSONObject jsonObject;

    private static Map map = new HashMap();

    @BeforeClass
    public static void runBeforeAllTestMethods() throws JSONException {

        jsonObject = new JSONObject();

        List<String> listWords = new ArrayList<>();
        listWords.add("Duis");
        listWords.add("Sed");
        listWords.add("Donec");
        listWords.add("Augue");
        listWords.add("Pellentesque");
        listWords.add("test");

        JSONArray ja = new JSONArray(listWords);
        jsonObject.put("searchText", ja);

        map.put("searchText",listWords);

    }
    @Test
    public void testGetSearch() throws IOException {
        System.out.println(jsonObject);
        System.out.println(map);

        Map<String,Integer> frequecyMap = new HashMap<String,Integer>();
        frequecyMap.put("test", 20);
//        when(countRepository.readLinesFromFile()).thenReturn(frequecyMap);
//        when(countRepository1.readLinesFromFile()).thenReturn(frequecyMap);
//        final Map<String, Integer> searchMap = countWords.getSearch((Object) map);
//        System.out.println("SearchMap:"+searchMap);
    }

    @Test
    public void testTop5() throws IOException {
        Map<String,Integer> frequecyMap = new HashMap<String,Integer>();
        frequecyMap.put("test", 20);
//        when(countRepository.readLinesFromFile()).thenReturn(frequecyMap);
//        when(countRepository1.readLinesFromFile()).thenReturn(frequecyMap);
//        final Map<String, Integer> searchMap = countWords.getTopMostCount("5");
//        System.out.println("SearchMap:"+searchMap);
    }
}


