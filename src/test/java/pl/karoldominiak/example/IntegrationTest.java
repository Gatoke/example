package pl.karoldominiak.example;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = ExampleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class IntegrationTest {

    private static final int WIRE_MOCK_PORT = 11111;
    private static final HttpEntity HTTP_ENTITY = new HttpEntity(new LinkedMultiValueMap<>() {{
        add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    }});

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int applicationPort;

    private WireMockServer wireMockServer;

    public IntegrationTest() {
        wireMockServer = new WireMockServer(WIRE_MOCK_PORT);
        WireMock.configureFor("localhost", WIRE_MOCK_PORT);
    }

    @Before
    public void startWireMockServer() {
        wireMockServer.start();
    }

    @After
    public void stopWireMockServer() {
        wireMockServer.stop();
    }

    protected String performRequest(final String endpoint, final HttpMethod method, final Object body) {
        return testRestTemplate.exchange(getBaseUrl() + endpoint, method, HTTP_ENTITY, String.class, body)
                               .getBody();
    }

    private String getBaseUrl() {
        return "http://localhost:" + applicationPort;
    }

}
