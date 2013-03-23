package cn.cavy.zoe.action.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.cavy.common.util.DataPage;
import cn.cavy.zoe.action.PageAction;
import cn.cavy.zoe.entity.Category;
import cn.cavy.zoe.entity.Journal;
import cn.cavy.zoe.service.CategoryService;
import cn.cavy.zoe.service.JournalService;
import cn.cavy.zoe.service.TagService;

@Controller
@RequestMapping("/admin/journal")
public class JournalAction extends PageAction {

    @Resource
    CategoryService categoryService;

    @Resource
    TagService tagService;

    @Resource
    JournalService journalService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, HttpServletRequest request) {
        DataPage<Journal> journals = journalService.findIndexJournal(getPageIndex(request), getPageSize(request));
        model.addAttribute("journals", journals);

        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "admin/journalList";
    }

    @RequestMapping(value = "/{path}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable
    String path) {
        Journal journal = journalService.getJournalByPath(path);
        if (journal == null)
            journal = new Journal();
        model.addAttribute("journal", journal);

        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);

        String tagStr = tagService.getStrByJournalId(journal.getId());
        model.addAttribute("tag", tagStr);

        return "admin/journalEdit";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newJournal(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);

        model.addAttribute("journal", new Journal());

        return "admin/journalEdit";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String save(Model model, HttpServletRequest request, @ModelAttribute("journal")
    Journal journal, @RequestParam("tagStr")
    String tagStr) {
        journalService.create(journal, tagStr);

        return this.list(model, request);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);

        model.addAttribute("journal", new Journal());

        return "admin/journalList";
    }
    
    @RequestMapping(value = "/imageUpload")
    public void imageUpload(Model model, HttpServletRequest request) {
    }
}