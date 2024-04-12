package comapi.redissonlettuce.Config;

import io.lettuce.core.RedisClient;
import jakarta.annotation.Resource;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RedissonConfig {
    @Resource
    private RedisConnectionFactory factory;
    @Value("${spring.data.redis.cluster.nodes}")
    private String redisNodes;

    @Bean
    public RedissonClient redisson() {
        Config config = new Config();
        //单点
        config.useSingleServer()
                .setAddress("redis://root@127.0.0.1:6379")
                .setPassword("root");
        //集群
      /*  config.useClusterServers()
                .addNodeAddress("redis://127.0.0.1:6379")
                .addNodeAddress("redis://192.168.80.129:6379")
                .setPassword("root");*/
        return Redisson.create(config);
    }

    @Bean
    public RedisClient redisClient() {
        return RedisClient.create("redis://root@127.0.0.1:6379");
    }


    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(this.factory);
        return redisTemplate;
    }
}
