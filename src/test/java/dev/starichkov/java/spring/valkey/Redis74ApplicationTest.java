package dev.starichkov.java.spring.valkey;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 09.02.2025 20:31
 */
class Redis74ApplicationTest extends BaseApplicationTest {

    @Container
    private static final GenericContainer<?> REDIS_7_4 =
            new GenericContainer<>(DockerImageName.parse("redis:7.4.2-alpine3.21"))
                    .withExposedPorts(6379);

    @DynamicPropertySource
    private static void registerRedisProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.redis.host", REDIS_7_4::getHost);
        registry.add("spring.data.redis.port", () -> REDIS_7_4.getMappedPort(6379).toString());
    }

    @Override
    protected String getKey() {
        return "redis74testKey";
    }
}
