package top.zzh.filter;

import top.zzh.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    //初始化
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("loginfilter init...");
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();
        if (notFilter(uri)) {
            filterChain.doFilter(servletRequest,  servletResponse);
        } else {
            HttpSession session = request.getSession();
            Object user = session.getAttribute(Constants.USER_IN_SESSION);
            if (user != null) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                response.sendRedirect("/");
            }
        }
    }

    //销毁
    @Override
    public void destroy() {
        logger.info("loginfilter destroy...");
    }

    //不需要过滤的页面和样式
    private boolean notFilter(String uri) {
        return uri.equals("/") || uri.contains("/user/login") || uri.contains("/code")
         || uri.contains("/static");
    }


}
