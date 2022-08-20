package cn.fishland.diary.controller.v1;

import cn.fishland.diary.controller.VersionController;
import cn.fishland.diary.pojo.Article;
import cn.fishland.diary.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * TODO
 *
 * @author LessAnn
 * @version 1.0
 */
@Controller
public class ArticleController extends VersionController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/home/article/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        Article article = articleService.getArticle(id);
        if (article == null || id == null) {
            return "redirect:../../article-list";
        }
        model.addAttribute("article", article);
        model.addAttribute("update", 1);
        return "home/article-edit";
    }

    @GetMapping("/home/article/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        Boolean delete = articleService.delete(id);
        if (delete) {
            return "redirect:../../article-list";
        }
        model.addAttribute("error", "delete id={" + id + "} error");
        return "home/article-list";
    }

    @PostMapping("/home/article")
    public String article(Article article, Integer update) {
        if (update == 0) {
            articleService.addArticle(article);
        }
        if (update == 1) {
            articleService.updateArticle(article);
        }
        return "redirect:./article-list";
    }


}
