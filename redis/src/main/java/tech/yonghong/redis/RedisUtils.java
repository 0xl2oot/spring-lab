package tech.yonghong.redis;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author yonghong
 * @date 2020/3/2
 **/
@Slf4j
@Component
@AllArgsConstructor
public class RedisUtils {

    private RedisTemplate<String, Object> redisTemplate;

    private StringRedisTemplate stringRedisTemplate;

    // ========================================String========================================

    public void writeValue(String key, String value) {
        BoundValueOperations<String, String> ops = stringRedisTemplate.boundValueOps(key);
        ops.set(value);
    }

    public void writeValue(String key, String value, long timeout, TimeUnit unit) {
        BoundValueOperations<String, String> ops = stringRedisTemplate.boundValueOps(key);
        ops.set(value, timeout, unit);
    }

    public String loadValue(String key) {
        BoundValueOperations<String, String> ops = stringRedisTemplate.boundValueOps(key);
        return ops.get();
    }

    // ========================================String========================================

    public void writeValueObject(String key, Object value) {
        BoundValueOperations<String, Object> ops = redisTemplate.boundValueOps(key);
        ops.set(value);
    }

    public void writeValueObject(String key, Object value, long timeout, TimeUnit unit) {
        BoundValueOperations<String, Object> ops = redisTemplate.boundValueOps(key);
        ops.set(value, timeout, unit);
    }

    public Object loadValueObject(String key) {
        BoundValueOperations<String, Object> ops = redisTemplate.boundValueOps(key);
        return ops.get();
    }

    // ========================================List========================================

    public void writeList(String key, List<String> list) {
        BoundListOperations<String, String> ops = stringRedisTemplate.boundListOps(key);
        ops.rightPushAll(list.toArray(new String[0]));
    }

    public List<String> loadList(String key) {
        BoundListOperations<String, String> ops = stringRedisTemplate.boundListOps(key);
        return ops.range(0, -1);
    }

    public void removeList(String key, long count, String value) {
        BoundListOperations<String, String> ops = stringRedisTemplate.boundListOps(key);
        ops.remove(count, value);
    }

    public void rightPushList(String key, String value) {
        BoundListOperations<String, String> ops = stringRedisTemplate.boundListOps(key);
        ops.rightPush(value);
    }

    public String leftPopList(String key) {
        BoundListOperations<String, String> ops = stringRedisTemplate.boundListOps(key);
        return ops.leftPop();
    }

    // ========================================List========================================

    public void writeListObject(String key, List<?> list) {
        BoundListOperations<String, Object> ops = redisTemplate.boundListOps(key);
        ops.rightPushAll(list.toArray(new Object[0]));
    }

    public List<?> loadListObject(String key) {
        BoundListOperations<String, Object> ops = redisTemplate.boundListOps(key);
        return ops.range(0, -1);
    }

    public void removeListObject(String key, long count, Object value) {
        BoundListOperations<String, Object> ops = redisTemplate.boundListOps(key);
        ops.remove(count, value);
    }

    public void rightPushListObject(String key, Object value) {
        BoundListOperations<String, Object> ops = redisTemplate.boundListOps(key);
        ops.rightPush(value);
    }

    public Object leftPopListObject(String key) {
        BoundListOperations<String, Object> ops = redisTemplate.boundListOps(key);
        return ops.leftPop();
    }

    // ========================================Set========================================

    public void writeSet(String key, Set<String> set) {
        BoundSetOperations<String, String> ops = stringRedisTemplate.boundSetOps(key);
        ops.add(set.toArray(new String[0]));
    }

    public void writeToSet(String key, String value) {
        BoundSetOperations<String, String> ops = stringRedisTemplate.boundSetOps(key);
        ops.add(value);
    }

    public void removeFromSet(String key, String value) {
        BoundSetOperations<String, String> ops = stringRedisTemplate.boundSetOps(key);
        ops.remove(value);
    }

    public Set<String> loadSet(String key) {
        BoundSetOperations<String, String> ops = stringRedisTemplate.boundSetOps(key);
        return ops.members();
    }

    public String randomMemberSet(String key) {
        BoundSetOperations<String, String> ops = stringRedisTemplate.boundSetOps(key);
        return ops.randomMember();
    }

    // ========================================Set========================================

    public void writeSetObject(String key, Set<Object> set) {
        BoundSetOperations<String, Object> ops = redisTemplate.boundSetOps(key);
        ops.add(set.toArray(new Object[0]));
    }

    public void writeToSetObject(String key, Object value) {
        BoundSetOperations<String, Object> ops = redisTemplate.boundSetOps(key);
        ops.add(value);
    }

    public void removeFromSetObject(String key, Object value) {
        BoundSetOperations<String, Object> ops = redisTemplate.boundSetOps(key);
        ops.remove(value);
    }

    public Set<Object> loadSetObject(String key) {
        BoundSetOperations<String, Object> ops = redisTemplate.boundSetOps(key);
        return ops.members();
    }

    public Object randomMemberSetObject(String key) {
        BoundSetOperations<String, Object> ops = redisTemplate.boundSetOps(key);
        return ops.randomMember();
    }

    // ========================================ZSet========================================

    public void writeToZSet(String key, String value, double score) {
        BoundZSetOperations<String, String> ops = stringRedisTemplate.boundZSetOps(key);
        ops.add(value, score);
    }

