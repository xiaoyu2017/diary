package cn.fishland.diary.controller.v1;

import cn.fishland.diary.controller.VersionController;
import cn.fishland.diary.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * TODO
 *
 * @author LessAnn
 * @version 1.0
 */
@Controller
public class IndexController extends VersionController {

    @Autowired
    ArticleService articleService;

    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }
}
