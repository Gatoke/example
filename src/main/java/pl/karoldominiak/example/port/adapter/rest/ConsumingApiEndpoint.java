package pl.karoldominiak.example.port.adapter.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.karoldominiak.example.port.adapter.client.JsonPlaceholderClient;
import pl.karoldominiak.example.port.adapter.client.PostNotFoundException;
import pl.karoldominiak.example.port.adapter.client.model.Post;

import java.util.List;

@RestController
@AllArgsConstructor
@Api(description = "Methods consuming external API - in this case https://jsonplaceholder.typicode.com")
@RequestMapping(value = "/jsonplaceholder", produces = MediaType.APPLICATION_JSON_VALUE)
public class ConsumingApiEndpoint {

    private final JsonPlaceholderClient jsonPlaceholderClient;

    @GetMapping("/posts")
    @ApiOperation(value = "Returns fake posts from https://jsonplaceholder.typicode.com")
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok().body(jsonPlaceholderClient.getAllPosts());
    }

    @GetMapping("/posts/{id}")
    @ApiOperation(value = "Returns specific fake post from https://jsonplaceholder.typicode.com - use values from 1 to 100.")
    public ResponseEntity<Post> getOnePost(@PathVariable("id") final Long id) throws PostNotFoundException {
        return ResponseEntity.ok().body(jsonPlaceholderClient.getPost(id));
    }

    @ExceptionHandler
    public ResponseEntity handlePostNotFoundException(final PostNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }
}
