package cn.cavy.zoe.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.cavy.zoe.entity.Journal;
import cn.cavy.zoe.filter.JournalFilter;

public interface JournalMapper {

    List<Journal> findIndexJournal(@Param("offset")
    int offset, @Param("length")
    int length);

    Journal getByPath(@Param("path")
    String path);

    List<Journal> findByCatPath(@Param("catPath")
    String catPath, @Param("offset")
    int offset, @Param("length")
    int length);

    List<Journal> findByTagName(@Param("tagName")
    String tagName, @Param("offset")
    int offset, @Param("length")
    int length);

    void save(Journal journal);

    void update(Journal journal);

    List<Journal> findByFilter(@Param("filter")
    JournalFilter journalFilter, @Param("offset")
    int offset, @Param("length")
    int length);

    int getCount(@Param("filter")
    JournalFilter journalFilter);
}
