package pl.karoldominiak.example.port.adapter.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import pl.karoldominiak.example.port.adapter.client.model.Post;

import java.util.Collections;
import java.util.List;

@Service
public class JsonPlaceholderClient {

    private final RestTemplate restTemplate;

    @Value("${jsonplaceholder.posts}")
    private String postsUrl;

    public JsonPlaceholderClient(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Post> getAllPosts() {
        final ParameterizedTypeReference<List<Post>> typeReference = new ParameterizedTypeReference<>() {
        };
        try {
            return restTemplate.exchange(postsUrl, HttpMethod.GET, HttpEntity.EMPTY, typeReference).getBody();
        } catch (final RestClientException exception) {
            return Collections.emptyList();
        }
    }

    public Post getPost(final Long id) throws PostNotFoundException {
        final UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(postsUrl)
                                                                .pathSegment(id.toString())
                                                                .build();
        try {
            return restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET, HttpEntity.EMPTY, Post.class).getBody();
        } catch (final RestClientException exception) {
            throw new PostNotFoundException(exception.getMessage());
        }
    }
}