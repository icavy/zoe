package cn.cavy.zoe.service;

import java.util.List;

import cn.cavy.zoe.entity.Category;

public interface CategoryService {

    Category get(Long categoryId);

    List<Category> findAll();

    Category getByPath(String path);

}
