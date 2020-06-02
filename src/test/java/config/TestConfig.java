package config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {"ru.examples.jpa.repos"})
@ComponentScan(basePackages = {"ru.examples.service"})
@EntityScan(basePackages = {"ru.examples.jpa.entity"})
public class TestConfig {

}
