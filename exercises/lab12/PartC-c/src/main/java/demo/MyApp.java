package demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class MyApp implements CommandLineRunner {

    private final AppProperties appProperties;

    public MyApp(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public static void main(String[] args) {
        SpringApplication.run(MyApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("AppProperties: " + appProperties);
    }
}
