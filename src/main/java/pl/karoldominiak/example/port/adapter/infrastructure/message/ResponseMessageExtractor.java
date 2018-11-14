package pl.karoldominiak.example.port.adapter.infrastructure.message;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

public class ResponseMessageExtractor extends MessageExtractor {

    private static final String RESPONSE = "<<<<<<<<<<<<<<<<<<<<<< Start Response <<<<<<<<<<<<<<<<<<<<<<";
    private static final String END_RESPONSE = "<<<<<<<<<<<<<<<<<<<<<<  End Response  <<<<<<<<<<<<<<<<<<<<<<";

    public String extractMessage(final HttpRequest request, final ClientHttpResponse response) throws IOException {
        final String method = Objects.requireNonNull(request.getMethod()).toString();
        final String url = request.getURI().toString();

        final int statusCode = response.getRawStatusCode();
        final Map<String, String> responseHeaders = response.getHeaders().toSingleValueMap();
        final byte[] responseBody = response.getBody().readAllBytes();
        return formatResponse(method, url, statusCode, responseHeaders, responseBody);
    }

    private String formatResponse(final String method,
                                  final String url,
                                  final int statusCode,
                                  final Map<String, String> headers,
                                  final byte[] responseBody) {
        return new StringJoiner(NEW_LINE, NEW_LINE, NEW_LINE).add(RESPONSE)
                                                             .add("Method: " + method)
                                                             .add("URL: " + url)
                                                             .add("Status code: " + statusCode + NEW_LINE)
                                                             .add("---HEADERS---" + NEW_LINE + formatHeaders(headers) + NEW_LINE)
                                                             .add("---BODY---" + NEW_LINE + new String(responseBody))
                                                             .add(END_RESPONSE)
                                                             .toString();
    }
}
