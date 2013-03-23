package cn.cavy.zoe.service;

import cn.cavy.common.util.DataPage;
import cn.cavy.zoe.entity.Journal;
import cn.cavy.zoe.filter.JournalFilter;


public interface JournalService {

    DataPage<Journal> findIndexJournal(int pageIndex, int pageSize);

    Journal getJournalByPath(String path);

    DataPage<Journal> getJournalByCatPath(String catName, Integer pageIndex, Integer pageSize);

    DataPage<Journal> getJournalByTagName(String tagName, Integer pageIndex, Integer pageSize);

    void create(Journal journal, String tagStr);

    DataPage<Journal> findIndexJournal(JournalFilter journalFilter, Integer pageIndex, Integer pageSize);
}
