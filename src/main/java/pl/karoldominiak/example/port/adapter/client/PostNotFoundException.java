package pl.karoldominiak.example.port.adapter.client;

public class PostNotFoundException extends Throwable {

    PostNotFoundException(final String message) {
        super(message);
    }
}
