package dev.starichkov.java.spring.valkey;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 09.02.2025 20:33
 */
class Valkey72ApplicationTest extends BaseApplicationTest {

    @Container
    private static final GenericContainer<?> VALKEY_7_2 =
            new GenericContainer<>(DockerImageName.parse("valkey/valkey:7.2.8-alpine3.21"))
                    .withExposedPorts(6379);

    @DynamicPropertySource
    private static void registerRedisProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.redis.host", VALKEY_7_2::getHost);
        registry.add("spring.data.redis.port", () -> VALKEY_7_2.getMappedPort(6379).toString());
    }

    @Override
    protected String getKey() {
        return "valkey72testKey";
    }
}
