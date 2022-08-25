package cn.fishland.diary.task;

import cn.fishland.diary.pojo.ApiBrowse;
import cn.fishland.diary.service.ApiBrowseService;
import cn.fishland.diary.util.DiaryUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

/**
 * TODO
 *
 * @author xiaoyu
 * @version 1.0
 */
@Slf4j
@Component
public class CountTask {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    ApiBrowseService apiBrowseService;

    @Scheduled(cron = "0 0 0/1 * * ?")
    private void countApiBrowse() {
        log.info("count api browse start >>>>>>");
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        Map<Object, Object> map = hash.entries(DiaryUtil.REDIS_API_BROWSE_KEY);
        if (map == null || map.keySet().size() == 0) {
            log.info("count api browse end >>>>>>");
            return;
        }

        String ymd = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        // map 2 apiBrowse list
        ArrayList<ApiBrowse> list = new ArrayList<>();
        for (Object o : map.keySet()) {
            list.add(new ApiBrowse(ymd, (String) o, Long.valueOf((String) map.get(o))));
        }
        Boolean saveApiBrowse = apiBrowseService.saveApiBrowse(list);
        log.info("count api browse end, exec result=[" + saveApiBrowse + "] >>>>>>");
    }

}
