package dev.starichkov.java.spring.valkey;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 09.02.2025 18:58
 */
@Service
public class ServiceWithCaching {

    @Cacheable("sample")
    public String getValue(String key) {
        return "value for key " + key;
    }
}
