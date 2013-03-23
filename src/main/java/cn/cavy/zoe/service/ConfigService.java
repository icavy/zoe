package cn.cavy.zoe.service;

import java.util.Set;

import cn.cavy.zoe.entity.Config;

public interface ConfigService {

    public static final String BASE_TITLE = "base.title";

    public static final String BASE_SUBTITLE = "base.subtitle";

    public static final String BASE_URL = "base.url";

    public static final String SETTING_TAG_SPLIT = "settings.tagSplit";

    public static final String SETTING_TAG_CLOUND_SIZE = "settings.tagCloundSize";

    public static final String SETTING_SKIN = "settings.skin";

    String getValue(String key);

    Integer getIntValue(String key);

    Config getConfig(String key);

    Set<String> getGroups();

    Set<Config> getGroupConfig(String group);

    void clearCache();

    void save(Config config);
}
