package dev.starichkov.java.spring.valkey;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Service class with caching functionality.
 * Uses Spring's caching abstraction to cache and evict values.
 * 
 * @since 09.02.2025 18:58
 */
@Service
@Slf4j
public class SampleServiceWithCaching {

    private final CacheManager cacheManager;

    public SampleServiceWithCaching(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    /**
     * Retrieves the cached value for the given key.
     * If the value is not present in the cache, it generates a new value,
     * caches it, and then returns it.
     * 
     * @param key the key for which the cached value is to be retrieved
     * @return the cached value
     */
    @Cacheable("sample")
    public String getValue(String key) {
        log.info("Service: Providing value for key '{}'", key);
        // Generate a new value if not present in the cache
        return UUID.randomUUID() + "-" + key;
    }

    /**
     * Deletes the cached value for the given key.
     * 
     * @param key the key for which the cached value is to be deleted
     */
    @CacheEvict("sample")
    public void deleteValue(String key) {
        log.info("Service: Deleting value for key '{}'", key);
    }

    /**
     * Retrieves all cached values.
     * 
     * @return a map of all keys and their corresponding cached values
     */
    public Map<Object, Object> listAllValues() {
        log.info("Service: Listing all cached values");
        Map<Object, Object> allValues = new HashMap<>();
        Object nativeCacheObj = cacheManager.getCache("sample").getNativeCache();
        if (nativeCacheObj instanceof ConcurrentMap) {
            ConcurrentMap<Object, Object> nativeCache = (ConcurrentMap<Object, Object>) nativeCacheObj;
            nativeCache.forEach(allValues::put);
        } else {
            log.warn("Native cache is not an instance of ConcurrentMap");
        }
        return allValues;
    }

    /**
     * Loads the movie dataset into Valkey if not already present.
     */
    public void loadMovieDataset() {
        log.info("Service: Loading movie dataset into Valkey if not already present");
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/datasets/movie-database/import_movies.redis"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String key = parts[0];
                    String value = parts[1];
                    if (cacheManager.getCache("movie").get(key) == null) {
                        cacheManager.getCache("movie").put(key, value);
                    }
                }
            }
        } catch (IOException e) {
            log.error("Error loading movie dataset", e);
        }
    }
}
