package dev.starichkov.java.spring.valkey;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
@Testcontainers
@Slf4j
class Redis72aIT {

    @Container
    private static final GenericContainer<?> REDIS_7_2 =
            new GenericContainer<>(DockerImageName.parse("redis:7.2.7-alpine3.21"))
                    .withExposedPorts(6379);

    @DynamicPropertySource
    private static void registerRedisProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.redis.host", REDIS_7_2::getHost);
        registry.add("spring.data.redis.port", () -> REDIS_7_2.getMappedPort(6379).toString());
    }

    @Autowired
    private SampleServiceWithCaching sampleService;

    @Test
    void testCaching() {
        var key = "redis72testKey";

        var value1 = getValue(key);
        var value2 = getValue(key);
        assertEquals(value1, value2);

        deleteValue(key);

        var value3 = getValue(key);
        assertNotEquals(value1, value3);
    }

    private String getValue(String key) {
        log.info("Test: Requesting value for key '{}'", key);
        return sampleService.getValue(key);
    }

    private void deleteValue(String key) {
        log.info("Test: Deleting value for key '{}'", key);
        sampleService.deleteValue(key);
    }
}
