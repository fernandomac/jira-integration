package edu.sample.common;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

public final class ToStringHelper {
    
    public static String defaultToString(Object object){
        return reflectionToString(object, JSON_STYLE);
    }

}
