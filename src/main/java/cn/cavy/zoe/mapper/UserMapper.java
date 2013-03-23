package cn.cavy.zoe.mapper;

import org.apache.ibatis.annotations.Param;

import cn.cavy.zoe.entity.User;

public interface UserMapper {

    User getByLoginName(@Param("loginName")
    String loginName);

}
