package dev.starichkov.java.spring.valkey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * REST controller for handling requests related to sample caching.
 * Provides endpoints to get, delete, and list cached values.
 * 
 * @since 09.02.2025 19:00
 */
@RestController
@RequestMapping("/sample")
public class SampleController {

    private final SampleServiceWithCaching sampleService;

    /**
     * Constructor for SampleController.
     * 
     * @param sampleService the service class with caching functionality
     */
    @Autowired
    public SampleController(SampleServiceWithCaching sampleService) {
        this.sampleService = sampleService;
    }

    /**
     * Endpoint to get the cached value for a given key.
     * 
     * @param key the key for which the cached value is to be retrieved
     * @return the cached value
     */
    @GetMapping("/{key}")
    public String getValue(@PathVariable("key") String key) {
        return sampleService.getValue(key);
    }

    /**
     * Endpoint to delete the cached value for a given key.
     * 
     * @param key the key for which the cached value is to be deleted
     */
    @DeleteMapping("/{key}")
    public void deleteValue(@PathVariable("key") String key) {
        sampleService.deleteValue(key);
    }

    /**
     * Endpoint to list all cached values.
     * 
     * @return a map of all keys and their corresponding cached values
     */
    @GetMapping("/list")
    public Map<Object, Object> listAllValues() {
        return sampleService.listAllValues();
    }
}
