package dev.starichkov.java.spring.valkey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 09.02.2025 19:00
 */
@RestController
@RequestMapping("/sample")
public class SampleController {

    private final SampleServiceWithCaching sampleService;

    @Autowired
    public SampleController(SampleServiceWithCaching sampleService) {
        this.sampleService = sampleService;
    }

    @GetMapping("/{key}")
    public String getValue(@PathVariable("key") String key) {
        return sampleService.getValue(key);
    }

    @DeleteMapping("/{key}")
    public void deleteValue(@PathVariable("key") String key) {
        sampleService.deleteValue(key);
    }
}
