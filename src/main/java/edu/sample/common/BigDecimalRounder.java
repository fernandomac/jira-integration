package edu.sample.common;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class BigDecimalRounder {
    
    public static BigDecimal round(BigDecimal value){
        BigDecimal roundedValue = BigDecimal.ZERO;
        if(value != null){
            roundedValue = value.setScale(2, RoundingMode.HALF_UP);
        }
        return roundedValue;
    }
}
