package cn.fishland.diary.service;

import cn.fishland.diary.pojo.Article;
import cn.fishland.diary.vo.ArticleVo;

import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author xiaoyu
 * @version 1.0
 */
public interface ArticleService {

    /**
     * 新增文章
     *
     * @param article 文章内容对象
     * @return 是否成功
     */
    Boolean addArticle(Article article);

    /**
     * 修改文章内容
     *
     * @param article 文章内容
     * @return 是否成功
     */
    Boolean updateArticle(Article article);

    /**
     * 获得指定页文章内容
     *
     * @param page 页数
     * @return 当前页文章列表
     */
    List<Article> getArticles(Integer page);

    /**
     * 根据用户id获得文章内容
     *
     * @param id 文章唯一表示
     * @return 文章内容对象
     */
    Article getArticle(Long id);

    /**
     * 删除文章
     *
     * @param id 文章id
     * @return 删除是否成功
     */
    Boolean delete(Long id);

    /**
     * 获得文章vo对象
     *
     * @return 文章vo对象列表
     */
    List<ArticleVo> getArticleVos(int page);

    /**
     * 获得文章页数
     *
     * @return 页数
     */
    int getPageNumber();

    /**
     * 获得每日更新和每日热门内容
     *
     * @return 每日更新内容集合
     */
    Map<String, List<ArticleVo>> dailyData();
}
