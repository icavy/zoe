package cn.cavy.zoe.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.cavy.common.util.RequestUtil;

public class AuthenticationFilter implements Filter {

    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    public static final String USER_SESSION = "userSession";

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @SuppressWarnings("unused")
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        final HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        final HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        Object sessionObj = RequestUtil.getSessionAttribute(httpServletRequest, AuthenticationFilter.USER_SESSION);
        if (sessionObj != null) {
            UserSession userSession = (UserSession) sessionObj;

            chain.doFilter(request, response);
        } else {
            httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    public void destroy() {
    }
}
