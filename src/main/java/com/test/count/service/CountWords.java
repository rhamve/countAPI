package com.test.count.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public interface CountWords {

    Map<String,Integer> getSearch(Object object) throws IOException;

    Map<String,Integer> getTopMostCount(String strNumbers) throws IOException;

}
