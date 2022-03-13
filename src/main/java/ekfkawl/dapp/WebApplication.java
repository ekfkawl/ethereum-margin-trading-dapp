package ekfkawl.dapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Configuration
@EnableScheduling
public class WebApplication {
    public static void main(String[] args) {
            SpringApplication.run(WebApplication.class, args);
        }
}
