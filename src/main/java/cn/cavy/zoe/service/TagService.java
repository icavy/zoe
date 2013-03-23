package cn.cavy.zoe.service;

import java.util.List;

import cn.cavy.zoe.entity.Tag;


public interface TagService {

    List<Tag> getByJournalId(Long id);

    List<Tag> getTagClound();

    void create(String tagStr, Long id);

    String getStrByJournalId(Long id);

}
