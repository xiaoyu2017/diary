package cn.fishland.diary.filter;

import cn.fishland.diary.pojo.User;
import cn.fishland.diary.util.DiaryUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

/**
 * TODO
 *
 * @author xiaoyu
 * @version 1.0
 */
@Slf4j
@Component
public class CheckFilter implements Filter {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        // 登录放行所有
        if (isLogin(httpServletRequest) || isPassLink(httpServletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            // 不可放行的需要登录
            servletRequest.setAttribute("error", "请先登录！");
            httpServletRequest.getRequestDispatcher("/login").forward(servletRequest, servletResponse);
        }
    }

    /**
     * 放行的请求链接
     *
     * @param request 请求内容
     * @return 是否放行
     */
    private boolean isPassLink(HttpServletRequest request) {
        // 判断是否为登录和注册请求
        String uri = request.getRequestURI();

        // 记录api访问
        countApiBrowse(uri);

        if (uri.equals("/login") || uri.equals("/register") || uri.equals("/") || uri.equals("/index")
                || uri.matches("/article/.+") || uri.matches("/css/.+") || uri.matches("/img/.+")
                || uri.matches("/js/.+") || uri.matches("/self/.+") || uri.equals("/v1/login")
                || uri.matches("/v1/img/.+")|| uri.matches("/page/.+")) {
            return true;
        }
        return false;
    }

    private void countApiBrowse(String uri) {
        redisTemplate.opsForHash().increment(DiaryUtil.REDIS_API_BROWSE_KEY, uri, 1L);
    }

    /**
     * 是否登录
     *
     * @param request 请求内容
     * @return 是否登录
     */
    public Boolean isLogin(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (Objects.nonNull(user) && user.getId() != null) {
            return true;
        }
        return false;
    }

}
