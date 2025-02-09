package dev.starichkov.java.spring.valkey;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 09.02.2025 20:30
 */
@ActiveProfiles("test")
@SpringBootTest
@Testcontainers
@Slf4j
abstract class BaseApplicationTest {

    @Autowired
    private SampleServiceWithCaching sampleService;

    @Test
    void testCaching() {
        var key = getKey();

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

    protected abstract String getKey();
}
