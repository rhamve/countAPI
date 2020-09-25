package com.test.count.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Integration Test
 */
public class CountControllerTest extends CountControllerAbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    private static String searchURL;
    private static String topListURL;

    private static RestTemplate restTemplate;
    private static HttpHeaders headers;

    private static JSONObject jsonRequestObject;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeClass
    public static void runBeforeAllTestMethods() throws JSONException {
        searchURL = "http://localhost:8181/search";
        topListURL = "http://localhost:8181/top/5";

        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        jsonRequestObject = new JSONObject();

        List<String> listWords = new ArrayList<>();
        listWords.add("Duis");
        listWords.add("Sed");
        listWords.add("Donec");
        listWords.add("Augue");
        listWords.add("Pellentesque");
        listWords.add("test");

        JSONArray ja = new JSONArray(listWords);
        jsonRequestObject.put("searchText", ja);

    }

    @Test
    public void getHello() throws Exception {
        String uri = "/";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }


    @Test
    public void getSearch() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(searchURL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_VALUE).content(jsonRequestObject.toString())).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String strResponse = mvcResult.getResponse().getContentAsString();
        HashMap<String,Integer> resultMap = super.mapFromJson(strResponse, HashMap.class);
        assertEquals(200, status);
        assertNotNull(resultMap);
        assertEquals(resultMap.get("Sed"), 16 );
        assertEquals(resultMap.get("Donec"), 8 );
        assertEquals(resultMap.get("test"), 0 );
        assertEquals(resultMap.get("Augue"), 7 );
        assertEquals(resultMap.get("Pellentesque"), 6 );
        assertEquals(resultMap.get("Duis"), 11 );
    }

    @Test
    public void getTop5() throws Exception {

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(topListURL)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String strResponse = mvcResult.getResponse().getContentAsString();
        LinkedHashMap<String,Integer> resultMap = super.mapFromJson(strResponse, LinkedHashMap.class);
        assertNotNull(resultMap);
        assertEquals(resultMap.get("eget"), 17 );
        assertEquals(resultMap.get("vel"), 17 );
        assertEquals(resultMap.get("sed"), 16 );
        assertEquals(resultMap.get("in"), 15 );
        assertEquals(resultMap.get("et"), 14 );
        assertEquals(200, status);
    }
}
