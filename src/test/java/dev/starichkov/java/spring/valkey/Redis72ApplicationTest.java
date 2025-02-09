package dev.starichkov.java.spring.valkey;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 09.02.2025 18:57
 */
class Redis72ApplicationTest extends BaseApplicationTest {

    @Container
    private static final GenericContainer<?> REDIS_7_2 =
            new GenericContainer<>(DockerImageName.parse("redis:7.2.7-alpine3.21"))
                    .withExposedPorts(6379);

    @DynamicPropertySource
    private static void registerRedisProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.redis.host", REDIS_7_2::getHost);
        registry.add("spring.data.redis.port", () -> REDIS_7_2.getMappedPort(6379).toString());
    }

    @Override
    protected String getKey() {
        return "redis72testKey";
    }
}
