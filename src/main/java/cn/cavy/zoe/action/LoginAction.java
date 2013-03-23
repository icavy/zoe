package cn.cavy.zoe.action;

import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.cavy.zoe.exception.BusinessException;
import cn.cavy.zoe.service.AuthorizingService;

@Controller
@RequestMapping("/login")
public class LoginAction extends BaseAction {

    @Resource
    AuthorizingService authorizingService;

    @RequestMapping(method = RequestMethod.GET)
    public String loginPage(HttpServletRequest request) {
        return Constant.LOGIN;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String login(@RequestParam
    String loginName, @RequestParam
    String password, HttpServletRequest request) {

        authorizingService.login(loginName, password);

        return redirect("");
    }

    @SuppressWarnings("unchecked")
    @ExceptionHandler(BusinessException.class)
    public String handleException(BusinessException ex, HttpServletRequest request) {
        Enumeration<String> e = request.getParameterNames();
        while(e.hasMoreElements()){
            String parameterName =  e.nextElement();
            request.setAttribute(parameterName, request.getParameter(parameterName));
        }
        
        request.setAttribute("message", ex.getMessage());

        return request.getServletPath();
    }
}