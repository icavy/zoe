package cn.cavy.zoe.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.cavy.zoe.entity.Config;
import cn.cavy.zoe.mapper.ConfigMapper;
import cn.cavy.zoe.service.ConfigService;

@Service("configService")
public class ConfigServiceImpl implements ConfigService {

    @Resource
    ConfigMapper configMapper;

    @Resource
    Properties configProperties;

    private static Map<String, Config> configCache = new HashMap<String, Config>();

    @PostConstruct
    public void initCache() {
        List<Config> configList = configMapper.findAll();
        for (Config config : configList) {
            configCache.put(config.getKey(), config);
        }
    }

    public String getValue(String key) {
        String value = null;
        Config config = getConfig(key);
        if (config == null)
            value = configProperties.getProperty(key);
        else 
            value = config.getValue();
        
        return value;
    }

    public Config getConfig(String key) {
        return configCache.get(key);
    }

    public Integer getIntValue(String key) {
        String value = this.getValue(key);
        return value == null ? null : Integer.valueOf(value);
    }

    public Set<String> getGroups() {
        Set<String> groupSet = new HashSet<String>();
        Collection<Config> configs = configCache.values();
        for (Config config : configs) {
            groupSet.add(config.getGroup());
        }
        return groupSet;
    }

    public Set<Config> getGroupConfig(String group) {
        Set<Config> groupSet = new HashSet<Config>();
        Collection<Config> configs = configCache.values();
        for (Config config : configs) {
            if (config.getGroup().equals(group))
                groupSet.add(config);
        }
        return groupSet;
    }

    public void clearCache() {
        configCache.clear();
        initCache();
    }

    public void save(Config config) {
        configMapper.save(config);

        configCache.put(config.getKey(), config);
    }
}