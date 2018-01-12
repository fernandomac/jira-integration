package edu.sample.common.logging;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class BusinessLogMessage {

    private String event;
    private Map<String, Object> mapMessage = new HashMap<>();

    public BusinessLogMessage(String event) {
        super();
        this.event = event;
    }

    public BusinessLogMessage add(String key, Object value) {
        mapMessage.put(key, value);
        return this;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("BUSINESS - ").append(event).append(" [");

        sb.append(mapMessage.entrySet().stream().map(m -> m.getKey() + "=" + m.getValue())
                .collect(Collectors.joining(", ")));

        sb.append("]");

        return sb.toString();
    }
}
