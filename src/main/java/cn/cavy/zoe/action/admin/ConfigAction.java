package cn.cavy.zoe.action.admin;

import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.cavy.common.util.CollectionUtil;
import cn.cavy.zoe.action.BaseAction;
import cn.cavy.zoe.action.vo.OperationResult;
import cn.cavy.zoe.entity.Config;
import cn.cavy.zoe.service.ConfigService;

@Controller
@RequestMapping("/admin/config")
public class ConfigAction {

    @Resource
    ConfigService configService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, HttpServletRequest request) {
        Set<String> groupSet = configService.getGroups();
        model.addAttribute("groupSet", groupSet);

        if (CollectionUtil.isNotEmpty(groupSet)) {
            String defaultGroup = groupSet.iterator().next();
            Set<Config> configSet = configService.getGroupConfig(defaultGroup);

            model.addAttribute("configSet", configSet);
        }

        return "admin/configList";
    }

    @RequestMapping(value = "/{group}", method = RequestMethod.GET)
    public String listGroup(Model model, HttpServletRequest request, @PathVariable
    String group) {
        Set<String> groupSet = configService.getGroups();
        model.addAttribute("groupSet", groupSet);

        Set<Config> configSet = configService.getGroupConfig(group);
        model.addAttribute("configSet", configSet);

        return "admin/configList";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newConfig(Model model, HttpServletRequest request) {
        Config config = new Config();
        model.addAttribute("config", config);

        return "admin/configEdit";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String save(Model model, @ModelAttribute("config")
    Config config) {
        configService.save(config);

        model.addAttribute("config", config);
        model.addAttribute(BaseAction.OPERATION_RESULT, OperationResult.SUCCESS);

        return "admin/configEdit";
    }
}