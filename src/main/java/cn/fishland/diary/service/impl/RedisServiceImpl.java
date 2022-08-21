package cn.fishland.diary.service.impl;

import cn.fishland.diary.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author xiaoyu
 * @version 1.0
 */
@Slf4j
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public Boolean setList(String key, String... vals) {
        try {
            if (vals == null) {
                return false;
            }
            if (vals.length >= 1) {
                redisTemplate.opsForList().leftPushAll(key, vals);
                return true;
            }
        } catch (Exception e) {
            log.error(String.format("left push all error:[%s]", e.getMessage()));
        }
        return false;
    }
}
