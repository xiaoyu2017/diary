package cn.fishland.diary.service.impl;

import cn.fishland.diary.dao.ArticleDao;
import cn.fishland.diary.pojo.Article;
import cn.fishland.diary.service.ArticleService;
import cn.fishland.diary.vo.ArticleVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public Boolean addArticle(Article article) {
        try {
            articleDao.insert(article);
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
    public List<ArticleVo> getArticleVos() {
        try {
            return articleDao.selectVoAll();
        } catch (Exception e) {
            log.error(String.format("get article vo error:[%s]", e.getMessage()));
            return null;
        }
    }
}
