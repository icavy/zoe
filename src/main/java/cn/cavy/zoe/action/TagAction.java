package cn.cavy.zoe.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import cn.cavy.common.util.DataPage;
import cn.cavy.zoe.entity.Category;
import cn.cavy.zoe.entity.Journal;
import cn.cavy.zoe.entity.Menu;
import cn.cavy.zoe.entity.Tag;
import cn.cavy.zoe.service.CategoryService;
import cn.cavy.zoe.service.JournalService;
import cn.cavy.zoe.service.MenuService;
import cn.cavy.zoe.service.TagService;

@Controller
@RequestMapping("/tag")
public class TagAction extends PageAction {

    @Resource
    MenuService menuService;

    @Resource
    JournalService journalService;

    @Resource
    TagService tagService;

    @Resource
    CategoryService categoryService;

    @RequestMapping(value = "/{tagName}", method = RequestMethod.GET)
    public String post(Model model, HttpServletRequest request, @PathVariable
    String tagName) {
        List<Menu> menus = menuService.findAll();
        model.addAttribute("menus", menus);

        DataPage<Journal> journals = journalService.getJournalByTagName(tagName, getPageIndex(request),
                getPageSize(request));
        model.addAttribute("journals", journals);

        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);

        List<Tag> tags = tagService.getTagClound();
        model.addAttribute("tags", tags);
        
        model.addAttribute("tagName", tagName);

        return "tag";
    }
}
