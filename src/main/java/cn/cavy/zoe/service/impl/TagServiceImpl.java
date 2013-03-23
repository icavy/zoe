package cn.cavy.zoe.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.cavy.common.util.CollectionUtil;
import cn.cavy.common.util.StringUtil;
import cn.cavy.zoe.entity.Tag;
import cn.cavy.zoe.mapper.TagMapper;
import cn.cavy.zoe.service.ConfigService;
import cn.cavy.zoe.service.TagService;

@Service("tagService")
public class TagServiceImpl implements TagService {

    @Resource
    TagMapper tagMapper;

    @Resource
    ConfigService configService;

    private static Map<Long, Tag> tagCache = new HashMap<Long, Tag>();

    @PostConstruct
    public void initCache() {
        if (tagCache.size() > 0)
            tagCache.clear();

        List<Tag> tags = tagMapper.findAll();
        for (Tag tag : tags) {
            tagCache.put(tag.getId(), tag);
        }
    }

    public List<Tag> getByJournalId(Long journalId) {
        List<Long> tagIds = tagMapper.findTagIdByJournalId(journalId);

        List<Tag> tags = new ArrayList<Tag>();
        for (Long id : tagIds) {
            tags.add(tagCache.get(id));
        }
        return tags;
    }

    public List<Tag> getTagClound() {
        Integer tagCloundSize = configService.getIntValue(ConfigService.SETTING_TAG_CLOUND_SIZE);

        List<Tag> tagList = new ArrayList<Tag>();
        tagList.addAll(tagCache.values());
        if (tagCloundSize.intValue() >= tagList.size())
            return tagList;

        List<Tag> tagClound = new ArrayList<Tag>();
        for (int i = 0; i < tagList.size(); i++) {
            int index = new Random().nextInt(tagList.size());
            tagClound.add(tagList.get(index));

            tagList.remove(index);
        }

        return tagClound;
    }

    public void create(String tagStr, Long journalId) {
        if (StringUtil.isEmpty(tagStr))
            return;

        String[] tags = tagStr.split(configService.getValue(ConfigService.SETTING_TAG_SPLIT));

        Collection<Tag> tagInCache = tagCache.values();
        for (String tagName : tags) {
            if (StringUtil.isEmpty(tagName))
                return;

            boolean tagExist = false;
            for (Tag tag : tagInCache) {
                if (tagName.equals(tag.getName())) {
                    relate(tag.getId(), journalId);
                    tagExist = true;
                }
            }
            if (!tagExist)
                createAndRelate(tagName, journalId);
        }
    }

    private void createAndRelate(String tagName, Long journalId) {
        Tag tag = new Tag();
        tag.setName(tagName);
        tagMapper.save(tag);
        
        tagCache.put(tag.getId(), tag);

        this.relate(tag.getId(), journalId);        
    }

    private void relate(Long tagId, Long journalId) {
        tagMapper.relate(tagId, journalId);
    }

    public String getStrByJournalId(Long journalId) {
        List<Tag> tagList = this.getByJournalId(journalId);
        List<String> tagNameList = new ArrayList<String>(tagList.size());
        for (Tag tag : tagList) {
            tagNameList.add(tag.getName());
        }

        return CollectionUtil.toString(tagNameList, configService.getValue(ConfigService.SETTING_TAG_SPLIT));

    }
}