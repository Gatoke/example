package pl.karoldominiak.example.port.adapter.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.support.TaskUtils;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SpringConfiguration {

    @Value("${restTemplate.readTimeout}")
    private int readTimeout;

    @Value("${restTemplate.connectTimeout}")
    private int connectTimeout;

    @Bean
    public RestTemplate restTemplate() {
        final SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        simpleClientHttpRequestFactory.setReadTimeout(readTimeout);
        simpleClientHttpRequestFactory.setConnectTimeout(connectTimeout);
        return new RestTemplate(simpleClientHttpRequestFactory);
    }

    @Bean(name = "applicationEventMulticaster")
    public ApplicationEventMulticaster applicationEventMulticaster() {
        final SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
        eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
        eventMulticaster.setErrorHandler(TaskUtils.LOG_AND_SUPPRESS_ERROR_HANDLER);
        return eventMulticaster;
    }
}
