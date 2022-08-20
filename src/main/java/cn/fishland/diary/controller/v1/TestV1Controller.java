package cn.fishland.diary.controller.v1;

import cn.fishland.diary.controller.VersionController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * TODO
 *
 * @author LessAnn
 * @version 1.0
 */
@Controller
@RequestMapping("/test")
public class TestV1Controller extends VersionController {

    @ResponseBody
    @GetMapping("/version")
    public String versionTest() {
        return "version";
    }
}
