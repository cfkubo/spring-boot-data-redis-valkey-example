package dev.starichkov.java.spring.valkey;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 09.02.2025 18:58
 */
@Service
@Slf4j
public class SampleServiceWithCaching {

    @Cacheable("sample")
    public String getValue(String key) {
        log.info("Service: Providing value for key '{}'", key);
        return UUID.randomUUID() + "-" + key;
    }

    @CacheEvict("sample")
    public void deleteValue(String key) {
        log.info("Service: Deleting value for key '{}'", key);
    }
}
