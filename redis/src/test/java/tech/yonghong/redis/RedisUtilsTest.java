package tech.yonghong.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yonghong
 * @date 2020/3/2
 **/
@Slf4j
class RedisUtilsTest extends RedisApplicationTests {

    @Autowired
    private RedisUtils redisUtils;

    @Test
    void valueTest() {
        redisUtils.writeValue("string:string", "string");
        log.info(redisUtils.loadValue("string:string"));
    }

    @Test
    void valueObjectTest() {
        redisUtils.writeValueObject("object:string", "string");
        log.info("{}", redisUtils.loadValue("object:string"));
        log.info("{}", redisUtils.loadValueObject("object:string"));
    }

    @Test
    void listTest() {
        List<String> list = new ArrayList<>();
        list.add("element1");
        list.add("element2");
        list.add("element3");
        redisUtils.writeList("string:list", list);
        log.info("{}", redisUtils.loadList("string:list"));
    }

    @Test
    void listTestObject() {

    }

    @Test
    void setTest() {

    }

    @Test
    void setTestObject() {

    }

    @Test
    void zsetTest() {

    }

    @Test
    void zsetTestObject() {

    }

    @Test
    void hashTest() {

    }

    @Test
    void hashTestObject() {

    }
}