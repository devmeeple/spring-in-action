package io.github.devmeeple.ch11.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration("projectConfigCh11")
public class ProjectConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }
}
