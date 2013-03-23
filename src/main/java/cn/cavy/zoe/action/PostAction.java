package cn.cavy.zoe.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.cavy.zoe.entity.Category;
import cn.cavy.zoe.entity.Journal;
import cn.cavy.zoe.entity.Menu;
import cn.cavy.zoe.entity.Tag;
import cn.cavy.zoe.service.CategoryService;
import cn.cavy.zoe.service.JournalService;
import cn.cavy.zoe.service.MenuService;
import cn.cavy.zoe.service.TagService;

@Controller
@RequestMapping("/post")
public class PostAction extends BaseAction{

    @Resource
    MenuService menuService;

    @Resource
    JournalService journalService;

    @Resource
    TagService tagService;

    @Resource
    CategoryService categoryService;

    @RequestMapping(value = "/{path}", method = RequestMethod.GET)
    public String post(Model model, HttpServletRequest request, HttpServletResponse response, @PathVariable
    String path) throws IOException {
        Journal journal = journalService.getJournalByPath(path);
        if (journal == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        model.addAttribute("journal", journal);

        List<Menu> menus = menuService.findAll();
        model.addAttribute("menus", menus);

        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);

        List<Tag> tags = tagService.getTagClound();
        model.addAttribute("tags", tags);

        return "post";
    }
}