    public void removeFromZSet(String key, String value) {
        BoundZSetOperations<String, String> ops = stringRedisTemplate.boundZSetOps(key);
        ops.remove(value);
    }

    public void removeZSet(String key, long start, long end) {
        BoundZSetOperations<String, String> ops = stringRedisTemplate.boundZSetOps(key);
        ops.removeRange(start, end);
    }

    public void removeByScoreZSet(String key, double min, double max) {
        BoundZSetOperations<String, String> ops = stringRedisTemplate.boundZSetOps(key);
        ops.removeRangeByScore(min, max);
    }

    public void incrementScoreZSet(String key, String value, double score) {
        BoundZSetOperations<String, String> ops = stringRedisTemplate.boundZSetOps(key);
        ops.incrementScore(value, score);
    }

    public Set<String> loadZSet(String key) {
        BoundZSetOperations<String, String> ops = stringRedisTemplate.boundZSetOps(key);
        return ops.range(0, -1);
    }

    public Set<String> loadReverseZSet(String key) {
        BoundZSetOperations<String, String> ops = stringRedisTemplate.boundZSetOps(key);
        return ops.reverseRange(0, -1);
    }

    public Set<String> loadByScoreZSet(String key, double min, double max) {
        BoundZSetOperations<String, String> ops = stringRedisTemplate.boundZSetOps(key);
        return ops.rangeByScore(min, max);
    }

    public Set<String> loadReverseByScoreZSet(String key, double min, double max) {
        BoundZSetOperations<String, String> ops = stringRedisTemplate.boundZSetOps(key);
        return ops.reverseRangeByScore(min, max);
    }

    public Long size(String key) {
        BoundZSetOperations<String, String> ops = stringRedisTemplate.boundZSetOps(key);
        return ops.size();
    }

    public Long countByScoreZSet(String key, double min, double max) {
        BoundZSetOperations<String, String> ops = stringRedisTemplate.boundZSetOps(key);
        return ops.count(min, max);
    }

    // ========================================ZSet========================================

    public void writeToZSetObject(String key, Object value, double score) {
        BoundZSetOperations<String, Object> ops = redisTemplate.boundZSetOps(key);
        ops.add(value, score);
    }

    public void removeFromZSetObject(String key, Object value) {
        BoundZSetOperations<String, Object> ops = redisTemplate.boundZSetOps(key);
        ops.remove(value);
    }

    public void incrementScoreZSetObject(String key, Object value, double score) {
        BoundZSetOperations<String, Object> ops = redisTemplate.boundZSetOps(key);
        ops.incrementScore(value, score);
    }

    public Set<Object> loadZSetObject(String key) {
        BoundZSetOperations<String, Object> ops = redisTemplate.boundZSetOps(key);
        return ops.range(0, -1);
    }

    public Set<Object> loadReverseZSetObject(String key) {
        BoundZSetOperations<String, Object> ops = redisTemplate.boundZSetOps(key);
        return ops.reverseRange(0, -1);
    }

    public Set<Object> loadByScoreZSetObject(String key, double min, double max) {
        BoundZSetOperations<String, Object> ops = redisTemplate.boundZSetOps(key);
        return ops.rangeByScore(min, max);
    }

    public Set<Object> loadReverseByScoreZSetObject(String key, double min, double max) {
        BoundZSetOperations<String, Object> ops = redisTemplate.boundZSetOps(key);
        return ops.reverseRangeByScore(min, max);
    }

    // ========================================Hash========================================

    public void writeHash(String key, String hkey, String value) {
        BoundHashOperations<String, String, String> ops = stringRedisTemplate.boundHashOps(key);
        ops.put(hkey, value);
    }

    public String loadHash(String key, String hkey) {
        BoundHashOperations<String, String, String> ops = stringRedisTemplate.boundHashOps(key);
        return ops.get(hkey);
    }

    public Boolean hasKeyHash(String key, String hkey) {
        BoundHashOperations<String, String, String> ops = stringRedisTemplate.boundHashOps(key);
        return ops.hasKey(hkey);
    }

    public Set<String> keysHash(String key) {
        BoundHashOperations<String, String, String> ops = stringRedisTemplate.boundHashOps(key);
        return ops.keys();
    }

    public Map<String, String> entriesHash(String key) {
        BoundHashOperations<String, String, String> ops = stringRedisTemplate.boundHashOps(key);
        return ops.entries();
    }

    public void deleteHash(String key, String hkey) {
        BoundHashOperations<String, String, String> ops = stringRedisTemplate.boundHashOps(key);
        ops.delete(hkey);
    }

    public void deleteHash(String key, List<String> hkeys) {
        BoundHashOperations<String, String, String> ops = stringRedisTemplate.boundHashOps(key);
        ops.delete(hkeys.toArray(new Object[0]));
    }

    // ========================================Hash========================================

    public void writeHashObject(String key, String hkey, Object value) {
        BoundHashOperations<String, String, Object> ops = redisTemplate.boundHashOps(key);
        ops.put(hkey, value);
    }

    public Object loadHashObject(String key, String hkey) {
        BoundHashOperations<String, String, Object> ops = redisTemplate.boundHashOps(key);
        return ops.get(hkey);
    }

    public Map<String, Object> entriesHashObject(String key) {
        BoundHashOperations<String, String, Object> ops = redisTemplate.boundHashOps(key);
        return ops.entries();
    }
}
