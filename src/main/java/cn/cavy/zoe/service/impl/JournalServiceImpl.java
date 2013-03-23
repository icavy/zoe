package cn.cavy.zoe.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cavy.common.util.DataPage;
import cn.cavy.zoe.entity.Journal;
import cn.cavy.zoe.filter.JournalFilter;
import cn.cavy.zoe.mapper.JournalMapper;
import cn.cavy.zoe.service.CategoryService;
import cn.cavy.zoe.service.JournalService;
import cn.cavy.zoe.service.TagService;

@Service("journalService")
public class JournalServiceImpl implements JournalService {

    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(JournalServiceImpl.class);

    @Autowired
    JournalMapper journalMapper;

    @Autowired
    CategoryService categoryService;

    @Autowired
    TagService tagService;

    public DataPage<Journal> findIndexJournal(int pageIndex, int pageSize) {
        int journalCount = journalMapper.getCount(new JournalFilter());

        int offset = (pageIndex - 1) * pageSize;

        List<Journal> journals = journalMapper.findIndexJournal(offset, pageSize);
        for (Journal journal : journals) {
            setLinked(journal);
        }

        return new DataPage<Journal>(journalCount, pageIndex, pageSize, journals);
    }

    public Journal getJournalByPath(String path) {
        Journal journal = journalMapper.getByPath(path);
        if (journal == null)
            return null;
        setLinked(journal);
        return journal;
    }

    public DataPage<Journal> getJournalByCatPath(String catPath, Integer pageIndex, Integer pageSize) {
        int journalCount = journalMapper.getCount(new JournalFilter());

        int offset = (pageIndex - 1) * pageSize;

        List<Journal> journals = journalMapper.findByCatPath(catPath, offset, pageSize);
        for (Journal journal : journals) {
            setLinked(journal);
        }

        return new DataPage<Journal>(journalCount, pageIndex, pageSize, journals);
    }

    private void setLinked(Journal journal) {
        if (journal == null)
            return;
        journal.setCategory(categoryService.get(journal.getCategoryId()));
        journal.setTags(tagService.getByJournalId(journal.getId()));
    }

    public DataPage<Journal> getJournalByTagName(String tagName, Integer pageIndex, Integer pageSize) {
        int journalCount = journalMapper.getCount(new JournalFilter());

        int offset = (pageIndex - 1) * pageSize;

        List<Journal> journals = journalMapper.findByTagName(tagName, offset, pageSize);
        for (Journal journal : journals) {
            setLinked(journal);
        }

        return new DataPage<Journal>(journalCount, pageIndex, pageSize, journals);
    }

    public void create(Journal journal, String tagStr) {
        journal.setCreateDate(new Date());

        if (journal.getId() == null)
            journalMapper.save(journal);
        else
            journalMapper.update(journal);

        tagService.create(tagStr, journal.getId());
    }

    public DataPage<Journal> findIndexJournal(JournalFilter journalFilter, Integer pageIndex, Integer pageSize) {

        int offset = (pageIndex - 1) * pageSize;

        int journalCount = journalMapper.getCount(journalFilter);

        List<Journal> journals = journalMapper.findByFilter(journalFilter, offset, pageSize);
        for (Journal journal : journals) {
            setLinked(journal);
        }

        return new DataPage<Journal>(journalCount, pageIndex, pageSize, journals);
    }
}