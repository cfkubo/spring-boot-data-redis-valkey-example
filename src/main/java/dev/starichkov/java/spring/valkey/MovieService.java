package dev.starichkov.java.spring.valkey;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/**
 * Service class for handling movie data operations.
 */
@Service
@Slf4j
public class MovieService {

    private final CacheManager cacheManager;

    public MovieService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    /**
     * Retrieves all movie data from Valkey.
     * 
     * @return a map of all movie keys and their corresponding values
     */
    @Cacheable("movie")
    public Map<Object, Object> listAllMovies() {
        log.info("Service: Listing all movies");
        Map<Object, Object> allMovies = new HashMap<>();
        Object nativeCacheObj = cacheManager.getCache("movie") != null ? cacheManager.getCache("movie").getNativeCache() : null;
        if (nativeCacheObj instanceof ConcurrentMap) {
            ConcurrentMap<Object, Object> nativeCache = (ConcurrentMap<Object, Object>) nativeCacheObj;
            nativeCache.forEach(allMovies::put);
        } else {
            log.warn("Native cache is not an instance of ConcurrentMap");
        }
        return allMovies;
    }
}