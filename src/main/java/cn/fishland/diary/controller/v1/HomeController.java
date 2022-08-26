package cn.fishland.diary.controller.v1;

import cn.fishland.diary.controller.VersionController;
import cn.fishland.diary.pojo.Article;
import cn.fishland.diary.pojo.User;
import cn.fishland.diary.service.ArticleService;
import cn.fishland.diary.service.UserService;
import cn.fishland.diary.util.DiaryUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
 * @author xiaoyu
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

    @GetMapping("/home/article-edit")
    public String articleEdit(Model model) {
        model.addAttribute("update", 0);
        return "home/article-edit";
    }

    @GetMapping("/home/article-list")
    public String articleList(Integer page, Model model) {
        List<Article> articles = articleService.getArticles(page);
        model.addAttribute("articles", articles);
        return "home/article-list";
    }

    /* 数据请求 */
    @PostMapping("/login")
    public String check(User user, String code, HttpSession session) {
        // 判断验证码
        if (StringUtils.isBlank(code) || !code.equalsIgnoreCase((String) session.getAttribute(DiaryUtil.SESSION_KEY_IMAGE_CODE))) {
            session.getServletContext().setAttribute("message", "验证码不正确！");
            return "redirect:/login";
        }

        Boolean check = userService.userCheck(user, session);
        if (check) {
            return "redirect:./home/article-edit";
        }
        session.getServletContext().setAttribute("message", "用户名或密码不正确！");
        return "redirect:/login";
    }

    /*@PostMapping("/register")
    public String register(User user, Model model) {
        if (user != null && user.getName() != null && user.getPassword() != null) {
            Boolean register = userService.register(user);
            if (register) {
                return "login";
            }
            return "register";
        }
        return "register";
    }*/
}
