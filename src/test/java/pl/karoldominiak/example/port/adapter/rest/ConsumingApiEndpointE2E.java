package pl.karoldominiak.example.port.adapter.rest;

import org.assertj.core.util.Files;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.util.ResourceUtils;
import pl.karoldominiak.example.IntegrationTest;

import java.io.File;
import java.nio.charset.Charset;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class ConsumingApiEndpointE2E extends IntegrationTest {

    @Test
    public void shouldReturnPosts() throws Exception {
        // given
        stubFor(get(urlEqualTo("/posts")).willReturn(aResponse().withHeader("Content-Type", "application/json")
                                                                .withBodyFile("getAllPostsResponse.json")));

        final File expectedFile = ResourceUtils.getFile("classpath:ApiResponse/getAllPostsResponse.json");
        final String expected = Files.contentOf(expectedFile, Charset.defaultCharset());

        // when
        final String result = performRequest("/jsonplaceholder/posts", HttpMethod.GET, null);

        // then
        assertThat(result).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void shouldReturnEmptyListWhenHttpErrorOccurred() throws Exception {
        // given
        stubFor(get(urlEqualTo("/posts")).willReturn(aResponse().withStatus(503)));

        final File expectedFile = ResourceUtils.getFile("classpath:ApiResponse/emptyList.json");
        final String expected = Files.contentOf(expectedFile, Charset.defaultCharset());

        // when
        final String result = performRequest("/jsonplaceholder/posts", HttpMethod.GET, null);

        // then
        assertThat(result).isEqualToIgnoringWhitespace(expected);
    }
}
