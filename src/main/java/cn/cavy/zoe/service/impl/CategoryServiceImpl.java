package cn.cavy.zoe.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.cavy.zoe.entity.Category;
import cn.cavy.zoe.mapper.CategoryMapper;
import cn.cavy.zoe.service.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Resource
    CategoryMapper categoryMapper;

    private static Map<Long, Category> categoryCache = new HashMap<Long, Category>();

    @PostConstruct
    public void initCache() {
        List<Category> categories = categoryMapper.findAll();
        for (Category category : categories) {
            categoryCache.put(category.getId(), category);
        }
    }

    public Category get(Long categoryId) {
        return categoryCache.get(categoryId);
    }

    public List<Category> findAll() {
        List<Category> categories = new ArrayList<Category>();
        categories.addAll(categoryCache.values());
        return categories;
    }

    public Category getByPath(String path) {
        return categoryMapper.getByPath(path);
    }
}