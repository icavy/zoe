package cn.cavy.zoe.mapper;

import java.util.List;

import cn.cavy.zoe.entity.Menu;
import cn.cavy.zoe.filter.MenuFilter;

public interface MenuMapper {

    List<Menu> findByFilter(MenuFilter filter);
}
