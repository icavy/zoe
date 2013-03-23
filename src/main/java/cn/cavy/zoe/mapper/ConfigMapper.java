package cn.cavy.zoe.mapper;

import java.util.List;

import cn.cavy.zoe.entity.Config;

public interface ConfigMapper {

    List<Config> findAll();

    void save(Config config);
}
