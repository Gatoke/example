package pl.karoldominiak.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class ExampleApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }
}
