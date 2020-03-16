package tech.yonghong.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yonghong
 * @date 2020/3/14
 **/
@Slf4j
class RedisInfoUtilsTest extends RedisApplicationTests {

    @Autowired
    private RedisInfoUtils redisInfoUtils;

    @Test
    void getMemoryInfo() {
        log.info("{}", redisInfoUtils.getAllInfo());
        log.info("{}", redisInfoUtils.getMemoryInfo());
        log.info("{}", redisInfoUtils.getCpuInfo());
    }
}