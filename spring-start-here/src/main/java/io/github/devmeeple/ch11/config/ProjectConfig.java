package io.github.devmeeple.ch11.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(basePackages = "io.github.devmeeple.ch11.proxy")
@Configuration("projectConfigCh11")
public class ProjectConfig {
}
