package cn.fishland.diary.controller.v1;

import cn.fishland.diary.pojo.Article;
import cn.fishland.diary.service.ArticleService;
import cn.fishland.diary.vo.ArticleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * TODO
 *
 * @author LessAnn
 * @version 1.0
 */
@Controller
public class IndexController {

    @Autowired
    ArticleService articleService;

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        // 获得文章内容
        List<ArticleVo> articleVos = articleService.getArticleVos();

        // 获得每日更新

        // 获得热门文章

        model.addAttribute("articleVos", articleVos);
        return "index";
    }

    @GetMapping("/article/{id}")
    public String article(@PathVariable Long id, Model model) {
        Article article = articleService.getArticle(id);
        model.addAttribute("article", article);
        return "article";
    }
}
