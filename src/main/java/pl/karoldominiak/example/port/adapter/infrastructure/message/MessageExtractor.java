package pl.karoldominiak.example.port.adapter.infrastructure.message;

import java.util.Map;
import java.util.stream.Collectors;

abstract class MessageExtractor {

    static final String NEW_LINE = System.lineSeparator();

    String formatHeaders(final Map<String, String> headers) {
        return headers.entrySet()
                      .stream()
                      .map(header -> header.getKey() + ": " + header.getValue())
                      .collect(Collectors.joining(System.lineSeparator()));
    }
}
