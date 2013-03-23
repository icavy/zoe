package cn.cavy.zoe.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.cavy.zoe.service.ConfigService;

public class SkinInterceptor implements HandlerInterceptor {

    @Resource
    ConfigService configService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        if (modelAndView != null && needSkin(modelAndView.getViewName())) {
            modelAndView.getModel().put("skinPath", getSkinPath(request));
            modelAndView.setViewName("/skins/" + configService.getValue(ConfigService.SETTING_SKIN) + "/"
                    + modelAndView.getViewName());
        }
    }

    public String getSkinPath(HttpServletRequest request) {
        return request.getSession().getServletContext().getContextPath() + "/skins/"
                + configService.getValue(ConfigService.SETTING_SKIN);
    }

    private boolean needSkin(String viewName) {
        return !(viewName.startsWith("admin") || viewName.startsWith("login") || viewName.startsWith("forward:") || viewName
                .startsWith("redirect:"));
    }
}