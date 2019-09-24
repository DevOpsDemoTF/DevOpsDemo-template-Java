package service.app.util;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.contrib.json.classic.JsonLayout;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

public class LogJsonLayout extends JsonLayout {
    private final ObjectMapper mapper = new ObjectMapper();

    public LogJsonLayout() {
        this.appendLineSeparator = true;
        this.includeContextName = false;
        this.includeThreadName = false;
        this.jsonFormatter = mapper::writeValueAsString;
    }

    @Override
    public Map toJsonMap(ILoggingEvent event) {
        var map = new LinkedHashMap<String, Object>();

        var formattedTimestamp = ZonedDateTime.ofInstant(
                Instant.ofEpochMilli(event.getTimeStamp()),
                ZoneId.systemDefault()
        ).format(DateTimeFormatter.ISO_INSTANT);

        add("time", this.includeTimestamp, formattedTimestamp, map);
        add("level", this.includeLevel, event.getLevel().levelStr, map);
        add("thread", this.includeThreadName, event.getThreadName(), map);
        addMap("properties", this.includeMDC, event.getMDCPropertyMap(), map);
        add("logger", this.includeLoggerName, event.getLoggerName(), map);
        add("msg", this.includeFormattedMessage, event.getFormattedMessage(), map);
        add("msg", this.includeMessage, event.getMessage(), map);
        add("context", this.includeContextName, event.getLoggerContextVO().getName(), map);
        addThrowableInfo("exception", this.includeException, event, map);
        addCustomDataToJsonMap(map, event);
        return map;
    }
}
