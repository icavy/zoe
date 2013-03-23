package cn.cavy.zoe.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.cavy.zoe.entity.Journal;

public interface JournalMapper {

    int getCount();

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
}
