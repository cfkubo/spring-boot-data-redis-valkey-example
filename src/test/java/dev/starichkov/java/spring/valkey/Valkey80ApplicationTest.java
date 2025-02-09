package dev.starichkov.java.spring.valkey;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 09.02.2025 20:36
 */
class Valkey80ApplicationTest extends BaseApplicationTest {

    @Container
    private static final GenericContainer<?> VALKEY_8_0 =
            new GenericContainer<>(DockerImageName.parse("valkey/valkey:8.0.2-alpine3.21"))
                    .withExposedPorts(6379);

    @DynamicPropertySource
    private static void registerRedisProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.redis.host", VALKEY_8_0::getHost);
        registry.add("spring.data.redis.port", () -> VALKEY_8_0.getMappedPort(6379).toString());
    }

    @Override
    protected String getKey() {
        return "valkey80testKey";
    }
}
