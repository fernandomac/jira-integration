package edu.sample.common.logging;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class MaskingPatternLayout extends PatternLayout {

	private static final String MASKING = "*************";
	private static final String END_TOKEN = "\"";

	private String patternsProperty;
	private Optional<Pattern> pattern;
	

	public String getPatternsProperty() {
	    return patternsProperty;
	}

	public void setPatternsProperty(String patternsProperty) {
	    this.patternsProperty = patternsProperty;
	    if (this.patternsProperty != null) {
	        this.pattern = Optional.of(Pattern.compile(patternsProperty, Pattern.MULTILINE));
	    } else {
	        this.pattern = Optional.empty();
	    }
	}

    @Override
    public String doLayout(ILoggingEvent event) {
    	  String message = super.doLayout(event);
          
    	  if (pattern.isPresent()) {
        	  StringBuilder sb = new StringBuilder(message);              
        	  Matcher matcher = pattern.get().matcher(message);
              while (matcher.find()) {
	              int group = 1;
	              while (group <= matcher.groupCount()) {
	                  if (matcher.group(group) != null) {
	                	  int start = matcher.end(group) + 1;
	                	  int end = message.indexOf(END_TOKEN, start);                	  
	                	  String value = message.substring(start, end);
	                	  int valueStartIdx = sb.indexOf(value);
	                	  sb = sb.replace(valueStartIdx, valueStartIdx + value.length(), MASKING);	                	  
	                  }
	                  group++;
	              }
	          }
              message = sb.toString();
          }
          return message;
    }

}