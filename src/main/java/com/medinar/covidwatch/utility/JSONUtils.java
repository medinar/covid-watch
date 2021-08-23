package com.medinar.covidwatch.utility;

import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author Rommel Medina
 */
public class JSONUtils {
    
    private JSONUtils () {
    }

    /**
     * Converts JSON String into List of Java objects
     *
     * @param <T> Type
     * @param json JSON String
     * @param list Generic type of list
     * @return List of Java objects derived from the JSON String
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public static <T> List<T> convertFromJsonToList(
            String json,
            TypeReference<List<T>> list
    ) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, list);
    }

    /**
     * Convert JSON String into Java object
     *
     * @param <T> Type
     * @param json JSON String
     * @param obj Type of object
     * @return Java object derived from the JSON String
     * @throws IOException
     */
    public static <T> T covertFromJsonToObject(
            String json,
            Class<T> obj
    ) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, obj);
    }

    /**
     * Converts an object into JSON
     *
     * @param obj Object to be converted
     * @return JSON derived from the given Java object
     * @throws JsonProcessingException
     */
    public static String covertFromObjectToJson(
            Object obj
    ) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

}
