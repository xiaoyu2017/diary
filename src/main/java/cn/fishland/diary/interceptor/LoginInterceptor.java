package cn.fishland.diary.interceptor;

import cn.fishland.diary.pojo.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * TODO
 *
 * @author xiaoyu
 * @version 1.0
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        System.out.println(">>>>" + uri);
        // 是否登录
        if (isLogin(request)) {
            return true;
        }
        response.sendRedirect("/login");
        return false;
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

        if (uri.equals("/login") || uri.equals("/register") || uri.equals("/") || uri.equals("/index")
                || uri.matches("/article/.+") || uri.matches("/css/.+") || uri.matches("/img/.+")
                || uri.matches("/js/.+") || uri.matches("/self/.+") || uri.equals("/v1/login")
                || uri.matches("/v1/img/.+") || uri.matches("/page/.+") || uri.equals("/code/image")) {
            return true;
        }
        return false;
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
