package cn.fishland.diary.service.impl;

import cn.fishland.diary.dao.ArticleDao;
import cn.fishland.diary.pojo.Article;
import cn.fishland.diary.service.ArticleService;
import cn.fishland.diary.util.DiaryUtil;
import cn.fishland.diary.vo.ArticleVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author xiaoyu
 * @version 1.0
 */
@Slf4j
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleDao articleDao;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public Boolean addArticle(Article article) {
        try {
            articleDao.insert(article);
            initDailyData();
            return true;
        } catch (Exception e) {
            log.error(String.format("add article error:[{%s}]", e.getMessage()));
            return false;
        }
    }

    @Override
    public Boolean updateArticle(Article article) {
        try {
            articleDao.update(article);
            initDailyData();
            return true;
        } catch (Exception e) {
            log.error(String.format("update article error:[%s]", e.getMessage()));
            return false;
        }
    }

    @Override
    public List<Article> getArticles(Integer page) {
        return articleDao.selectAll();
    }

    @Override
    public Article getArticle(Long id) {
        return articleDao.findById(id);
    }

    @Override
    public Boolean delete(Long id) {
        try {
            articleDao.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error(String.format("delete article error=[%s]", e.getMessage()));
            return false;
        }
    }

    @Override
    public List<ArticleVo> getArticleVos(int page) {
        try {
            return articleDao.selectVoPages((page - 1) * DiaryUtil.APP_INDEX_PAGE_NUMBER, DiaryUtil.APP_INDEX_PAGE_NUMBER);
        } catch (Exception e) {
            log.error(String.format("get article vo error:[%s]", e.getMessage()));
            return null;
        }
    }

    @Override
    public int getPageNumber() {
        try {
            int count = articleDao.count();
            return (count + 15) / 16;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Map<String, List<ArticleVo>> dailyData() {
        ListOperations<String, String> redisList = redisTemplate.opsForList();
        Long dailyUpdateSize = redisList.size(DiaryUtil.REDIS_DAILY_UPDATE);
        if (dailyUpdateSize <= 0) {
            List<ArticleVo> articleVos = articleDao.selectVoPages(0, 10);
            List<String> strings = articleVos.stream().map(ArticleVo::toJson).collect(Collectors.toList());

            // 设置每日更新
            redisList.rightPushAll(DiaryUtil.REDIS_DAILY_UPDATE, strings);
            // 设置每日热门 TODO 这样设置是不正确的，等待访问统计实现再进行完善
            redisList.rightPushAll(DiaryUtil.REDIS_DAILY_HOT, strings);
            HashMap<String, List<ArticleVo>> map = new HashMap<>(2);
            map.put("dailyUpdate", articleVos);
            map.put("dailyHot", articleVos);
            return map;
        }

        List<String> dailyUpdates = redisList.range(DiaryUtil.REDIS_DAILY_UPDATE, 0, dailyUpdateSize);
        ObjectMapper objectMapper = new ObjectMapper();
        List<ArticleVo> articleVos = dailyUpdates.stream().map(e -> {
            try {
                return objectMapper.readValue(e, ArticleVo.class);
            } catch (JsonProcessingException ex) {
                ex.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());

        HashMap<String, List<ArticleVo>> map = new HashMap<>(2);
        map.put("dailyUpdate", articleVos);
        map.put("dailyHot", articleVos);
        return map;
    }

    public void initDailyData() {
        ListOperations<String, String> redisList = redisTemplate.opsForList();
        Long dailyUpdateSize = redisList.size(DiaryUtil.REDIS_DAILY_UPDATE);
        Long dailyHotSize = redisList.size(DiaryUtil.REDIS_DAILY_HOT);

        if (dailyUpdateSize > 0) {
            redisTemplate.delete(DiaryUtil.REDIS_DAILY_UPDATE);
        }
        if (dailyHotSize > 0) {
            redisTemplate.delete(DiaryUtil.REDIS_DAILY_HOT);
        }

        List<ArticleVo> articleVos = articleDao.selectVoPages(0, 10);
        List<String> strings = articleVos.stream().map(ArticleVo::toJson).collect(Collectors.toList());

        // 设置每日更新
        redisList.rightPushAll(DiaryUtil.REDIS_DAILY_UPDATE, strings);
        // 设置每日热门 TODO 这样设置是不正确的，等待访问统计实现再进行完善
        redisList.rightPushAll(DiaryUtil.REDIS_DAILY_HOT, strings);
    }
}
