package cn.cavy.zoe.action.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/")
public class AdminIndexAction {

    @RequestMapping
    public String index(Model model, HttpServletRequest request) {
        return "admin/index";
    }
}