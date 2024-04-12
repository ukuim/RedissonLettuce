package comapi.redissonlettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.sync.RedisCommands;
import org.redisson.Redisson;
import org.redisson.RedissonJsonBucket;
import org.redisson.api.RJsonBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class userconfig {
@Autowired
    RedisClient redisClient;
@Autowired
    RedissonClient redissonClient;
    @GetMapping("/test")
    public String test() {
        RedisCommands<String, String> redisCommands = redisClient.connect().sync();
        redisCommands.set("test", "test");
     redissonClient.getBucket("data").set("data");
        redisClient.shutdown();
        redissonClient.shutdown();
        return "test";
    }
}
