package cn.fishland.diary.controller.v1;

import cn.fishland.diary.controller.VersionController;
import cn.fishland.diary.pojo.Article;
import cn.fishland.diary.pojo.User;
import cn.fishland.diary.service.ArticleService;
import cn.fishland.diary.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 后台页面相关访问控制类
 *
 * @author LessAnn
 * @version 1.0
 */
@Slf4j
@Controller
public class HomeController extends VersionController {

    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;

    /* 页面handler */

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home/article-edit")
    public String articleEdit() {
        return "home/article-edit";
    }

    @GetMapping("/home/article-list")
    public String articleList(Integer page,Model model) {
        List<Article> articles = articleService.getArticles(page);
        model.addAttribute("articles", articles);
        return "home/article-list";
    }

    /* 数据请求 */

    @PostMapping("/login")
    public String check(User user, HttpSession session) {
        Boolean check = userService.userCheck(user, session);
        if (check) {
            return "redirect:./home/home-edit";
        }
        session.getServletContext().setAttribute("message", "用户名或密码不正确！");
        return "login";
    }

    @PostMapping("/register")
    public String register(User user, Model model) {
        if (user != null && user.getName() != null && user.getPassword() != null) {
            Boolean register = userService.register(user);
            if (register) {
                return "login";
            }
            return "register";
        }
        return "register";
    }
}
