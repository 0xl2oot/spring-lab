package tech.yonghong.redis;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author yonghong
 * @date 2020/3/14
 **/
@Slf4j
@Component
@AllArgsConstructor
public class RedisInfoUtils {

    private RedisConnectionFactory redisConnectionFactory;

    public Properties getAllInfo() {
        return redisConnectionFactory.getConnection().info();
    }

    public Properties getMemoryInfo() {
        return redisConnectionFactory.getConnection().info("memory");
    }

    public Properties getCpuInfo() {
        return redisConnectionFactory.getConnection().info("cpu");
    }
}
