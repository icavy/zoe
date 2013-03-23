package cn.cavy.zoe.mapper;

import java.util.Collection;

import org.apache.ibatis.annotations.Param;

public interface PermissionMapper {

    Collection<String> queryPermissions(@Param("loginName")
    String loginName);

}
