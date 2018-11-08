package pl.karoldominiak.example.port.adapter.infrastructure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import pl.karoldominiak.example.port.adapter.infrastructure.message.RequestMessageExtractor;
import pl.karoldominiak.example.port.adapter.infrastructure.message.ResponseMessageExtractor;

import java.io.IOException;

@Slf4j(topic = "pl.karoldominiak.example.communication")
public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {

    private final RequestMessageExtractor requestMessageExtractor = new RequestMessageExtractor();
    private final ResponseMessageExtractor responseMessageExtractor = new ResponseMessageExtractor();

    @Override
    public ClientHttpResponse intercept(final HttpRequest request,
                                        final byte[] body,
                                        final ClientHttpRequestExecution execution) throws IOException {
        final String requestLog = requestMessageExtractor.extractMessage(request, body);
        log.debug(requestLog);

        final ClientHttpResponse response = execution.execute(request, body);
        final String responseLog = responseMessageExtractor.extractMessage(request, response);
        log.debug(responseLog);

        return response;
    }
}
