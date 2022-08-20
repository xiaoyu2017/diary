package cn.fishland.diary.filter;

import cn.fishland.diary.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * TODO
 *
 * @author LessAnn
 * @version 1.0
 */
@Slf4j
//@Component
public class CheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        // 判断是否为登录和注册请求
        String uri = httpServletRequest.getRequestURI();
        if (uri != null && uri.equals("/login") || uri.equals("/register")) {
            filterChain.doFilter(servletRequest, servletResponse);
        }

        // 判断是否登录过
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if (user != null && user.getId() != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        }


        servletRequest.setAttribute("error", "请先登录！");
        httpServletRequest.getRequestDispatcher("/login").forward(servletRequest, servletResponse);
    }

}
