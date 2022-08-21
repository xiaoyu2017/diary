package cn.fishland.diary.controller.v1;

import cn.fishland.diary.controller.VersionController;
import cn.fishland.diary.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * TODO
 *
 * @author xiaoyu
 * @version 1.0
 */
@Controller
@RequestMapping("/test")
public class TestV1Controller extends VersionController {

    @Autowired
    RedisService redisService;

    @ResponseBody
    @GetMapping("/version")
    public String versionTest() {
        return "version";
    }


    @ResponseBody
    @GetMapping("/redis/list")
    public String redis() {
        redisService.setList("Test", "123");
        return "version";
    }
}
