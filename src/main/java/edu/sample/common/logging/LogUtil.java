package edu.sample.common.logging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LogUtil {
    
    public static BusinessLogMessage businessEvent(final String event) {
        return new BusinessLogMessage(event);        
    }
    
    /**
     * Helper function to serialize objects to JSON
     * Function also filters fields that are mapped with
     * Sensitive annotation.
     * @param o
     * @return
     */
    public static String toJSON(final Object o) {
        String value = null;
        ObjectMapper om = new ObjectMapper();
        
        try {
            value = om.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        
        return value;
    }
}
