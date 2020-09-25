package com.test.count.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.test.count.service.CountWords;
import com.test.count.service.CountWordsImpl;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CountController {

    @Autowired
    CountWords countWords;

    @RequestMapping("/")
    public String gethello() {
        return "Hello World Spring Boot Microservices Rest API";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Map> search(@RequestBody Object object) {

        Map<String, Integer> frequencyMap = null;
        try {
            frequencyMap = countWords.getSearch(object);
        }catch (IOException ioe) {
            ioe.getMessage();
        }
        return ResponseEntity.ok(frequencyMap);
    }

    @RequestMapping(value = "/top/{digit}", method = RequestMethod.GET)
    public ResponseEntity<Map> getTopNumbers(@PathVariable("digit") String strNumbers) {
        Map<String, Integer> frequencyMap = null;
        try {
            frequencyMap = countWords.getTopMostCount(strNumbers);
        } catch (IOException ioe) {
            ioe.getMessage();
        }
        System.out.print("Controller"+frequencyMap);
        return ResponseEntity.ok(frequencyMap);

//        OutputStreamWriter outputStream = new OutputStreamWriter(outputMessage.getBody());
//        StatefulBeanToCsv<T> beanToCsv =
//                new StatefulBeanToCsvBuilder(outputStream)
//                        .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
//                        .withMappingStrategy(strategy)
//                        .build();
//        try {
//            beanToCsv.write(l.getList());
//            outputStream.close();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

    }

}
