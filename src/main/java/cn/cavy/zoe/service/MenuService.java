package cn.cavy.zoe.service;

import java.util.List;

import cn.cavy.zoe.entity.Menu;

public interface MenuService {

    /**
     * 获取所有的菜单
     * 
     * @return
     */
    public List<Menu> findAll();

    public void clearCache();
}
