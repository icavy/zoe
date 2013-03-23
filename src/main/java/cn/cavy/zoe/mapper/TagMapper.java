package cn.cavy.zoe.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.cavy.zoe.entity.Tag;

public interface TagMapper {

    List<Tag> findAll();

    List<Long> findTagIdByJournalId(@Param("journalId")
    Long journalId);

    void save(Tag tag);

    void relate(@Param("tagId")
    Long tagId, @Param("journalId")
    Long journalId);
}
