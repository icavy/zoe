package cn.cavy.zoe.service.impl;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.cavy.zoe.entity.Menu;
import cn.cavy.zoe.filter.MenuFilter;
import cn.cavy.zoe.mapper.MenuMapper;
import cn.cavy.zoe.service.MenuService;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

    private Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Autowired
    MenuMapper menuMapper;

    @Cacheable(value = "COMMON", key = "'menu'")
    public List<Menu> findAll() {
        List<Menu> menuList = this.getTopMenu();
        for (Menu menu : menuList) {
            buildSubMenu(menu);
        }

        return menuList;
    }

    private void buildSubMenu(Menu menu) {
        List<Menu> subMenuList = this.getSubMenuList(menu.getId());
        for (Menu subMenu : subMenuList) {
            buildSubMenu(subMenu);
        }

        menu.setSubMenuList(subMenuList);
    }

    @SuppressWarnings("unchecked")
    private List<Menu> getSubMenuList(Long id) {
        if (id == null)
            return Collections.EMPTY_LIST;

        MenuFilter filter = new MenuFilter();
        filter.setParentId(id);

        return menuMapper.findByFilter(filter);
    }

    /**
     * 获取顶层菜单
     * 
     * @return
     */
    private List<Menu> getTopMenu() {
        return this.getSubMenuList(Menu.TOP_MENU_ID);
    }

    @CacheEvict(value = "COMMON", key = "'menu'")
    public void clearCache() {
        logger.info("clearCache");
    }
}