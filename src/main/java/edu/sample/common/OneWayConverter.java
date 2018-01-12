package edu.sample.common;

public interface OneWayConverter<From,To> {
    
    To convert(From fromType);

}
