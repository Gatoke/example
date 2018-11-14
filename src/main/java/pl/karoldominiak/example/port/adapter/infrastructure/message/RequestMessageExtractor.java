package pl.karoldominiak.example.port.adapter.infrastructure.message;

import org.springframework.http.HttpRequest;

import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

public class RequestMessageExtractor extends MessageExtractor {

    private static final String START_REQUEST = ">>>>>>>>>>>>>>>>>>>>>> Start Request >>>>>>>>>>>>>>>>>>>>>>";
    private static final String END_REQUEST = ">>>>>>>>>>>>>>>>>>>>>>  End Request  >>>>>>>>>>>>>>>>>>>>>>";

    public String extractMessage(final HttpRequest request, final byte[] body) {
        final String method = Objects.requireNonNull(request.getMethod()).toString();
        final String url = request.getURI().toString();
        final Map<String, String> requestHeaders = request.getHeaders().toSingleValueMap();
        return formatRequest(method, url, body, requestHeaders);
    }

    private String formatRequest(final String method,
                                 final String url,
                                 final byte[] body,
                                 final Map<String, String> headers) {
        return new StringJoiner(NEW_LINE, NEW_LINE, NEW_LINE).add(START_REQUEST)
                                                             .add("Method: " + method)
                                                             .add("URL: " + url + NEW_LINE)
                                                             .add("---HEADERS---" + NEW_LINE + formatHeaders(headers) + NEW_LINE)
                                                             .add("---BODY---" + NEW_LINE + new String(body))
                                                             .add(END_REQUEST)
                                                             .toString();
    }
}
