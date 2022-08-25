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
import java.util.Map;

/**
 * TODO
 *
 * @author xiaoyu
 * @version 1.0
 */
@Controller
public class IndexController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        // 获得文章内容
        List<ArticleVo> articleVos = articleService.getArticleVos(1);

        // 页数
        int pageNumber = articleService.getPageNumber();

        // 获得每日更新

        // 获得热门文章

        model.addAttribute("articleVos", articleVos);
        model.addAttribute("pageStart", 1);
        model.addAttribute("pageIndex", 1);
        if (pageNumber > 5) {
            model.addAttribute("pageEnd", 5);
        } else {
            model.addAttribute("pageEnd", pageNumber);
        }
        model.addAttribute("pageNumber", pageNumber);

        return "index";
    }

    @GetMapping("/page/{page}")
    public String page(@PathVariable Integer page, Model model) {
        // 获得文章内容
        List<ArticleVo> articleVos = articleService.getArticleVos(page);

        // 页数
        int pageNumber = articleService.getPageNumber();

        // 获得每日更新

        // 获得热门文章

        model.addAttribute("articleVos", articleVos);
        model.addAttribute("pageIndex", page);
        if (pageNumber > 5) {
            model.addAttribute("pageStart", page - 4);
        } else {
            model.addAttribute("pageStart", 1);
        }

        if (pageNumber > 5) {
            model.addAttribute("pageEnd", page);
        } else {
            model.addAttribute("pageEnd", pageNumber);
        }
        model.addAttribute("pageNumber", pageNumber);
        return "index";
    }

    @GetMapping("/article/{id}")
    public String article(@PathVariable Long id, Model model) {
        Article article = articleService.getArticle(id);
        model.addAttribute("article", article);
        return "article";
    }
}
