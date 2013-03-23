package cn.cavy.zoe.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.cavy.zoe.entity.Category;

public interface CategoryMapper {

    List<Category> findAll();

    Category getByPath(@Param("path")
    String path);

}
