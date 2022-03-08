package ekfkawl.dapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"ekfkawl.dapp.domain.service"})

public class WebApplication {
    public static void main(String[] args) {
            SpringApplication.run(WebApplication.class, args);
        }
}
