package dev.starichkov.java.spring.valkey;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class RedisAndValkeySampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisAndValkeySampleApplication.class, args);
    }

    @Bean
    CommandLineRunner loadMovieDataset(SampleServiceWithCaching sampleServiceWithCaching) {
        return args -> {
            sampleServiceWithCaching.loadMovieDataset();
        };
    }
}
