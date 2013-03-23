package cn.cavy.zoe.action.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.cavy.zoe.service.ConfigService;

@Controller
@RequestMapping("/admin/cache")
public class CacheAction {

    @Resource
    ConfigService configService;
    
    @RequestMapping
    public String index(Model model, HttpServletRequest request) {
        return "admin/cache";
    }
    
    @RequestMapping(value="/clearConfigCache")
    public String clearConfigCache(Model model, HttpServletRequest request) {
        
        configService.clearCache();
        
        return "admin/cache";
    }
}
