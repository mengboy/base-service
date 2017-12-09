package com.study.util;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonMapper {
    private static ObjectMapper objectMapper = null;

    public static com.fasterxml.jackson.databind.ObjectMapper getObjectMapper(){
        if(objectMapper==null){
            objectMapper = new ObjectMapper(); // create once, reuse
            objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }
        return objectMapper;
    }
}
